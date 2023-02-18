package student;

public class TriState extends Student {
    private String state;

    /**
     * This method creates a Student object using required objects  from other classes.
     *
     * @param profile         profile object from profile class that contains first name, last name and dob of student.
     * @param major           major enum object that contains major, school and major code.
     * @param creditCompleted integer number of credits completed by student.
     */
    public TriState(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }


    @Override
    public double tuitionDue(int creditsEnrolled) {
        int fullTime = 12;
        int ctTutionDiscount = 5000;
        int nyTutionDiscount = 4000;
        if ( state == "NY" && creditsEnrolled >= fullTime ) {
            return tuitionDue(creditsEnrolled) - nyTutionDiscount;
        } else if ( state == "CT" && creditsEnrolled >= fullTime ) {
            return tuitionDue(creditsEnrolled) - ctTutionDiscount;
        } else {
            return tuitionDue(creditsEnrolled);
        }
    }


    @Override
    public boolean isResident() {
        return false;
    }
}
