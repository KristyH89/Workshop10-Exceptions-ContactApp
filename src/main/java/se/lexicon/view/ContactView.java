package se.lexicon.view;

import se.lexicon.model.Contact;

import java.util.List;
import java.util.Scanner;

public class ContactView {

    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public void displayMenu() {
        System.out.println("\n=== Contact App ===");
        System.out.println("1. Add Contact");
        System.out.println("2. View All Contacts");
        System.out.println("3. Find Contact by Name");
        System.out.println("0. Exit");
    }

    public void displayContacts(List<Contact> contacts) {
        for (Contact c : contacts) {
            System.out.println(c.getName() + " - " + c.getPhoneNumber());
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }
}