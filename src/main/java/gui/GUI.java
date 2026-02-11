package gui;

import classes.*;
import exceptions.DuplicateInfoException;
import exceptions.IDException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Hashtable;

import static java.lang.Long.parseLong;

public class GUI {

    private CRS crs;
    private Hashtable<Long, Patient> patients;
    private long loggedInPatientID;

    public GUI(CRS crs) {
        this.crs = crs;
        this.patients = crs.getPatients();
        openCreateLoginScreen();
    }

    private void openCreateLoginScreen() {
        JFrame loginFrame = new JFrame("Clinic Reservation System");
        loginFrame.setSize(1000, 700);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username(ID):");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    Long id = parseLong(username);
                    if (patients.containsKey(id)) {
                        Patient patient = patients.get(id);
                        if (patient.getPassword().equals(password)) {
                            JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
                            loggedInPatientID = id;
                            loginFrame.dispose();
                            openRendezvousScreen();
                        } else {
                            JOptionPane.showMessageDialog(loginFrame, "Incorrect password.");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(loginFrame, "User not found.");
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid ID format.");
                }
                catch (DuplicateInfoException dex) {
                    JOptionPane.showMessageDialog(loginFrame, "This patient already exist.");
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateAccountWindow();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(104,190,213,255));
        panel.add(loginButton, gbc);

        gbc.gridy = 3;
        panel.add(createAccountButton, gbc);
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setBackground(new Color(46,166,201,255));
        loginFrame.add(panel, BorderLayout.CENTER);
        loginFrame.setVisible(true);
    }

    private void openCreateAccountWindow() {
        JFrame createAccountFrame = new JFrame("Create Account");
        createAccountFrame.setSize(500, 400);
        createAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createAccountFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel usernameLabel = new JLabel("Username (ID):");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton submitButton = new JButton("Create");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    Long id = parseLong(username);
                    if (patients.containsKey(id)) {
                        JOptionPane.showMessageDialog(createAccountFrame, "User with this ID already exists.");
                        throw new DuplicateInfoException("Duplicate info error.");
                    } else {
                        Patient newPatient = new Patient(id, name, password);
                        patients.put(id, newPatient);
                        JOptionPane.showMessageDialog(createAccountFrame, "Account created successfully!");

                        crs.saveTablesToDisk(Main.fullPath);

                        createAccountFrame.dispose();
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(createAccountFrame, "Invalid ID format.");
                }
                catch (DuplicateInfoException dex) {
                    JOptionPane.showMessageDialog(createAccountFrame, dex.getMessage());
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        createAccountFrame.add(nameLabel, gbc);

        gbc.gridx = 1;
        createAccountFrame.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        createAccountFrame.add(usernameLabel, gbc);

        gbc.gridx = 1;
        createAccountFrame.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        createAccountFrame.add(passwordLabel, gbc);

        gbc.gridx = 1;
        createAccountFrame.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(46,166,201,255));
        createAccountFrame.add(submitButton, gbc);

        createAccountFrame.setVisible(true);
    }

    private void openRendezvousScreen() {
        JFrame rendezvousFrame = new JFrame("Reservation System");
        rendezvousFrame.setSize(1000, 700);
        rendezvousFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rendezvousFrame.setLayout(new BorderLayout(10, 20));

        JLabel label = new JLabel("Reservation Screen", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        rendezvousFrame.add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 100, 100));

        JLabel hospitalLabel = new JLabel("Choose Hospital:");
        JComboBox<String> hospitalComboBox = new JComboBox<>();
        hospitalComboBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        crs.getHospitals().forEach((id, hospital) -> hospitalComboBox.addItem(id + " - " + hospital.getName()));
        panel.add(hospitalLabel);
        panel.add(hospitalComboBox);

        JLabel sectionLabel = new JLabel("Choose Section:");
        JComboBox<String> sectionComboBox = new JComboBox<>();
        sectionComboBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        panel.add(sectionLabel);
        panel.add(sectionComboBox);

        JLabel doctorLabel = new JLabel("Choose Doctor:");
        JComboBox<String> doctorComboBox = new JComboBox<>();
        doctorComboBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(doctorLabel);
        panel.add(doctorComboBox);

        JLabel schedule = new JLabel("Doctor's Schedule");
        JTextArea doctorSchedule = new JTextArea();
        doctorSchedule.setEditable(false);
        doctorSchedule.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(schedule);
        panel.add(doctorSchedule);

        JLabel dateLabel = new JLabel("Choose Date:");
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(dateSpinner, "yyyy/MM/dd HH:mm");
        dateSpinner.setEditor(timeEditor);
        panel.add(dateLabel);
        panel.add(dateSpinner);

        JButton confirmButton = new JButton("Confirm Reservation");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int hospitalID = Integer.parseInt(hospitalComboBox.getSelectedItem().toString().split(" - ")[0]);
                    int sectionID = Integer.parseInt(sectionComboBox.getSelectedItem().toString().split(" - ")[0]);
                    int diplomaID = Integer.parseInt(doctorComboBox.getSelectedItem().toString().split(" - ")[0]);
                    Date desiredDate = (Date) dateSpinner.getValue();

                    Date currentDate = new Date();
                    if (desiredDate.before(currentDate)) {
                        JOptionPane.showMessageDialog(rendezvousFrame, "The date cannot be in the past. Please choose a future date.");
                        return;
                    }

                    boolean success = crs.makeRendezvous(loggedInPatientID, hospitalID, sectionID, diplomaID, desiredDate);
                    if (success) {
                        JOptionPane.showMessageDialog(rendezvousFrame, "Reservation successful!");
                        Patient loggedinPatient = crs.getPatients().get(loggedInPatientID);
                        Rendezvous addedRendezvous = crs.getHospitals().get(hospitalID).getSection(sectionID).getDoctor(diplomaID).getSchedule().getRendezvous(loggedinPatient,desiredDate);
                        doctorSchedule.append("\n");
                        doctorSchedule.append(String.valueOf(addedRendezvous));
                        crs.saveTablesToDisk(Main.fullPath);
                    } else {
                        JOptionPane.showMessageDialog(rendezvousFrame, "Reservation unavailable. Please choose another date.");
                    }
                }
                catch (IDException idex) {
                    JOptionPane.showMessageDialog(rendezvousFrame, "Invalid ID. Please check the IDs and try again.");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(rendezvousFrame, "Error: " + ex.getMessage());
                }
            }
        });
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBackground(new Color(60, 245, 88));
        panel.add(confirmButton);

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(46,166,201,255));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openCreateLoginScreen();
                    rendezvousFrame.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(rendezvousFrame, "Error: " + ex.getMessage());
                }
            }
        });
        panel.add(backButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rendezvousFrame.dispose();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(rendezvousFrame, "Error: " + ex.getMessage());
                }
            }
        });
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(250, 63, 63));
        panel.add(exitButton);

        rendezvousFrame.add(panel, BorderLayout.CENTER);

        rendezvousFrame.setVisible(true);

        hospitalComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sectionComboBox.removeAllItems();
                doctorComboBox.removeAllItems();
                Object selectedItem = hospitalComboBox.getSelectedItem();

                if (selectedItem == null) {
                    return;
                }

                int selectedHospitalID = Integer.parseInt(hospitalComboBox.getSelectedItem().toString().split(" - ")[0]);
                Hospital selectedHospital = crs.getHospitals().get(selectedHospitalID);
                selectedHospital.getSections().forEach(section -> sectionComboBox.addItem(section.getId() + " - " + section.getName()));
            }
        });

        sectionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctorComboBox.removeAllItems();
                Object selectedHospitalItem = hospitalComboBox.getSelectedItem();
                Object selectedSectionItem = sectionComboBox.getSelectedItem();

                if (selectedHospitalItem == null || selectedSectionItem == null) {
                    return;
                }

                int selectedHospitalID = Integer.parseInt(hospitalComboBox.getSelectedItem().toString().split(" - ")[0]);
                int selectedSectionID = Integer.parseInt(sectionComboBox.getSelectedItem().toString().split(" - ")[0]);
                Section selectedSection = crs.getHospitals().get(selectedHospitalID).getSection(selectedSectionID);
                selectedSection.getDoctors().forEach(doctor -> doctorComboBox.addItem(doctor.getDiploma_id() + " - " + doctor.getName()));
            }
        });

        doctorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctorSchedule.setText("");
                Object selectedDoctorItem = doctorComboBox.getSelectedItem();
                if (selectedDoctorItem == null) {
                    return;
                }
                int selectedHospitalID = Integer.parseInt(hospitalComboBox.getSelectedItem().toString().split(" - ")[0]);
                int selectedSectionID = Integer.parseInt(sectionComboBox.getSelectedItem().toString().split(" - ")[0]);
                int selectedDoctorDiplomaID = Integer.parseInt(selectedDoctorItem.toString().split(" - ")[0]);
                Doctor selectedDoctor = crs.getHospitals().get(selectedHospitalID).getSection(selectedSectionID).getDoctor(selectedDoctorDiplomaID);
                doctorSchedule.append("\nMAX PATIENT PER DAY: " + selectedDoctor.getSchedule().getMaxPatientPerDay() + "\n");
                selectedDoctor.getSchedule().writeSchedule(doctorSchedule);
            }
        });
    }
}

