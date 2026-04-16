package se.lexicon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void shouldCreateValidContact() {
        Contact contact = new Contact("Alice", "0701234567");

        assertEquals("Alice", contact.getName());
        assertEquals("0701234567", contact.getPhoneNumber());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("", "0701234567"));
    }

    @Test
    void shouldThrowExceptionWhenPhoneIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("Alice", "123"));
    }

    @Test
    void shouldThrowExceptionWhenPhoneContainsLetters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("Alice", "07012abc67"));
    }
}