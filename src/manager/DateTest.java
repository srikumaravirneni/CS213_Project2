package manager;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void test_isValid_invalid() {
        Date dateTest1 = new Date("11/21/800");
        assertFalse(dateTest1.isValid());
    }

    @Test
    public void test_isValid_notleap() {
        Date dateTest2 = new Date("2/29/2003");
        assertFalse(dateTest2.isValid());
    }

    @Test
    public void test_isValid_invalidMonth() {
        Date dateTest3 = new Date("13/31/2003");
        assertFalse(dateTest3.isValid());
    }

    @Test
    public void test_isValid_notThirtyOne() {
        Date dateTest4 = new Date("4/31/2003");
        assertFalse(dateTest4.isValid());
    }

    @Test
    public void test_isValid_notNegative() {
        Date dateTest5 = new Date("-1/2/2007");
        assertFalse(dateTest5.isValid());
    }

    @Test
    public void test_isValid_correctDate() {
        Date dateTest6 = new Date("1/2/2007");
        assertTrue(dateTest6.isValid());
    }

    @Test
    public void test_isValid_correctDate2() {
        Date dateTest7 = new Date("5/1/1996");
        assertTrue(dateTest7.isValid());
    }

}