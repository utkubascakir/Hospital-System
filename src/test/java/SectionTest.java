import classes.Doctor;
import classes.Section;
import exceptions.DuplicateInfoException;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class SectionTest {

    @Test
    public void testSectionInitialization() {
        Section section = new Section(1, "Cardiology");

        assertEquals(1, section.getId());
        assertEquals("Cardiology", section.getName());
        assertNotNull(section.getDoctors());
        assertTrue(section.getDoctors().isEmpty());
    }

    @Test
    public void testAddDoctor() {
        Section section = new Section(1, "Cardiology");

        Doctor doctor1 = new Doctor(123456789L, "Dr. Smith", 101, 5);
        Doctor doctor2 = new Doctor(987654321L, "Dr. Doe", 102, 10);

        section.addDoctor(doctor1);
        section.addDoctor(doctor2);

        LinkedList<Doctor> doctors = section.getDoctors();
        assertEquals(2, doctors.size());
        assertEquals(doctor1, doctors.get(0));
        assertEquals(doctor2, doctors.get(1));
    }

    @Test(expected = DuplicateInfoException.class)
    public void testAddDuplicateDoctor() {
        Section section = new Section(1, "Cardiology");

        Doctor doctor1 = new Doctor(123456789L, "Dr. Smith", 101, 5);

        section.addDoctor(doctor1);
        section.addDoctor(doctor1);
    }

    @Test
    public void testGetDoctor() {
        Section section = new Section(1, "Neurology");

        Doctor doctor1 = new Doctor(123456789L, "Dr. Alice", 201, 8);
        Doctor doctor2 = new Doctor(987654321L, "Dr. Bob", 202, 7);

        section.addDoctor(doctor1);
        section.addDoctor(doctor2);

        Doctor foundDoctor = section.getDoctor(201);
        assertNotNull(foundDoctor);
        assertEquals(doctor1, foundDoctor);

        Doctor notFoundDoctor = section.getDoctor(999);
        assertNull(notFoundDoctor);
    }

    @Test
    public void testToString() {
        Section section = new Section(1, "Oncology");
        String expectedOutput = "Section Name: Oncology\nSectionID: 1";

        assertEquals(expectedOutput, section.toString());
    }

    @Test
    public void testListDoctors() {
        Section section = new Section(1, "Pediatrics");

        Doctor doctor1 = new Doctor(123456789L, "Dr. Green", 301, 10);
        Doctor doctor2 = new Doctor(987654321L, "Dr. Brown", 302, 12);

        section.addDoctor(doctor1);
        section.addDoctor(doctor2);

        LinkedList<Doctor> doctors = section.getDoctors();
        assertEquals(2, doctors.size());
        assertEquals(doctor1, doctors.get(0));
        assertEquals(doctor2, doctors.get(1));
    }
}
