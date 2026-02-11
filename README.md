# 🏥 Clinic Reservation System (CRS)

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-Testing-green.svg)](https://junit.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)]()

A comprehensive **Hospital Management & Appointment Booking System** developed in Java. This project demonstrates advanced Object-Oriented Programming (OOP) principles, offering both a robust **Graphical User Interface (GUI)** using Swing and a lightweight **Command Line Interface (CLI)** for system administration.

---

## 🚀 Key Features

### 🖥️ Graphical User Interface (Patient Module)
The GUI is designed for patients to easily manage their appointments with a user-friendly experience.
* **User Authentication:** Secure login and registration system with ID/Password validation.
* **Dynamic Filtering:** Real-time cascading selection for **Hospital**, **Section**, and **Doctor**. (Selecting a hospital updates sections; selecting a section updates doctors).
* **Appointment Scheduling:**
    * View specific doctor schedules and constraints (Max patients per day).
    * Date selection with validation (prevents past dates).
    * Conflict detection to prevent double-booking.
* **Data Persistence:** Automatic saving of patient and appointment data to local storage.

### 💻 Command Line Interface (Admin Module)
The CLI serves as the administrative backbone of the system, allowing for complete system configuration.
* **Admin Dashboard:** Accessed via secure credentials.
* **System Configuration:**
    * **Add Hospitals:** Create new hospital entities.
    * **Add Sections:** Assign medical departments to hospitals.
    * **Add Doctors:** Register new doctors with specific diploma IDs and daily quotas.
* **Hierarchy Visualization:** View the complete tree structure of the medical system (Hospital -> Section -> Doctor).

---

## 🛠️ Tech Stack & Architecture

* **Language:** Java 21
* **Build Tool:** Maven
* **UI Framework:** Java Swing (AWT/Swing)
* **Testing:** JUnit 4
* **Data Structure:** `Hashtable` for O(1) access to records, `LinkedList` for appointment history.
* **Design Patterns:**
    * **MVC Principle:** Separation of logic (`CRS`, `Doctor`, `Patient`) from views (`GUI`, `CLI`).
    * **Exception Handling:** Custom exceptions like `DuplicateInfoException` and `IDException` for robust error management.

---

## ⚙️ Installation & Usage

### Prerequisites
* Java Development Kit (JDK) 21 or higher
* Maven

### 1. Clone the Repository
```bash
git clone [https://github.com/utkubascakir/Hospital-Management-System.git](https://github.com/utkubascakir/Hospital-Management-System.git)
cd Hospital-Management-System
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
You can run the application directly from the Main class. The system typically asks to choose between GUI or CLI mode upon startup.

## 🔐 Credentials for Testing
To explore the full capabilities of the system, you can use the following pre-defined credentials or create your own:

## 👤 Patient (Standard User)
Action: Click "Create Account" on the login screen.

ID: Any unique numeric ID (e.g., 12345)

Password: Any secure password.

🛡️ Admin (System Manager - CLI Mode)
Username (ID): 999

Password: admin123

Note: Use these credentials to add new hospitals and doctors to the system.

🧪 Testing
The project includes comprehensive unit tests to ensure system integrity. Run the tests using Maven:

Bash
mvn test
This will execute tests for:

HospitalStructureTest: Validates the hierarchy and data entry logic.

RendezvousServiceTest: Verifies the appointment booking algorithms and constraints.

📂 Project Structure
Plaintext
src/
├── main/java/
│   ├── classes/       # Core Logic (CRS, Doctor, Hospital, etc.)
│   ├── exceptions/    # Custom Exception Classes
│   └── gui/           # UI Implementations (GUI.java, CLI.java)
└── test/java/         # JUnit Test Suites
Note: This project was developed as a final assignment for the Object-Oriented Programming course to demonstrate proficiency in Java application development and software architecture.