package se.lexicon.exception;

public class ContactStorageException extends RuntimeException {

    public ContactStorageException() {
        super();
    }

    public ContactStorageException(String message) {
        super(message);
    }

    public ContactStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactStorageException(Throwable cause) {
        super(cause);
    }
}
