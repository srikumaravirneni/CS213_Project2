package student;

public class International extends NonResident {
    private boolean isStudyAbroad;

    /**
     * This method creates a Student object using required objects  from other classes.
     *
     * @param profile         profile object from profile class that contains first name, last name and dob of student.
     * @param major           major enum object that contains major, school and major code.
     * @param creditCompleted integer number of credits completed by student.
     */
    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad) {
        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }


    @Override
    public double tuitionDue(int creditsEnrolled) {

        int tuition = 29737;
        int universityFee = 3268;
        double totalDue;
        int maxFullTime = 16;
        int fullTime = 12;
        int perCreditRate = 966;
        int insuranceFees = 2650;


        if ( !isStudyAbroad ) {
            if ( isValid(creditsEnrolled) && creditsEnrolled < fullTime ) {
                return ( perCreditRate * ( creditsEnrolled ) ) + ( universityFee * 0.8 );
            } else if ( isValid(creditsEnrolled) && creditsEnrolled < maxFullTime && creditsEnrolled > fullTime ) {
                return tuition + universityFee + insuranceFees;
            } else if ( isValid(creditsEnrolled) && creditsEnrolled > maxFullTime ) {
                return tuition + universityFee + ( perCreditRate * ( creditsEnrolled - maxFullTime ) ) + insuranceFees;
            }
        } else if ( isStudyAbroad ) {

        }


        return 0;
    }

    @Override
    public boolean isResident() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + " (international)";
    }
}
