package se.lexicon.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.lexicon.exception.ContactStorageException;
import se.lexicon.exception.DuplicateContactException;
import se.lexicon.model.Contact;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileContactDAOImplTest {

    @TempDir
    Path tempDir;

    private FileContactDAOImpl dao;

    @BeforeEach
    void setUp() throws Exception {
        Path file = tempDir.resolve("contacts.txt");
        dao = new FileContactDAOImpl(file);
    }

    @Test
    void shouldSaveAndFindAllContacts() throws Exception {
        dao.save(new Contact("Alice", "0701234567"));
        dao.save(new Contact("Bob", "0739876543"));

        List<Contact> contacts = dao.findAll();

        assertEquals(2, contacts.size());
    }

    @Test
    void shouldFindContactByName() throws Exception {
        dao.save(new Contact("Alice", "0701234567"));

        Contact result = dao.findByName("Alice");

        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }

    @Test
    void shouldReturnNullWhenContactNotFound() throws Exception {
        Contact result = dao.findByName("Unknown");

        assertNull(result);
    }

    @Test
    void shouldThrowDuplicateContactException() throws Exception {
        dao.save(new Contact("Alice", "0701234567"));

        assertThrows(DuplicateContactException.class,
                () -> dao.save(new Contact("Alice", "0709999999")));
    }

    @Test
    void shouldCreateFileIfMissing() {
        assertDoesNotThrow(() -> dao.findAll());
    }
}
