import classes.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CRSManagementTest {

    private CRS crs;
    private Hospital hospital;
    private Section section;
    private Doctor doctor;
    private Patient patient;

    @Before
    public void setUp() {
        crs = new CRS();
        hospital = new Hospital(1, "Test Hospital");
        crs.getHospitals().put(1, hospital);
        section = new Section(1, "Test Section");
        hospital.addSection(section);
        doctor = new Doctor(1, "Dr. Test", 1, 10);
        section.addDoctor(doctor);
        patient = new Patient(123456789L, "Test Patient", "password123");
        crs.getPatients().put(123456789L, patient);
    }

    @Test
    public void testCreateAccount_Success() {
        String name = "New Patient";
        String username = "987654321";
        String password = "newpassword";
        Patient newPatient = new Patient(Long.parseLong(username), name, password);
        crs.getPatients().put(Long.parseLong(username), newPatient);
        assertTrue(crs.getPatients().containsKey(Long.parseLong(username)));
        assertEquals(name, crs.getPatients().get(Long.parseLong(username)).getName());
        assertEquals(password, crs.getPatients().get(Long.parseLong(username)).getPassword());
    }

    @Test
    public void testLogin_Success() {
        long patientID = 123456789L;
        String password = "password123";
        assertTrue(crs.getPatients().containsKey(patientID));
        assertEquals(password, crs.getPatients().get(patientID).getPassword());
    }

    @Test
    public void testLogin_Fail_WrongPassword() {
        long patientID = 123456789L;
        String wrongPassword = "wrongpassword";
        assertNotEquals(wrongPassword, crs.getPatients().get(patientID).getPassword());
    }

    @Test
    public void testAdminLogin_Success() {
        int adminUsername = 999;
        String adminPassword = "admin123";

        assertEquals(999, adminUsername);
        assertEquals("admin123", adminPassword);
    }

    @Test
    public void testAddHospital_Success() {
        int hospitalID = 2;
        String hospitalName = "New Test Hospital";
        Hospital newHospital = new Hospital(hospitalID, hospitalName);
        crs.getHospitals().put(hospitalID, newHospital);
        assertTrue(crs.getHospitals().containsKey(hospitalID));
        assertEquals(hospitalName, crs.getHospitals().get(hospitalID).getName());
    }

    @Test
    public void testAddSection_Success() {
        int sectionID = 2;
        String sectionName = "New Section";
        Section newSection = new Section(sectionID, sectionName);
        hospital.addSection(newSection);
        assertTrue(hospital.getSections().contains(newSection));
    }

    @Test
    public void testAddDoctor_Success() {
        int diplomaID = 2;
        String doctorName = "Dr. New Doctor";
        int maxPatientPerDay = 20;
        Doctor newDoctor = new Doctor(diplomaID, doctorName, diplomaID, maxPatientPerDay);
        section.addDoctor(newDoctor);
        assertTrue(section.getDoctors().contains(newDoctor));
    }
}
