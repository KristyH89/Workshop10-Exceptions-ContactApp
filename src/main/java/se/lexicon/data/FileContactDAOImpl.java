package se.lexicon.data;

import se.lexicon.exception.ContactStorageException;
import se.lexicon.exception.DuplicateContactException;
import se.lexicon.model.Contact;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileContactDAOImpl implements ContactDAO {

    private final Path filePath;

    public FileContactDAOImpl(Path filePath) throws ContactStorageException {
        this.filePath = filePath;

        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new ContactStorageException("Failed to initialize storage", e);
        }
    }

    @Override
    public List<Contact> findAll() throws ContactStorageException {
        List<Contact> contacts = new ArrayList<>();

        try (var lines = Files.lines(filePath)) {
            lines.forEach(line -> {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacts.add(new Contact(parts[0], parts[1]));
                }
            });
        } catch (IOException e) {
            throw new ContactStorageException("Failed to read contacts", e);
        }

        return contacts;
    }

    @Override
    public void save(Contact contact)
            throws ContactStorageException, DuplicateContactException {

        if (findByName(contact.getName()) != null) {
            throw new DuplicateContactException("Contact already exists: " + contact.getName());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardOpenOption.APPEND)) {

            writer.write(contact.getName() + "," + contact.getPhoneNumber());
            writer.newLine();

        } catch (IOException e) {
            throw new ContactStorageException("Failed to save contact", e);
        }
    }

    @Override
    public Contact findByName(String name) throws ContactStorageException {
        return findAll()
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}