# 🏥 Clinic Reservation System (CRS)

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-Testing-green.svg)](https://junit.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)]()

A hospital appointment booking system developed in Java as a university project. The system provides a Swing-based GUI for patients to book appointments and a CLI for administrators to manage the hospital structure.

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
* Maven (optional - see alternative methods below)

### 1. Clone the Repository
```bash
git clone https://github.com/utkubascakir/Hospital-System.git
cd Hospital-System
```

### 2. Installation Options

#### Option A: Using Maven (Recommended)

**If you don't have Maven installed:**

**Windows:**
1. Download Maven from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
2. Extract to `C:\Program Files\Apache\maven`
3. Add `C:\Program Files\Apache\maven\bin` to System PATH
4. Verify: `mvn --version`

**macOS/Linux:**
```bash
# macOS (using Homebrew)
brew install maven

# Linux (Ubuntu/Debian)
sudo apt-get install maven

# Verify installation
mvn --version
```

**Build the project:**
```bash
mvn clean install
```

#### Option B: Using IDE (IntelliJ IDEA / Eclipse)

1. Open the project folder in your IDE
2. The IDE will automatically detect the `pom.xml` and download dependencies
3. Right-click on the Main class and select "Run"

**Note:** Using an IDE is the easiest method if you don't want to install Maven separately.

### 3. Run the Application
You can run the application directly from the Main class. The system typically asks to choose between GUI or CLI mode upon startup.

---

## 🔐 Credentials for Testing

To explore the full capabilities of the system, you can use the following pre-defined credentials or create your own:

### 👤 Patient (Standard User)
* **Action:** Click "Create Account" on the login screen.
* **ID:** Any unique numeric ID (e.g., 12345)
* **Password:** Any secure password.

### 🛡️ Admin (System Manager - CLI Mode)
* **Username (ID):** 999
* **Password:** admin123

**Note:** Use these credentials to add new hospitals and doctors to the system.

---

## 🧪 Testing

The project includes comprehensive unit tests to ensure system integrity. Run the tests using Maven:

```bash
mvn test
```

This will execute tests for:
* **Core System Components:** CRS, Doctor, Patient, Hospital, Section
* **Business Logic:** Appointment scheduling, data validation, conflict detection
* **Data Management:** Person registry, schedule management

All test files can be found in the `src/test/java/` directory.

---

## 📂 Project Structure

```
src/
├── main/java/
│   ├── classes/       # Core Logic (CRS, Doctor, Hospital, etc.)
│   ├── exceptions/    # Custom Exception Classes
│   └── gui/           # UI Implementations (GUI.java, CLI.java)
└── test/java/         # JUnit Test Suites
```

---

## 📝 Note

This project was developed as a final assignment for the Object-Oriented Programming course to demonstrate proficiency in Java application development and software architecture.