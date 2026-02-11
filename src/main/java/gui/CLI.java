package gui;

import classes.*;
import exceptions.DuplicateInfoException;
import exceptions.IDException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Long.parseLong;

public class CLI {

    private Scanner scan = new Scanner(System.in);
    private CRS crs;
    private Hashtable<Long, Patient> patients;
    private long loggedInPatientID;
    private final int adminUsername = 999;
    private final String adminPassword = "admin123";

    public CLI(CRS crs) {
        this.crs = crs;
        this.patients = crs.getPatients();
    }

    public void openCLI() {
        System.out.println("\n\n------------------CLINIC RESERVATION SYSTEM------------------");
        System.out.println("1-Login");
        System.out.println("2-Create Account");
        System.out.print("Your choice: ");
        int login_choice = scan.nextInt();
        scan.nextLine();
        switch (login_choice) {
            case 1:
                openLoginCLI();
                break;
            case 2:
                openCreateAccountCLI();
                openLoginCLI();
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public void openLoginCLI() {
        this.patients = crs.getPatients();
        System.out.print("Username(ID): ");
        long tmp_patientID = scan.nextLong();
        scan.nextLine();
        System.out.print("Password: ");
        String tmp_password = scan.nextLine();
        if (patients.containsKey(tmp_patientID)) {
            Patient loginPatient = patients.get(tmp_patientID);
            if (loginPatient.getPassword().equals(tmp_password)) {
                loggedInPatientID = tmp_patientID;
                System.out.println("Login Successful!, Welcome " + loginPatient.getName() + ".");
                openRendezvousCLI();
            }
            else System.out.println("Incorrect Password.");
        }
        else if (tmp_patientID == adminUsername && tmp_password.equals(adminPassword)) {
            System.out.println("Admin Login Successful.");
            openAdminCLI();
        }
        else System.out.println("User not found.");
    }

    public void openCreateAccountCLI() {
        this.patients = crs.getPatients();
        System.out.print("Name: ");
        String createName = scan.nextLine();
        System.out.print("Username(ID): ");
        String createUsername = scan.nextLine();
        System.out.print("Password: ");
        String createPassword = scan.nextLine();

        Long createdAccountID = parseLong(createUsername);
        try {
            if (patients.containsKey(createdAccountID)) {
                System.out.println("User with this ID already exists.");
                throw new DuplicateInfoException("Duplicate info error.");
            }
            else {
                Patient newPatient = new Patient(createdAccountID, createName, createPassword);
                patients.put(createdAccountID, newPatient);
                System.out.println("Account created successfully!");
                System.out.println("New patient is saved!" + newPatient);
                crs.saveTablesToDisk(Main.fullPath);
            }
        }
        catch (DuplicateInfoException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void openRendezvousCLI() {
        System.out.println("\n\n----------------------Reservation Screen---------------------\n");
        System.out.println("Hospital - Section - Doctor");
        for (Hospital hospital : crs.getHospitals().values()) {
            for (Section section : hospital.getSections()) {
                for (Doctor doctor : section.getDoctors()) {
                    System.out.print("(" + hospital.getId() + ") " + hospital.getName());
                    System.out.print(" --> (" + section.getId() + ") " + section.getName());
                    System.out.println(" --> (" + doctor.getDiploma_id() + ") " + doctor.getName() + ", Max Patient Per Day: " + doctor.getSchedule().getMaxPatientPerDay());
                }
            }
        }
        int selectedHospitalID = 0;
        int selectedSectionID = 0;
        boolean doctorFound = false;
        System.out.print("\nEnter the Diploma ID of the doctor to make a reservation: ");
        int selectedDiplomaID = scan.nextInt();
        for (Hospital hospital : crs.getHospitals().values()) {
            for (Section section : hospital.getSections()) {
                for (Doctor doctor : section.getDoctors()) {
                    if (selectedDiplomaID == doctor.getDiploma_id()) {
                        selectedHospitalID = hospital.getId();
                        selectedSectionID = section.getId();
                        Doctor selectedDoctor = doctor;
                        System.out.println("\n\n" + selectedDoctor.getName() + "'s Schedule:");
                        selectedDoctor.getSchedule().listSchedule();
                        doctorFound = true;
                        break;
                    }
                }
            }
        }
        if (!doctorFound) {
            System.out.println("This doctor does not exist.");
        }
        scan.nextLine();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date selectedDate = null;
        boolean success = false;

        while (!success) {
            System.out.print("Enter a date (format: yyyy/MM/dd HH:mm): ");
            String input = scan.nextLine();
            try {
                selectedDate = dateTimeFormat.parse(input);
                Date currentDate = new Date();
                if (selectedDate.before(currentDate)) {
                    System.out.println("The date cannot be in the past. Please choose a future date.");
                    continue;
                }
                System.out.println("Valid date entered: " + dateTimeFormat.format(selectedDate));
                success = crs.makeRendezvous(loggedInPatientID, selectedHospitalID, selectedSectionID, selectedDiplomaID, selectedDate);
                if (!success) {
                    System.out.println("Reservation unavailable, please choose another date.");
                }
            }
            catch (ParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
            catch (IDException e) {
                System.out.println("This ID does not exist.");
                e.printStackTrace();
            }
        }
        System.out.println("Reservation Successful.");
        crs.saveTablesToDisk(Main.fullPath);
    }

    public void openAdminCLI() {
        System.out.println("\n\n-------------------------Admin Panel-------------------------");
        System.out.println("Current Hospital-Section-Doctor Info:");
        System.out.println("Hospital - Section - Doctor");
        for (Hospital hospital : crs.getHospitals().values()) {
            for (Section section : hospital.getSections()) {
                for (Doctor doctor : section.getDoctors()) {
                    System.out.print("(" + hospital.getId() + ") " + hospital.getName());
                    System.out.print(" --> (" + section.getId() + ") " + section.getName());
                    System.out.println(" --> (" + doctor.getDiploma_id() + ") " + doctor.getName() + ", Max Patient Per Day: " + doctor.getSchedule().getMaxPatientPerDay());
                }
            }
        }

        System.out.println("\n\nADD OPERATIONS");
        System.out.print("Hospital Name: ");
        String addedHospitalName = scan.nextLine();

        System.out.print("Hospital ID: ");
        int addedHospitalID = scan.nextInt();
        scan.nextLine();

        Hospital hospital = crs.getHospitals().get(addedHospitalID);

        if (hospital == null) {
            hospital = new Hospital(addedHospitalID, addedHospitalName);
            crs.getHospitals().put(addedHospitalID, hospital);
            System.out.println("New hospital created: " + addedHospitalName);
        } else {
            System.out.println("Existing hospital found: " + hospital.getName());
        }

        System.out.print("Section Name: ");
        String addedSectionName = scan.nextLine();

        System.out.print("Section ID: ");
        int addedSectionID = scan.nextInt();
        scan.nextLine();

        Section existingSection = hospital.getSection(addedSectionID);
        if (existingSection != null) {
            System.out.println("Section with ID " + addedSectionID + " already exists. No new section was created.");
        } else {
            try {
                hospital.addSection(new Section(addedSectionID, addedSectionName));
                System.out.println("New section created: " + addedSectionName);
            } catch (DuplicateInfoException e) {
                System.out.println("Section with ID " + addedSectionID + " already exists. No new section was created.");
            }
        }

        System.out.print("Doctor Name: ");
        String addedDoctorName = scan.nextLine();

        System.out.print("Diploma ID: ");
        int addedDiplomaID = scan.nextInt();
        scan.nextLine();

        System.out.print("Max Patient Per Day: ");
        int maxPatientPerDay = scan.nextInt();
        scan.nextLine();

        Section section = hospital.getSection(addedSectionID);
        if (section == null) {
            System.out.println("Error: Section with ID " + addedSectionID + " does not exist. Doctor cannot be added.");
        } else {
            try {
                section.addDoctor(new Doctor(addedDiplomaID, addedDoctorName, addedDiplomaID, maxPatientPerDay));
                System.out.println("Doctor added: " + addedDoctorName + " (Diploma ID: " + addedDiplomaID + ")");
            } catch (DuplicateInfoException e) {
                System.out.println("Doctor with diploma ID " + addedDiplomaID + " already exists in this section.");
            }
        }
        System.out.println("\nAdding operations completed successfully.");
    }
}