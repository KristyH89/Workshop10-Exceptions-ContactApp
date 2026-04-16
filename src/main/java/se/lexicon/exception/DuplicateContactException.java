package se.lexicon.exception;

/// Checked exception thrown when a contact with the same name already exists in storage.
public class DuplicateContactException extends Exception {

    public DuplicateContactException(String message) {
        super(message);
    }
}
