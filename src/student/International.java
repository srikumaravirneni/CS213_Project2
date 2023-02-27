package student;
import java.text.DecimalFormat;

/**
 * This class extends NonResident class to create an International student object and also to calculate their tuition
 * based on credits and whether they are study abroad or not.
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class International extends NonResident {
    private final boolean isStudyAbroad; //variable to store if the student is in study abroad program

    /**
     * This method creates an international student object.
     * @param profile profile of international student object being created.
     * @param major major of international student object being created.
     * @param creditCompleted number of credits completed by the internation student object being created.
     * @param isStudyAbroad true/false based on whether the student is study abroad student or not.
     */
    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad) {
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * Calculates the amount of tuition due for a specific international student based on Study abroad status and
     * credits enrolled.
     * @param creditsEnrolled the number of credits a student has enrolled.
     * @return tuition due for an international student based on credits enrolled.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {

        int tuition = 29737;
        int universityFee = 3268;
        double total;
        int maxFullTime = 16;
        int fullTime = 12;
        int perCreditRate = 966;
        int insuranceFees = 2650;

        DecimalFormat dFormat = new DecimalFormat("###.##" );

        if ( !isStudyAbroad ) {
            if ( isValid( creditsEnrolled ) && creditsEnrolled < fullTime ) {
                total = ( perCreditRate * ( creditsEnrolled ) ) + ( universityFee * 0.8 );
                return Double.parseDouble( dFormat.format( total ) );
            } else if ( isValid( creditsEnrolled ) && creditsEnrolled < maxFullTime && creditsEnrolled >= fullTime ) {
                total = tuition + universityFee + insuranceFees;
                return Double.parseDouble( dFormat.format( total ) );
            } else if ( isValid( creditsEnrolled ) && creditsEnrolled > maxFullTime ) {
                total = tuition + universityFee + ( perCreditRate * ( creditsEnrolled - maxFullTime ) ) + insuranceFees;
                return Double.parseDouble( dFormat.format( total ) );
            }
        } else if ( isStudyAbroad ) {
            return Double.parseDouble( dFormat.format( universityFee+insuranceFees ) );
        }
        return 0;
    }

    /**
     * Returns the status of the student if he is an international or international study abroad.
     * @return International student study abroad if he is a study abroad student otherwise international.
     */
    @Override
    public String getStatus() {

        if ( isStudyAbroad ) {
            return "International studentstudy abroad";
        }
        return "International student";
    }

    /**
     * Returns the resident status of a student.
     * @return false as international student is not resident student.
     */
    @Override
    public boolean isResident() {
        return false;
    }

    /**
     * This method overrides the superclass toString() method and prints the string representation of international.
     * student along with status based on whether he is study abroad or not.
     * @return international: returns international student object along with whether he is international study abroad
     * or just international.
     */
    @Override
    public String toString() {

        if ( isStudyAbroad ) {
            return super.toString() + "(international:study abroad)";
        }

        return super.toString() + "(international)";

    }
}
