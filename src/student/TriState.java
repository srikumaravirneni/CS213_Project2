package student;
import java.text.DecimalFormat;

/**
 * This class extends NonResident class to create a NonResident tristate student object.
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class TriState extends NonResident {
    private final String state; //the neighboring state the student is from

    /**
     * This method creates a NonResident tristate student object.
     * @param profile profile of tristate student object being created.
     * @param major major of tristate student object being created.
     * @param creditCompleted number of credits completed by the tristate student object being created.
     * @param state the tristate student is from.
     */
    public TriState(Profile profile, Major major, int creditCompleted, String state) {
        super(profile, major, creditCompleted);
        this.state = state;
    }

    /**
     * Calculates the amount of tuition due for a specific tristate student based on study abroad status and
     * credits enrolled.
     * @param creditsEnrolled the number of credits a student has enrolled.
     * @return tuition due for the tristate student based on credits enrolled.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {
        int fullTime = 12;
        int ctTuitionDiscount = 5000;
        int nyTuitionDiscount = 4000;
        DecimalFormat dFormat = new DecimalFormat( "###.##" );
        double total;

        if ( state.equals("NY") && creditsEnrolled >= fullTime ) {
            total = tuitionDue( creditsEnrolled ) - nyTuitionDiscount;
            return Double.parseDouble( dFormat.format( total ) );
        } else if ( state.equals("CT") && creditsEnrolled >= fullTime ) {
            total = tuitionDue( creditsEnrolled ) - ctTuitionDiscount;
            return Double.parseDouble( dFormat.format( total ) );
        } else {
            total = tuitionDue( creditsEnrolled );
            return Double.parseDouble( dFormat.format( total ) );
        }
    }

    /**
     * Returns the status of the student.
     * @return tristate with state name.
     */
    @Override
    public String getStatus() {
        return "Tri-state " + state;
    }

    /**
     * Returns the resident status of a student.
     * @return false as tristate student is not resident student.
     */
    @Override
    public boolean isResident() {
        return false;
    }

    /**
     * This method overrides the superclass toString() method and prints the string representation of tristate student
     * along with the state the student is from.
     * @return returns tristate student object along with tri-state status and the state student is from.
     */
    @Override
    public String toString() {
        return super.toString() + "(tri-state:" + this.state + ")";
    }
}
