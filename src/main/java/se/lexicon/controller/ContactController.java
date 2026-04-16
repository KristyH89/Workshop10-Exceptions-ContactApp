package se.lexicon.controller;

import se.lexicon.data.ContactDAO;
import se.lexicon.model.Contact;
import se.lexicon.view.ContactView;
import se.lexicon.exception.ExceptionHandler;

import java.util.List;

public class ContactController {

    private final ContactDAO contactDAO;
    private final ContactView contactView;

    public ContactController(ContactDAO contactDAO, ContactView contactView) {
        this.contactDAO = contactDAO;
        this.contactView = contactView;
    }

    // display menu
    public void run() {
        boolean running = true;

        while (running) {
            contactView.displayMenu();
            String choice = contactView.getUserInput("Choose option");

            try {
                switch (choice) {
                    case "1":
                        addContact();
                        break;
                    case "2":
                        viewAllContacts();
                        break;
                    case "3":
                        findContact();
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        contactView.displayMessage("Invalid choice");
                }
            } catch (Exception e) {
                ExceptionHandler.handle(e, contactView);
            }
        }
    }

    // methods
    private void addContact() throws Exception {
        String name = contactView.getUserInput("Enter name");
        String phone = contactView.getUserInput("Enter phone (10 digits)");

        Contact contact = new Contact(name, phone);
        contactDAO.save(contact);

        contactView.displayMessage("Contact added!");
    }

    private void viewAllContacts() throws Exception {
        List<Contact> contacts = contactDAO.findAll();

        if (contacts.isEmpty()) {
            contactView.displayMessage("No contacts found");
        } else {
            contactView.displayContacts(contacts);
        }
    }

    private void findContact() throws Exception {
        String name = contactView.getUserInput("Enter name");

        Contact contact = contactDAO.findByName(name);

        if (contact == null) {
            contactView.displayMessage("Contact not found.");
        } else {
            contactView.displayMessage(contact.getName() + " - " + contact.getPhoneNumber());
        }
    }
}