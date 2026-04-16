package se.lexicon.exception;
import se.lexicon.view.ContactView;

public class ExceptionHandler {

    public static void handle(Exception e, ContactView view) {

        if (e instanceof DuplicateContactException) {
            view.displayError("Contact already exists.");

        } else if (e instanceof ContactStorageException) {
            view.displayError("Error saving or loading contacts.");

        } else if (e instanceof IllegalArgumentException) {
            view.displayError(e.getMessage());

        } else {
            view.displayError("Unexpected error occurred.");
        }
    }

}
