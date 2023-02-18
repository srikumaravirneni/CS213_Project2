package student;

public class NonResident extends Student{


    /**
     * This method creates a Student object using required objects  from other classes.
     *
     * @param profile         profile object from profile class that contains first name, last name and dob of student.
     * @param major           major enum object that contains major, school and major code.
     * @param creditCompleted integer number of credits completed by student.
     */
    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }


    @Override
    public double tuitionDue(int creditsEnrolled) {

        int tuition = 29737;
        int universityFee = 3268;
        double totalDue;
        int maxFullTime = 16;
        int partTime = 12;
        int perCreditRate = 966;

        if ( isValid(creditsEnrolled) && creditsEnrolled < partTime ) {
            return ( perCreditRate * ( creditsEnrolled ) ) + ( universityFee * 0.8 );
        } else if ( isValid(creditsEnrolled) && creditsEnrolled < maxFullTime && creditsEnrolled > partTime ) {
            return tuition + universityFee;
        } else if ( isValid(creditsEnrolled) && creditsEnrolled > maxFullTime ) {
            return tuition + universityFee + ( perCreditRate * ( creditsEnrolled - maxFullTime ) );
        }

        return 0;
    }


    @Override
    public boolean isResident() {
        return false;
    }
}
