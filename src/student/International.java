package student;

import java.text.DecimalFormat;

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
        double total;
        int maxFullTime = 16;
        int fullTime = 12;
        int perCreditRate = 966;
        int insuranceFees = 2650;

        DecimalFormat dFormat = new DecimalFormat("###.##");

        if ( !isStudyAbroad ) {
            if ( isValid(creditsEnrolled) && creditsEnrolled < fullTime ) {
                total = ( perCreditRate * ( creditsEnrolled ) ) + ( universityFee * 0.8 );
                return Double.parseDouble(dFormat.format(total));
            } else if ( isValid(creditsEnrolled) && creditsEnrolled < maxFullTime && creditsEnrolled > fullTime ) {
                total = tuition + universityFee + insuranceFees;
                return Double.parseDouble(dFormat.format(total));
            } else if ( isValid(creditsEnrolled) && creditsEnrolled > maxFullTime ) {
                total = tuition + universityFee + ( perCreditRate * ( creditsEnrolled - maxFullTime ) ) + insuranceFees;
                return Double.parseDouble(dFormat.format(total));
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

        if (isStudyAbroad) {
            return super.toString() + " (international: study abroad)";
        }

        return super.toString() + " (international)";

    }
}
