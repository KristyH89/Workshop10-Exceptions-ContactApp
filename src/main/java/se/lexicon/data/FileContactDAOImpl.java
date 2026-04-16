package se.lexicon.data;

import se.lexicon.exception.ContactStorageException;
import se.lexicon.exception.DuplicateContactException;
import se.lexicon.model.Contact;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/// File-backed [ContactDAO]. Each contact is stored as one `name,phoneNumber` line.
public class FileContactDAOImpl implements ContactDAO {

    private final Path filePath;

    /// Creates the DAO. The backing file is created automatically if it does not exist.
    /// @throws ContactStorageException if the file cannot be created
    public FileContactDAOImpl(Path filePath) throws ContactStorageException {
        if (filePath == null) {
            throw new IllegalArgumentException("filePath must not be null.");
        }
        this.filePath = filePath;
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new ContactStorageException(
                    "Failed to initialise contact storage at: " + filePath, e);
        }
    }

    /// {@inheritDoc}
    ///
    /// Reads all lines from the backing file. Blank lines are skipped. Lines that cannot be parsed are skipped without throwing.
    @Override
    public List<Contact> findAll() throws ContactStorageException {
        List<Contact> contacts = new ArrayList<>();

        try (var reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    contacts.add(new Contact(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            throw new ContactStorageException("Failed to read contacts from storage.", e);
        }

        return contacts;
    }

    /// {@inheritDoc}
    ///
    /// A case-insensitive duplicate check is performed before writing.
    @Override
    public void save(Contact contact) throws ContactStorageException, DuplicateContactException {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null.");
        }

        // Check for duplicate before touching the file
        for (Contact existing : findAll()) {
            if (existing.getName().equalsIgnoreCase(contact.getName())) {
                throw new DuplicateContactException(
                        "A contact with the name '" + contact.getName() + "' already exists.");
            }
        }

        // APPEND mode so existing entries are preserved
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            writer.write(contact.toString());
            writer.newLine();
        } catch (IOException e) {
            throw new ContactStorageException(
                    "Failed to save contact '" + contact.getName() + "'.", e);
        }
    }

    @Override
    public Contact findByName(String name) throws ContactStorageException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Search name must not be null or blank.");
        }

        return findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() ->
                        new ContactStorageException("No contact found with name: " + name));
    }
}
