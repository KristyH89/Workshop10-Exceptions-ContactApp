package se.lexicon;

import se.lexicon.controller.ContactController;
import se.lexicon.data.FileContactDAOImpl;
import se.lexicon.view.ContactView;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        try {
            FileContactDAOImpl dao = new FileContactDAOImpl(Path.of("contacts.txt"));
            ContactView view = new ContactView();

            ContactController controller = new ContactController(dao, view);
            controller.run();

        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
        }
    }
}