package manager;
import org.junit.Test;
import student.*;
import static org.junit.Assert.*;

/**
 * This class is a test class that tests add() method in roster class for international and tristate student.
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class RosterTest {

    /**
     * Test condition that tests add() method for valid international student.
     */
    @Test
    public void testAddValidInternational() {
        Roster roster = new Roster();
        Student student = new International(new Profile("Mill", "Doctor", new Date("5/1/1996")),
                Major.CS, 30, false);
        boolean added = roster.add(student);
        assertTrue(added);
    }

    /**
     * Test condition that tests add() method for invalid international student.
     */
    @Test
    public void testAddInvalidInternational() {
        Roster roster = new Roster();
        Student student = new International(new Profile("Mill", "Doctor", new Date("-5/1/1996")),
                Major.CS, -30, false);
        boolean added = roster.add(student);
        assertFalse(added);
    }

    /**
     * Test condition that tests add() method for valid tristate student.
     */
    @Test
    public void testAddValidTriState() {
        Roster roster = new Roster();
        Student student = new TriState(new Profile("John", "Deal", new Date("5/1/1996")),
                Major.BAIT, 64, "NY");
        boolean added = roster.add(student);
        assertTrue(added);
    }

    /**
     * Test condition that tests add() method for invalid tristate student.
     */
    @Test
    public void testAddInvalidTriState() {
        Roster roster = new Roster();
        Student student = new TriState(new Profile("Jane", "Miller", new Date("5/1/1996")),
                Major.BAIT, 60, "TX");
        boolean added = roster.add(student);
        assertFalse(added);
    }
}