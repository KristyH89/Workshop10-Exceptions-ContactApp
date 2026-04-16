package se.lexicon;

import controller.ContactController;
import data.FileCOntactDAOImpl;
import view.ContactView;

import java.nio.file.Path;

public class Main {
    static <FileContactDAOImpl> void main(String[] args) {

        FileContactDAOImpl dao = new FIleCOntactDAOImpl(Path.of("contacts.txt"));
        ContactView view = new ContactView();

        ContactController controller = new ContactController(dao, view);
        controller.run();
    }

}

// I have to check everyhing as a whole.