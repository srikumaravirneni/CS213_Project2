package student;

/**
 * Handles the Student object which contains profile object, major enum and credit completed.
 * Student object is the object that is used for roster management.
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public abstract class Student implements Comparable<Student> {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int NEGATIVE_ONE = -1;
    private final Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    /**
     * This method creates a Student object using required objects  from other classes.
     *
     * @param profile         profile object from profile class that contains first name, last name and dob of student.
     * @param major           major enum object that contains major, school and major code.
     * @param creditCompleted integer number of credits completed by student.
     */
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    /**
     * Setting credit completed by student.
     * @param creditCompleted the number of credits completed.
     */
    public void setCreditCompleted(int creditCompleted) {
        this.creditCompleted = creditCompleted;
    }

    /**
     * checks if credits are valid.
     * @param creditEnrolled the number of credits enrolled by student.
     * @return true if creditsEnrolled are correct, false otherwise.
     */
    public boolean isValid(int creditEnrolled) {
        return creditEnrolled >= 3 && creditEnrolled <= 24;
    }

    /**
     * Calculates the amount of tuition due for student based on number of credits enrolled.
     * @param creditsEnrolled the number of credits a student has enrolled.
     * @return tuition due
     */
    public abstract double tuitionDue(int creditsEnrolled);

    /**
     * Returns the residency status of a student.
     * @return boolean value for residency.
     */
    public abstract boolean isResident(); //polymorphism

    /**
     * Returns the year parameter for the object.
     *
     * @return int representing the year.
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Returns the major parameter for the object.
     *
     * @return Major object representing the major.
     */
    public Major getMajor() {
        return this.major;
    }

    /**
     * Assigning major to the object.
     *
     * @param major major value to be set in parameter.
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * Returns the creditsCompleted parameter for the object.
     *
     * @return int representing the number of creditCompleted.
     */
    public int getCreditCompleted() {
        return creditCompleted;
    }
    /**
     * The method helps to figure out whether the student is freshman, sophomore, junior or senior based on credits
     * completed.
     *
     * @param creditsCompleted the number of credits a student has completed.
     * @return the standing of student i.e. freshman, sophomore, junior, senior.
     */
    public String standing(int creditsCompleted) {
        int freshmanLimit = 30;
        int sophLimit = 60;
        int juniorLimit = 90;

        if (creditsCompleted < freshmanLimit) {
            return "Freshman";
        } else if (creditsCompleted < sophLimit) {
            return "Sophomore";
        } else if (creditsCompleted < juniorLimit) {
            return "Junior";
        } else {
            return "Senior";
        }
    }

    /**
     * This method overrides the compareTo method in the object superclass and is used to compare two student objects.
     *
     * @param other the object to be compared.
     * @return returns value greater than 0 if student value is less than other, zero if student values are equal and
     * value less than zero if student value is less than other object.
     */
    @Override
    public int compareTo(Student other) {

        int profileCompare = profile.compareTo(other.getProfile());
        if (profileCompare == ZERO) {
            int majorCompare = getMajor().compareTo(other.getMajor());
            if (majorCompare == ZERO) {
                if (getCreditCompleted() < other.getCreditCompleted()) {
                    return NEGATIVE_ONE;
                } else if (getCreditCompleted() > other.getCreditCompleted()) {
                    return ONE;
                } else if (getCreditCompleted() == other.getCreditCompleted()) {
                    return ZERO;
                }
            } else {
                return majorCompare;
            }
        } else {
            return profileCompare;
        }
        return ZERO;
    }

    /**
     *Gets status of the student.
     * @return residency status of the student.
     */
    public abstract String getStatus();

    /**
     * This method overrides the superclass equals() methods and returns whether the given two student objects are
     * equal.
     *
     * @param studentToCompare the student that needs to be compared.
     * @return true if equal and false otherwise
     */
    @Override
    public boolean equals(Object studentToCompare) {

        if (!(studentToCompare instanceof Student student)) {
            return false;
        }

        return student.getProfile().equals(this.profile) && student.getMajor().equals(this.major)
                && student.getCreditCompleted() == this.creditCompleted;
    }

    /**
     * This method overrides the superclass toString() method and prints the string representation of the student
     * object.
     *
     * @return string representation of student object.
     */
    @Override
    public String toString() {
        return profile.toString() + " (" + this.major.getMajorCode() + " " + major + " " +
                this.major.getSchoolName() + ") " + "credits completed: " + creditCompleted
                + " (" + standing(this.creditCompleted) + ")";
    }

}
