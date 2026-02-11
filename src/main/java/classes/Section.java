package classes;

import exceptions.DuplicateInfoException;

import java.io.Serializable;
import java.util.LinkedList;

public class Section implements Serializable {
    private static final long serialVersionUID = 5L;
    private final int id;
    private String name;
    private LinkedList<Doctor> doctors = new LinkedList<>();

    public Section(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public LinkedList<Doctor> getDoctors() { return doctors; }
    @Override
    public String toString() {
        return "Section Name: " + getName() + "\nSectionID: " + getId();
    }

    public void listDoctors() {
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    public Doctor getDoctor(int diploma_id) {
        Doctor tmp_doctor = null;
        for (Doctor doctor : doctors) {
            if (doctor.getDiploma_id() == diploma_id) {
                tmp_doctor = doctor;
            }
        }
        if (tmp_doctor == null) {
            System.out.println("This doctor does not exist.");
        }
        return tmp_doctor;
    }

    public synchronized void addDoctor(Doctor doctor) throws DuplicateInfoException {
        for (Doctor existing_dr : doctors) {
            if (existing_dr.getDiploma_id() == doctor.getDiploma_id() && existing_dr.getName().equals(doctor.getName())) {
                throw new DuplicateInfoException("This doctor already exists: " + doctor) ;
            }
        }
        doctors.add(doctor);
    }
}
