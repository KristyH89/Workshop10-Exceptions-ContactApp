package se.lexicon.controller;

import se.lexicon.data.ContactDAO;
import se.lexicon.exception.ContactStorageException;
import se.lexicon.exception.DuplicateContactException;
import se.lexicon.exception.ExceptionHandler;
import se.lexicon.model.Contact;
import se.lexicon.view.ContactView;

import java.util.List;

/// Coordinates between [ContactView] (view) and [ContactDAO] (model) following
/// the MVC pattern.
///
/// The Controller:
/// - Drives the main application loop.
/// - Reads user input via the View and map it to DAO operations.
/// - Catches all exceptions thrown by the Model/DAO layer, log them via
///   [ExceptionHandler], and instruct the View to display an error message.
///
/// ## MVC Design Pattern
/// **Model** (Contact, ContactDAO) — owns the data and behaviours.
/// It does not care how data is displayed or how the user interacts
/// with the application.
///
/// **View** (ContactView) — owns all I/O (`Scanner`, `System.out`).
/// It only knows how to display things; it contains no business logic.
/// I have only done MVC in web apps before, so this is my first time applying it to a console app. 
/// Therefore i dont know if i have sufficiently separated the concerns of the view and controller, but i have done my best to keep the view as dumb as possible and let the controller handle logic.
///
/// **Controller** (this class) — the orchestrator. 
/// It tells the Model what to do and the View what to show.

public class ContactController {

    private final ContactDAO contactDAO;
    private final ContactView contactView;

    /// @throws IllegalArgumentException if either argument is null
    public ContactController(ContactDAO contactDAO, ContactView contactView) {
        if (contactDAO == null) throw new IllegalArgumentException("contactDAO must not be null.");
        if (contactView == null) throw new IllegalArgumentException("contactView must not be null.");
        this.contactDAO = contactDAO;
        this.contactView = contactView;
    }

    /// Starts the main application loop. Exits when the user enters `0`.
    public void run() {
        boolean running = true;
        while (running) {
            contactView.displayMenu();
            String choice = contactView.getUserInput("Enter choice:");

            switch (choice) {
                case "1" -> listContacts();
                case "2" -> addContact();
                case "3" -> findContact();
                case "0" -> {
                    contactView.displayMessage("Goodbye!");
                    running = false;
                }
                default -> contactView.displayMessage("Invalid option. Please enter 0–3.");
            }
        }
    }

    //region Private handler methods — each maps one menu option to DAO + View calls

    /// Retrieves all contacts from the DAO and hands them to the View for display.
    private void listContacts() {
        try {
            List<Contact> contacts = contactDAO.findAll();
            contactView.displayContacts(contacts);
        } catch (ContactStorageException e) {
            ExceptionHandler.handle(e);
            contactView.displayError(e.getMessage());
        }
    }

    /// Prompts the user for a name and phone number, validates them by
    /// constructing a [Contact], then saves it via the DAO.
    ///
    /// Exception flow:
    /// - [IllegalArgumentException] — invalid name/phone format (unchecked).
    /// - [DuplicateContactException] — name already exists (checked).
    /// - [ContactStorageException] — persistence failure (checked).
    ///
    /// All three are caught here, logged, and displayed to the user.
    private void addContact() {
        try {
            String name = contactView.getUserInput("Enter name:");
            String phone = contactView.getUserInput("Enter phone number (10 digits):");

            // Constructor validation may throw IllegalArgumentException
            Contact contact = new Contact(name, phone);
            contactDAO.save(contact);
            contactView.displayMessage("Contact saved successfully.");

        } catch (IllegalArgumentException e) {
            // Unchecked — invalid input supplied by the user
            ExceptionHandler.handle(e);
            contactView.displayError(e.getMessage());
        } catch (DuplicateContactException e) {
            // Checked — contact with this name already exists
            ExceptionHandler.handle(e);
            contactView.displayError(e.getMessage());
        } catch (ContactStorageException e) {
            // Checked — I/O failure
            ExceptionHandler.handle(e);
            contactView.displayError(e.getMessage());
        }
    }

    /// Prompts the user for a name and displays the matching contact if found.
    private void findContact() {
        try {
            String name = contactView.getUserInput("Enter name to search:");
            Contact contact = contactDAO.findByName(name);
            contactView.displayMessage(
                    "Found: " + contact.getName() + " | " + contact.getPhoneNumber());
        } catch (ContactStorageException e) {
            ExceptionHandler.handle(e);
            contactView.displayError(e.getMessage());
        }
    }

    //endregion
}
