import classes.Doctor;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DoctorTest {

    @Test
    public void testGetDiplomaId() {
        Doctor doctor = new Doctor(123456789L, "Dr. Smith", 98765, 10);
        assertEquals(98765, doctor.getDiploma_id());
    }

    @Test
    public void testGetSchedule() {
        Doctor doctor = new Doctor(123456789L, "Dr. Smith", 98765, 10);
        assertEquals(10, doctor.getSchedule().getMaxPatientPerDay());
    }
}
