package student;
import java.text.DecimalFormat;

/**
 * This class extends Student class to create a Resident student object and also calculates their tuition
 * based on credits.
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class Resident extends Student{
    private int scholarship; //the scholarship amount the student has received

    /**
     * This method creates a resident student object.
     * @param profile profile of resident student object.
     * @param major major of resident student object being created.
     * @param creditCompleted number of credits completed by the resident student object.
     * @param scholarship  scholarship awarded to student.
     */
    public Resident(Profile profile, Major major, int creditCompleted, int scholarship) {

        super(profile, major, creditCompleted);
        this.scholarship = scholarship;
    }

    /**
     * Assigning scholarship to the object.
     * @param scholarship scholarship value to be set in parameter.
     */
    public void setScholarship(int scholarship){
        this.scholarship = scholarship;
    }

    /**
     * Calculates the amount of tuition due for a specific resident student based credits enrolled and scholarship
     * received.
     * @param creditsEnrolled the number of credits a student has enrolled.
     * @return tuition due for a resident student based on credits enrolled after scholarship has been deducted.
     */
    @Override
    public double tuitionDue(int creditsEnrolled) {

        int tuition = 12536;
        int universityFee = 3268;
        double totalDue;
        int maxFullTime = 16;
        int partTime = 12;
        int perCreditRate = 404;

        DecimalFormat dFormat = new DecimalFormat( "###.##" );

        if ( isValid(creditsEnrolled) && creditsEnrolled < partTime ) {
            return ( perCreditRate * ( creditsEnrolled ) ) + ( universityFee * 0.8 );
        } else if ( isValid( creditsEnrolled ) && creditsEnrolled <= maxFullTime && creditsEnrolled > partTime ) {
            return tuition + universityFee - this.scholarship;
        } else if ( isValid( creditsEnrolled ) && creditsEnrolled > maxFullTime ) {
            double total = tuition + universityFee + ( perCreditRate * ( creditsEnrolled - maxFullTime ) ) - this.scholarship;
            return total;
        }
        return 0;
    }

    /**
     * Returns the status of the student.
     * @return resident as the student is resident.
     */
    @Override
    public String getStatus() {
        return "Resident";
    }

    /**
     * Returns the resident status of a student.
     * @return true as the student is resident.
     */
    @Override
    public boolean isResident() {
        return true;
    }

    /**
     * This method overrides the superclass toString() method and prints the string representation of resident student.
     * @return resident student object along with the status that he is resident.
     */
    @Override
    public String toString() {
        return super.toString() + "(resident)";
    }
}