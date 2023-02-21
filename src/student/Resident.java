package student;

public class Resident extends Student{
    private int scholarship;

    /**
     * This method creates a Student object using required objects  from other classes.
     *
     * @param profile         profile object from profile class that contains first name, last name and dob of student.
     * @param major           major enum object that contains major, school and major code.
     * @param creditCompleted integer number of credits completed by student.
     */


    public Resident(Profile profile, Major major, int creditCompleted, int scholarship) {

        super(profile, major, creditCompleted);
        this.scholarship = scholarship;

    }

    public void setScholarship(int scholarship){
        this.scholarship = scholarship;
    }

    public int getCredits() {
        return super.getCreditCompleted();
    }

    @Override
    public double tuitionDue(int creditsEnrolled) {

                int tuition = 12536;
                int universityFee = 3268;
                double totalDue;
                int maxFullTime = 16;
                int partTime = 12;
                int perCreditRate = 404;

                if ( isValid(creditsEnrolled) && creditsEnrolled < partTime ) {
                    return ( perCreditRate * ( creditsEnrolled ) ) + ( universityFee * 0.8 );
                } else if ( isValid(creditsEnrolled) && creditsEnrolled < maxFullTime && creditsEnrolled > partTime ) {
                    return tuition + universityFee - this.scholarship;
        } else if ( isValid(creditsEnrolled) && creditsEnrolled > maxFullTime ) {
            return tuition + universityFee + ( perCreditRate * ( creditsEnrolled - maxFullTime ) ) - this.scholarship;
        }


        return 0;
    }

    @Override
    public boolean isResident() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " (resident)";
    }
}
