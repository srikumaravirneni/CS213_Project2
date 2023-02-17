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
    public Resident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
        this.scholarship = scholarship;

    }
}
