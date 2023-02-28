package student;
import java.text.DecimalFormat;

/**
 * This class extends Student class to create a NonResident student object and also to calculate their tuition
 * based on credits
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class NonResident extends Student {

    /**
     * THis method creates a non-resident student object.
     * @param profile profile of non-resident student object.
     * @param major major of non-resident student object being created.
     * @param creditCompleted number of credits completed by the non-resident student object.
     */
    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    /**
     * Calculates the amount of tuition due for a specific non-resident student based on number of credits enrolled.
     * @param creditsEnrolled the number of credits a student has enrolled.
     * @return tuition due for a non-resident student based on credits enrolled.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {

        int tuition = 29737;
        int universityFee = 3268;
        double totalDue;
        int maxFullTime = 16;
        int partTime = 12;
        int perCreditRate = 966;
        DecimalFormat dFormat = new DecimalFormat( "###.##" );
        double total;

        if ( isValid(creditsEnrolled) && creditsEnrolled < partTime ) {
            total = ( perCreditRate * ( creditsEnrolled ) ) + ( universityFee * 0.8 );
            return total;
        } else if ( isValid( creditsEnrolled ) && creditsEnrolled <= maxFullTime && creditsEnrolled > partTime ) {
            total =  tuition + universityFee;
            return total;
        } else if ( isValid( creditsEnrolled ) && creditsEnrolled > maxFullTime ) {
            total = tuition + universityFee + ( perCreditRate * ( creditsEnrolled - maxFullTime ) );
            return total;
        }

        return 0;
    }

    /**
     * Returns the status of the student.
     * @return Non-Resident
     */
    @Override
    public String getStatus() {
        return "Non-Resident";
    }

    /**
     * Returns the resident status of a student
     * @return false as international student is non-resident student.
     */
    @Override
    public boolean isResident() {
        return false;
    }

    /**
     * This method overrides the superclass toString() method and prints the string representation of international
     * student status based on whether he is study abroad or not.
     * @return non-resident student object along with his status of being non-resident.
     */
    @Override
    public String toString() {
        return super.toString() + "(non-resident)";
    }
}