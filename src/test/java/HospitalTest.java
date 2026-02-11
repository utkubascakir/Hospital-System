import classes.Hospital;
import classes.Section;
import exceptions.DuplicateInfoException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HospitalTest {

    private Hospital hospital;

    @Before
    public void setUp() {
        hospital = new Hospital(1, "Test Hospital");
    }

    @Test
    public void testHospitalConstructor() {
        assertEquals(1, hospital.getId());
        assertEquals("Test Hospital", hospital.getName());
    }

    @Test
    public void testAddSection() {
        Section section1 = new Section(101, "Cardiology");
        Section section2 = new Section(102, "Neurology");

        hospital.addSection(section1);
        hospital.addSection(section2);

        assertEquals(2, hospital.getSections().size());
        assertTrue(hospital.getSections().contains(section1));
        assertTrue(hospital.getSections().contains(section2));
    }

    @Test(expected = DuplicateInfoException.class)
    public void testAddDuplicateSectionThrowsException() {
        Section section1 = new Section(101, "Cardiology");

        hospital.addSection(section1);
        hospital.addSection(section1);
    }

    @Test
    public void testGetSectionById() {
        Section section1 = new Section(101, "Cardiology");
        hospital.addSection(section1);

        Section retrievedSection = hospital.getSection(101);
        assertNotNull(retrievedSection);
        assertEquals("Cardiology", retrievedSection.getName());
    }

    @Test
    public void testGetSectionByName() {
        Section section1 = new Section(101, "Cardiology");
        hospital.addSection(section1);

        Section retrievedSection = hospital.getSection("Cardiology");
        assertNotNull(retrievedSection);
        assertEquals(101, retrievedSection.getId());
    }

    @Test
    public void testGetSectionByInvalidId() {
        Section retrievedSection = hospital.getSection(999);
        assertNull(retrievedSection);
    }

    @Test
    public void testGetSectionByInvalidName() {
        Section retrievedSection = hospital.getSection("Nonexistent Section");
        assertNull(retrievedSection);
    }

    @Test
    public void testListSections() {
        Section section1 = new Section(101, "Cardiology");
        Section section2 = new Section(102, "Neurology");

        hospital.addSection(section1);
        hospital.addSection(section2);
        hospital.listSections();
    }
}
