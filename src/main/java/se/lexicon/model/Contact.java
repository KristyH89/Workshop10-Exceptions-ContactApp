package se.lexicon.model;

/// Represents a contact with a name and a 10-digit phone number.
public class Contact {

    private String name;
    private String phoneNumber;

    /// @throws IllegalArgumentException if name or phoneNumber is invalid
    public Contact(String name, String phoneNumber) {
        setName(name);
        setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    /// @throws IllegalArgumentException if `name` is null or blank
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        this.name = name.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /// @throws IllegalArgumentException if `phoneNumber` is null, blank, or not exactly 10 digits
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank.");
        }
        if (!phoneNumber.matches("^\\d{10}$")) {
            throw new IllegalArgumentException(
                    "Phone number must be exactly 10 digits (e.g. 0701234567). Got: " + phoneNumber);
        }
        this.phoneNumber = phoneNumber;
    }

    /// Returns `name,phoneNumber` — the format used by the file storage.
    @Override
    public String toString() {
        return name + "," + phoneNumber;
    }
}
