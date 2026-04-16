package se.lexicon;

import se.lexicon.controller.ContactController;
import se.lexicon.data.FileContactDAOImpl;
import se.lexicon.exception.ContactStorageException;
import se.lexicon.exception.ExceptionHandler;
import se.lexicon.view.ContactView;

import java.nio.file.Paths;

/// Entry point for the Contact App.
///
/// The contacts file is created in the working
/// directory if it does not already exist.
public class App {
    public static void main(String[] args) {
        try {
            FileContactDAOImpl dao = new FileContactDAOImpl(Paths.get("contacts.txt"));
            ContactView view = new ContactView();
            ContactController controller = new ContactController(dao, view);

            controller.run();

        } catch (ContactStorageException e) {
            ExceptionHandler.handle(e);
            System.exit(1);
        }
    }
}
