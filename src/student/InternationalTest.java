package student;
import manager.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class is a test class that tests tuitionDue() method in international class.
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class InternationalTest {

    /**
     * Test condition that tests tuitionDue() method for study abroad international student.
     */
    @Test
    public void test_tuition_isStudyAbroad() {
        Profile profile = new Profile("Alice", "Lee", new Date("1/2/2007"));
        International student = new International(profile, Major.CS, 12, true);
        assertEquals(5918.00, student.tuitionDue(12),0.0);
    }

    /**
     * Test condition that tests tuitionDue() method for international student.
     */
    @Test
    public void test_tuition_notStudyAbroad() {
        Profile profile = new Profile("Alice", "Lee", new Date("5/1/1996"));
        International student = new International(profile, Major.EE, 12, false);
        assertEquals(35655.00, student.tuitionDue(12),0.0);
    }

}