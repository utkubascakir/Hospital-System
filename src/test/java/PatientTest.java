import classes.Patient;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PatientTest {

    @Test
    public void testGetPassword() {
        Patient patient = new Patient(123456789L, "John Doe", "password123");
        assertEquals("password123", patient.getPassword());
    }
}

