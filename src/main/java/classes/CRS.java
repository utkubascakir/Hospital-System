package classes;

import exceptions.IDException;

import java.io.*;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;

public class CRS {
    private Hashtable<Long, Patient> patients;
    private Hashtable<Integer, Hospital> hospitals;
    private LinkedList<Rendezvous> rendezvous;

    public CRS() {
        this.patients = new Hashtable<>();
        this.hospitals = new Hashtable<>();
        this.rendezvous = new LinkedList<>();
    }

    public Hashtable<Long, Patient> getPatients() { return patients; }
    public Hashtable<Integer, Hospital> getHospitals() { return hospitals; }
    public LinkedList<Rendezvous> getRendezvous() { return rendezvous; }

    public void checkID(Object obj, String name, long id) throws IDException {
        if (obj == null) {
            throw new IDException(name + " with ID " + id + " not found.");
        }
    }

    public synchronized boolean makeRendezvous(long patientID, int hospitalID, int sectionID, int diplomaID, Date desiredDate) {
        Patient tmp_patient = patients.get(patientID);
        checkID(tmp_patient, "Patient", patientID);

        Hospital tmp_hospital = hospitals.get(hospitalID);
        checkID(tmp_hospital, "Hospital", hospitalID);

        Section tmp_section = tmp_hospital.getSection(sectionID);
        checkID(tmp_section, "Section", sectionID);

        Doctor tmp_doctor = tmp_section.getDoctor(diplomaID);
        checkID(tmp_doctor, "Doctor", diplomaID);

        if (tmp_doctor.getSchedule().addRendezvous(tmp_patient, desiredDate)) {
            System.out.println("Rendezvous made successfully.");
            Rendezvous added_rendezvous = tmp_doctor.getSchedule().getRendezvous(tmp_patient, desiredDate);
            rendezvous.add(added_rendezvous);
            return true;
        } else {
            return false;
        }
    }

    public synchronized void saveTablesToDisk(String fullPath) {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fullPath));
            writer.writeObject(patients);
            writer.writeObject(hospitals);
            writer.writeObject(rendezvous);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while opening the file.");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized void loadTablesToDisk(String fullPath) {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fullPath));
            this.patients = (Hashtable<Long, Patient>) reader.readObject();
            this.hospitals = (Hashtable<Integer, Hospital>) reader.readObject();
            this.rendezvous = (LinkedList<Rendezvous>) reader.readObject();
            reader.close();
            System.out.println("Data successfully loaded from disk.");
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with empty data.");
            this.patients = new Hashtable<>();
            this.hospitals = new Hashtable<>();
            this.rendezvous = new LinkedList<>();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading data from disk.");
            e.printStackTrace();
        }
    }
}
