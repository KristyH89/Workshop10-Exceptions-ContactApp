package se.lexicon.data;

import se.lexicon.model.Contact;
import se.lexicon.exception.ContactStorageException;
import se.lexicon.exception.DuplicateContactException;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileContactDAOImpl implements ContactDAO {

    private Path filePath;

    public FileContactDAOImpl(Path filePath) {
        this.filePath = filePath;

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new ContactStorageException("Failed to initialize storage.");
        }
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacts.add(new Contact(parts[0], parts[1]));
                }
            }

        } catch (IOException e) {
            throw new ContactStorageException("Failed to read contacts.");
        }

        return contacts;
    }

    @Override
    public void save(Contact contact){
        if (findByName(contact.getName()) != null) {
            throw new DuplicateContactException("Contact already exists.");
        }

        try {
            Files.writeString(
                    filePath,
                    contact.getName() + "," + contact.getPhoneNumber() + System.lineSeparator(),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new ContactStorageException("Failed to save contact.");
        }
    }

    @Override
    public Contact findByName(String name) {
        return findAll()
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
