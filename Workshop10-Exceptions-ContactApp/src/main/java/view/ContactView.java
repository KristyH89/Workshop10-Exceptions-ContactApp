package view;

import java.util.List;
import java.util.Scanner;
import model.Contact;

// I'm waiting on the Contact class so things will not work yet.

public class ContactView {

    private Scanner scanner = new Scanner(System.in);

    public String getUserInput(String prompt) {
        System.out.println(prompt + ": ");
        return scanner.nextLine();
    }

    public void displayMenu() {
        System.out.println("\n=== Contact app ===");
        System.out.println("1. Add Contact");
        System.out.println("2. View All Contacts");
        System.out.println("3. Find Contact by Name");
        System.out.println("0. Exit");
    }

    public void displayContacts(List<Contact> contacts) {
        for (Contact c : contacts) {
            System.out.println(c.getName() + "- " + c.getPhoneNumber());

        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError (String message) {
        System.out.println("ERROR: " + message);
    }
}
