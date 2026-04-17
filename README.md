![Java](https://img.shields.io/badge/Java-25.0.2-blue)
# 📇 Workshop : Contact App

A **Java console-based Contact Management System** built using **MVC architecture**, **file persistence**, and **exception handling**.

---

## 📝 Table of Contents

1. [🧠 MVC Design Pattern](#-mvc-design-pattern)
2. [✨ Features](#-features)
3. [📂 Folder Structure](#-folder-structure)
4. [🛠 Available Options](#-available-options)
5. [🗃 Data Structure](#-data-structure)
6. [✅ Input Validation](#-input-validation)
7. [🧪 JUnit Testing](#-junit-testing)
8. [🚀 How to Run](#-how-to-run)
9. [⚡ Expected Output :](#-expected-output-)
10. [📌 Workshop Document](#-workshop-document)
---

## 🧠 MVC Design Pattern

The project follows MVC (Model-View-Controller) architecture:

### 🧠 Model
- Represents data + validation
- Example: Contact

### 🖥 View
- Handles input/output
- Example: ContactView
- Shows menus and messages

### 🎮 Controller
- Connects Model and View
- Handles application logic
- Manages exception flow

### 🔄 Flow Diagram
```text
👤 User
↓
🖥 View
↓
🎮 Controller
↓
🧠 Model
↓
💾 File Storage (DAO)
↑
⚠ ExceptionHandler
```

### 🎯 Benefits of MVC
- Clean separation of responsibilities
- Easier debugging
- Easy to extend features
- Better testability

---
## 📌 Workshop Document

You can find the workshop description here:

[Workshop Document](Exception_Workshop.md)

---
## ✨ Features

📇 This Contact App provides a simple but structured contact management system using Java and MVC architecture.

### 🧠 Core Functionality
- ➕ Add new contacts with name and phone number
- 📋 View all saved contacts from file storage
- 🔍 Search for a contact by name
- 🚪 Exit the application safely

---

## 📂 Folder Structure

```text
contact-app-workshop/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── se/lexicon/
│   │   │       ├── Main.java
│   │   │       ├── model/
│   │   │       │    └── Contact.java
│   │   │       ├── data/
│   │   │       │    ├── ContactDAO.java
│   │   │       │    └── FileContactDAOImpl.java
│   │   │       ├── view/
│   │   │       │    └── ContactView.java
│   │   │       ├── controller/
│   │   │       │    └── ContactController.java
│   │   │       └── exception/
│   │   │            ├── ContactStorageException.java
│   │   │            ├── DuplicateContactException.java
│   │   │            └── ExceptionHandler.java
│   │   └── resources/
│   │        └── contacts.txt
│   │
│   └── test/java/se/lexicon/
│   ├── data/
│   │   └── FileContactDAOImpl.java
│   └── model/
│       └── ContactTest.java
├── pom.xml
└── README.md

```
---
## 🛠 Available Options

📇 Manage contacts efficiently in a console application:

- ➕ Add new contact
- 📋 View all contacts
- 🔍 Search contact by name
- 💾 File-based persistence (contacts.txt)
- 🚫 Duplicate contact prevention
- ✅ Input validation using regex
- ⚠️ Custom exception handling
- 🏗 Clean MVC architecture


---
## 🗃 Data Structure

Each contact is stored in ```contacts.txt ```:

```java 
String name;
String phoneNumber;
```
Example:
```text
Alice,0701234567
Bob,0739876543
Charlie,0761112233
```
---

## ✅ Input Validation

👤 Name
- Cannot be empty
- Cannot be null

📞 Phone Number
- Must be exactly 10 digits
- Regex validation:
```text
^\\d{10}$
```
❌ Invalid input throws:
- IllegalArgumentException

---

## 🧪 JUnit Testing

✅ ```ContactTest.java```

Tests:

- Valid contact creation
- Empty name validation
- Invalid phone number validation
- Non-numeric phone number rejection

✅ ```FileContactDAOImplTest.java```

Tests:

- Save contacts successfully
- Retrieve all contacts
- Find contact by name
- Return null when contact does not exist
- Prevent duplicate contacts
- Auto-create storage file

---
## 🚀 How to Run

1. Clone the repository.

2. Build with Maven:
    ```bash
    mvn clean package
    ```

3. Run the CLI:
   ```bash
    java -cp target/contact-app-workshop-1.0.jar
    ```

---

## ⚡ Expected Output :

```
=== Contact App ===
1. Add Contact
2. View All Contacts
3. Find Contact by Name
0. Exit
-------------------
Choose option 1
Enter name Alice
Enter phone (10 digits) 0701234567
Contact added!

=== Contact App ===
1. Add Contact
2. View All Contacts
3. Find Contact by Name
0. Exit
-------------------
Choose option
-------------------
Choose option 1
Enter name Bob
Enter phone (10 digits) 9876543
[!] Phone must be exactly 10 digits

```

---
