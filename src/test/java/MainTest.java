import classes.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Hashtable;

import static org.junit.Assert.*;

public class MainTest {

    private CRS crs;
    private static final String TEST_FILE_PATH = System.getProperty("user.dir") + "/test_tables.dat";

    @Before
    public void setUp() {
        crs = new CRS();
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @After
    public void tearDown() {
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testInitializeDefaultData_FileNotExist() {
        File testFile = new File(TEST_FILE_PATH);
        assertFalse(testFile.exists());
        Main.initializeDefaultData(crs, TEST_FILE_PATH);
        assertTrue(testFile.exists());
        Hashtable<Integer, Hospital> hospitals = crs.getHospitals();
        assertEquals(3, hospitals.size());
        Hospital devletHastanesi = hospitals.get(34000);
        assertNotNull(devletHastanesi);
        assertEquals("State Hospital", devletHastanesi.getName());
        String fileContent = readFile(TEST_FILE_PATH);
        assertNotNull(fileContent);
        assertTrue(fileContent.length() > 0);
    }

    @Test
    public void testInitializeDefaultData_FileExists() {
        File testFile = new File(TEST_FILE_PATH);
        try {
            testFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Main.initializeDefaultData(crs, TEST_FILE_PATH);
        assertTrue(testFile.exists());
        Hashtable<Integer, Hospital> hospitals = crs.getHospitals();
        assertEquals(3, hospitals.size());
        String fileContent = readFile(TEST_FILE_PATH);
        assertNotNull(fileContent);
        assertTrue(fileContent.length() > 0);
    }

    private String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
