package student;

import java.text.DecimalFormat;

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
        DecimalFormat dFormat = new DecimalFormat("###.##");
        double total;

        if ( isValid(creditsEnrolled) && creditsEnrolled < partTime ) {

            total = ( perCreditRate * ( creditsEnrolled ) ) + ( universityFee * 0.8 );
            return Double.parseDouble(dFormat.format(total));
        } else if ( isValid(creditsEnrolled) && creditsEnrolled < maxFullTime && creditsEnrolled > partTime ) {
            total =  tuition + universityFee;
            return Double.parseDouble(dFormat.format(total));
        } else if ( isValid(creditsEnrolled) && creditsEnrolled > maxFullTime ) {
            total = tuition + universityFee + ( perCreditRate * ( creditsEnrolled - maxFullTime ) );
            return Double.parseDouble(dFormat.format(total));
        }

        return 0;
    }

    @Override
    public String getStatus() {
        return "Non-Resident";
    }

    @Override
    public boolean isResident() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "(non-resident)";
    }
}
