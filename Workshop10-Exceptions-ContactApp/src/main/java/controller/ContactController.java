package controller;

import data.ContactDAO;
import model.Contact;
import view.ContactView;
import exception.*;

import java.util.List;

public class ContactController {

    private ContactDAO contactDAO;
    private ContactView contactView;

    public ContactController(ContactDAO contactDAO, ContactView contactView) {
        this.contactDAO = contactDAO;
        this.contactView = contactView;
    }

    // display menu
    public void run() {
        boolean running = true;

        while (running) {
            contactView.displayMenu();
            String choice = contactView.getUserImput("Choose option");

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

    private void viewAllCOntacts() throws Exception {
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
