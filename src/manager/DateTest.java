package manager;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class is a test class that tests isValid() method in date class.
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class DateTest {

    /**
     * Test condition that tests isValid() method for an invalid year.
     */
    @Test
    public void test_isValid_invalid() {
        Date dateTest1 = new Date("11/21/800");
        assertFalse(dateTest1.isValid());
    }

    /**
     * Test condition that tests isValid() method for a non leap year day entered.
     */
    @Test
    public void test_isValid_notleap() {
        Date dateTest2 = new Date("2/29/2003");
        assertFalse(dateTest2.isValid());
    }

    /**
     * Test condition that tests isValid() method for invalid month entered.
     */
    @Test
    public void test_isValid_invalidMonth() {
        Date dateTest3 = new Date("13/31/2003");
        assertFalse(dateTest3.isValid());
    }

    /**
     * Test condition that tests isValid() method for not thirty-one days month.
     */
    @Test
    public void test_isValid_notThirtyOne() {
        Date dateTest4 = new Date("4/31/2003");
        assertFalse(dateTest4.isValid());
    }

    /**
     * Test condition that tests isValid() method for negative date entered.
     */
    @Test
    public void test_isValid_notNegative() {
        Date dateTest5 = new Date("-1/2/2007");
        assertFalse(dateTest5.isValid());
    }

    /**
     * Test condition that tests isValid() method for correct date entered.
     */
    @Test
    public void test_isValid_correctDate() {
        Date dateTest6 = new Date("1/2/2007");
        assertTrue(dateTest6.isValid());
    }

    /**
     * Test condition that tests isValid() method for correct date entered.
     */
    @Test
    public void test_isValid_correctDate2() {
        Date dateTest7 = new Date("5/1/1996");
        assertTrue(dateTest7.isValid());
    }

}