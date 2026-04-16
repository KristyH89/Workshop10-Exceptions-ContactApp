package se.lexicon.exception;

/// Checked exception for file storage read/write failures.
public class ContactStorageException extends Exception {

    public ContactStorageException(String message) {
        super(message);
    }

    public ContactStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
