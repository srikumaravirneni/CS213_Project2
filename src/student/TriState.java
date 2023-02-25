package student;

import java.text.DecimalFormat;

public class TriState extends NonResident {
    private String state;

    /**
     * This method creates a Student object using required objects  from other classes.
     *
     * @param profile         profile object from profile class that contains first name, last name and dob of student.
     * @param major           major enum object that contains major, school and major code.
     * @param creditCompleted integer number of credits completed by student.
     */
    public TriState(Profile profile, Major major, int creditCompleted, String state) {
        super(profile, major, creditCompleted);
        this.state = state;
    }


    @Override
    public double tuitionDue(int creditsEnrolled) {
        int fullTime = 12;
        int ctTuitionDiscount = 5000;
        int nyTuitionDiscount = 4000;
        DecimalFormat dFormat = new DecimalFormat("###.##");
        double total;

        if ( state.equals("NY") && creditsEnrolled >= fullTime ) {
            total = tuitionDue(creditsEnrolled) - nyTuitionDiscount;
            return Double.parseDouble(dFormat.format(total));
        } else if ( state.equals("CT") && creditsEnrolled >= fullTime ) {
            total = tuitionDue(creditsEnrolled) - ctTuitionDiscount;
            return Double.parseDouble(dFormat.format(total));
        } else {
            total = tuitionDue(creditsEnrolled);
            return Double.parseDouble(dFormat.format(total));
        }
    }

    @Override
    public String getStatus() {

        return "Tri-state " + state;

    }
    @Override
    public boolean isResident() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "(tri-state:" + this.state + ")";
    }
}
