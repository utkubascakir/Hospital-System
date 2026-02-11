import classes.Patient;
import classes.Rendezvous;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RendezvousTest {

    @Test
    public void testRendezvousInitialization() {
        Patient patient = new Patient(123456789L, "John Doe", "password123");
        Date appointmentDate = new Date();
        Rendezvous rendezvous = new Rendezvous(appointmentDate, patient);
        assertEquals(appointmentDate, rendezvous.getDateTime());
        assertEquals(patient, rendezvous.getPatient());
    }

    @Test
    public void testToStringMethod() {
        Patient patient = new Patient(987654321L, "Jane Doe", "securePass");
        Date appointmentDate = new Date();
        Rendezvous rendezvous = new Rendezvous(appointmentDate, patient);
        String expected = "Patient: Jane Doe, Date: " + appointmentDate.toString();
        assertEquals(expected, rendezvous.toString());
    }

    @Test
    public void testNotNullFields() {
        Patient patient = new Patient(555555555L, "Alice Smith", "pass123");
        Date appointmentDate = new Date();
        Rendezvous rendezvous = new Rendezvous(appointmentDate, patient);
        assertNotNull(rendezvous.getDateTime());
        assertNotNull(rendezvous.getPatient());
    }
}
