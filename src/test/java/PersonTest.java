import classes.Person;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PersonTest {

    @Test
    public void testGetName() {
        Person person = new Person(123456789L, "Jane Doe");
        assertEquals("Jane Doe", person.getName());
    }

    @Test
    public void testGetNationalId() {
        Person person = new Person(123456789L, "Jane Doe");
        assertEquals(123456789L, person.getNational_id());
    }
}

