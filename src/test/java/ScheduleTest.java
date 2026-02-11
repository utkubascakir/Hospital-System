import classes.Patient;
import classes.Rendezvous;
import classes.Schedule;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class ScheduleTest {

    @Test
    public void testScheduleInitialization() {
        Schedule schedule = new Schedule(5);

        assertEquals(5, schedule.getMaxPatientPerDay());
        assertNotNull(schedule.getSessions());
        assertTrue(schedule.getSessions().isEmpty());
    }

    @Test
    public void testAddRendezvous() {
        Schedule schedule = new Schedule(2);
        Patient patient1 = new Patient(123456789L, "John Doe", "password123");
        Patient patient2 = new Patient(987654321L, "Jane Smith", "password456");

        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JANUARY, 15, 10, 0);
        Date date1 = cal.getTime();

        cal.set(2025, Calendar.JANUARY, 15, 11, 0);
        Date date2 = cal.getTime();

        boolean result1 = schedule.addRendezvous(patient1, date1);
        assertTrue(result1);

        boolean result2 = schedule.addRendezvous(patient2, date2);
        assertTrue(result2);

        assertEquals(2, schedule.getSessions().size());
    }

    @Test
    public void testAddRendezvousSameTime() {
        Schedule schedule = new Schedule(5);
        Patient patient1 = new Patient(123456789L, "John Doe", "password123");
        Patient patient2 = new Patient(987654321L, "Jane Smith", "password456");

        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JANUARY, 15, 10, 0);
        Date date = cal.getTime();

        boolean result1 = schedule.addRendezvous(patient1, date);
        assertTrue(result1);

        boolean result2 = schedule.addRendezvous(patient2, date);
        assertFalse(result2);
    }

    @Test
    public void testAddRendezvousMaxLimitReached() {
        Schedule schedule = new Schedule(2);
        Patient patient1 = new Patient(123456789L, "John Doe", "password123");
        Patient patient2 = new Patient(987654321L, "Jane Smith", "password456");
        Patient patient3 = new Patient(111222333L, "Alice Brown", "password789");

        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JANUARY, 15, 10, 0);
        Date date1 = cal.getTime();

        cal.set(2025, Calendar.JANUARY, 15, 11, 0);
        Date date2 = cal.getTime();

        cal.set(2025, Calendar.JANUARY, 15, 12, 0);
        Date date3 = cal.getTime();

        assertTrue(schedule.addRendezvous(patient1, date1));
        assertTrue(schedule.addRendezvous(patient2, date2));
        assertFalse(schedule.addRendezvous(patient3, date3));
    }

    @Test
    public void testGetRendezvous() {
        Schedule schedule = new Schedule(5);
        Patient patient = new Patient(123456789L, "John Doe", "password123");

        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JANUARY, 15, 10, 0);
        Date date = cal.getTime();

        schedule.addRendezvous(patient, date);

        Rendezvous rendezvous = schedule.getRendezvous(patient, date);
        assertNotNull(rendezvous);
        assertEquals(patient, rendezvous.getPatient());
        assertEquals(date, rendezvous.getDateTime());

        cal.set(2025, Calendar.JANUARY, 16, 10, 0);
        Date nonExistentDate = cal.getTime();
        Rendezvous notFoundRendezvous = schedule.getRendezvous(patient, nonExistentDate);
        assertNull(notFoundRendezvous);
    }

    @Test
    public void testGetSessions() {
        Schedule schedule = new Schedule(3);
        assertNotNull(schedule.getSessions());
        assertTrue(schedule.getSessions().isEmpty());
    }
}
