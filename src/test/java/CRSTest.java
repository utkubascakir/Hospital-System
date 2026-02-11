import classes.*;
import exceptions.IDException;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.*;

public class CRSTest {
    private CRS crs;

    @Before
    public void setUp() {
        crs = new CRS();
        Patient patient = new Patient(123456789L, "John Doe", "password123");
        crs.getPatients().put(patient.getNational_id(), patient);
        Hospital hospital = new Hospital(1, "City Hospital");
        Section section = new Section(101, "Cardiology");
        Doctor doctor = new Doctor(123456789L, "Dr. Smith", 555, 5);
        section.addDoctor(doctor);
        hospital.addSection(section);
        crs.getHospitals().put(hospital.getId(), hospital);
    }

    @Test
    public void testMakeRendezvousSuccess() {
        boolean success = crs.makeRendezvous(123456789L, 1, 101, 555, new Date());
        assertTrue(success);
        assertEquals(1, crs.getRendezvous().size());
    }

    @Test(expected = IDException.class)
    public void testMakeRendezvousInvalidPatient() {
        crs.makeRendezvous(987654321L, 1, 101, 555, new Date());
    }

    @Test(expected = IDException.class)
    public void testMakeRendezvousInvalidHospital() {
        crs.makeRendezvous(123456789L, 99, 101, 555, new Date());
    }

    @Test(expected = IDException.class)
    public void testMakeRendezvousInvalidSection() {
        crs.makeRendezvous(123456789L, 1, 999, 555, new Date());
    }

    @Test(expected = IDException.class)
    public void testMakeRendezvousInvalidDoctor() {
        crs.makeRendezvous(123456789L, 1, 101, 999, new Date());
    }

    @Test
    public void testSaveAndLoadTablesToDisk() {
        String filePath = "test_crs_data.ser";
        crs.saveTablesToDisk(filePath);
        CRS loadedCrs = new CRS();
        loadedCrs.loadTablesToDisk(filePath);
        assertEquals(crs.getPatients().size(), loadedCrs.getPatients().size());
        assertEquals(crs.getHospitals().size(), loadedCrs.getHospitals().size());
        assertEquals(crs.getRendezvous().size(), loadedCrs.getRendezvous().size());
        File file = new File(filePath);
        if (file.exists()) {
            assertTrue(file.delete());
        }
    }
}
