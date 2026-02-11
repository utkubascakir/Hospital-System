package classes;

import java.io.Serializable;
import java.util.Date;

public class Rendezvous implements Serializable {
    private static final long serialVersionUID = 2L;
    private Date dateTime;
    private Patient patient;

    public Rendezvous(Date dateTime, Patient patient) {
        this.dateTime = dateTime;
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Patient: " + patient.getName() + ", Date: " + getDateTime();
    }

    public Date getDateTime() { return dateTime; }
    public Patient getPatient() { return patient; }
}
