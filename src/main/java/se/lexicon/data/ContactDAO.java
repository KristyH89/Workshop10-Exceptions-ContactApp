package se.lexicon.data;

import se.lexicon.exception.ContactStorageException;
import se.lexicon.exception.DuplicateContactException;
import se.lexicon.model.Contact;

import java.util.List;

/// DAO contract for [Contact] persistence. Implementations must not write to the console to keep MVC layers separate.
public interface ContactDAO {

    /// @throws ContactStorageException if storage cannot be read
    List<Contact> findAll() throws ContactStorageException;

    /// @throws ContactStorageException if storage cannot be written
    /// @throws DuplicateContactException if a contact with the same name already exists (case-insensitive)
    void save(Contact contact) throws ContactStorageException, DuplicateContactException;

    /// @throws ContactStorageException if storage cannot be read or no match is found
    Contact findByName(String name) throws ContactStorageException;
}
