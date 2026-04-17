package se.lexicon.view;

import se.lexicon.model.Contact;

import java.util.List;
import java.util.Scanner;

/// Handles all user-facing input and output for the Contact App.
public class ContactView {

    private final Scanner scanner = new Scanner(System.in);

    /// Displays a prompt and returns the user's trimmed input.

    /// @param prompt the message to display before waiting for input
    /// @return the trimmed string entered by the user
    public String getUserInput(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine().trim();
    }

    /// Displays the main navigation menu.
    public void displayMenu() {
        System.out.println("\n=== Contact App ===");
        System.out.println("1. Add Contact");
        System.out.println("2. View All Contacts");
        System.out.println("3. Find Contact by Name");
        System.out.println("0. Exit");
        System.out.println("-------------------");
    }

    /// Displays a formatted list of contacts.
    ///
    /// If the list is empty, a "No contacts found." message is shown instead.
    ///
    /// @param contacts the list of contacts to display; must not be null
    public void displayContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        System.out.println();
        System.out.printf("%-30s %s%n", "Name", "Phone Number");
        System.out.println("-".repeat(45));
        for (Contact c : contacts) {
            System.out.printf("%-30s %s%n", c.getName(), c.getPhoneNumber());
        }
        System.out.println("-".repeat(45));
    }

    /// Displays an informational message to the user.
    ///
    /// @param message the message to display
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /// Displays a user-friendly error message.
    ///
    /// Error messages are prefixed with `[!]` to make them visually
    /// distinct from normal output without using `System.err` in the view
    /// ([ExceptionHandler][se.lexicon.exception.ExceptionHandler] handles raw logging).
    ///
    /// @param message the error description to display
    public void displayError(String message) {
        System.out.println("[!] " + message);
    }
}
