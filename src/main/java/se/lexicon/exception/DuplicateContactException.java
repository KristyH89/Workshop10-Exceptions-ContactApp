package se.lexicon.exception;

public class DuplicateContactException extends RuntimeException {

    public DuplicateContactException() {
        super();
    }

    public DuplicateContactException(String message) {
        super(message);
    }

    public DuplicateContactException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateContactException(Throwable cause) {
        super(cause);
    }
}