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
    private Profile profile;
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

    public boolean isValid(int creditEnrolled) {
        if ( this.creditCompleted < 3 || this.creditCompleted > 24 ){
            return false;
        }
        return true;
    }

    public abstract double tuitionDue(int creditsEnrolled);

    public abstract boolean isResident(); //polymorphism
    /*
    /**
     * testbed main method for testing if Student compare to method is working as expected.
     *
     * @param args

    public static void main(String[] args) {
        Profile profile1, profile2 = null;
        System.out.println("Student compareTo() Testbed running... \n");
        profile1 = new Profile("Banner", "Bruce", new Date("5/1/1999"));
        Student fStudentTest1 = new Student(profile1, Major.CS, 20);
        profile2 = new Profile("Banner", "Bruce", new Date("5/1/1999"));
        Student sStudentTest1 = new Student(profile2, Major.CS, 20);
        studentTestResult(fStudentTest1, sStudentTest1, ZERO);

        profile1 = new Profile("Wayne", "Bruce", new Date("1/10/1990"));
        Student fStudentTest2 = new Student(profile1, Major.MATH, 100);
        profile2 = new Profile("Wayne", "Bruce", new Date("1/10/1990"));
        Student sStudentTest2 = new Student(profile2, Major.MATH, 100);
        studentTestResult(fStudentTest2, sStudentTest2, ZERO);

        profile1 = new Profile("Jane", "Mary", new Date("4/20/2003"));
        Student fStudentTest3 = new Student(profile1, Major.MATH, 45);
        profile2 = new Profile("Parker", "Peter", new Date("4/10/2003"));
        Student sStudentTest3 = new Student(profile2, Major.CS, 50);
        studentTestResult(fStudentTest3, sStudentTest3, NEGATIVE_ONE);

        profile1 = new Profile("Rogers", "Peggy", new Date("1/2/1980"));
        Student fStudentTest4 = new Student(profile1, Major.MATH, 60);
        profile2 = new Profile("Rogers", "Steve", new Date("2/3/1985"));
        Student sStudentTest4 = new Student(profile2, Major.EE, 62);
        studentTestResult(fStudentTest4, sStudentTest4, NEGATIVE_ONE);

        profile1 = new Profile("Lane", "Lois", new Date("7/15/1990"));
        Student fStudentTest5 = new Student(profile1, Major.BAIT, 35);
        profile2 = new Profile("Kent", "Clark", new Date("6/7/1995"));
        Student sStudentTest5 = new Student(profile2, Major.ITI, 55);
        studentTestResult(fStudentTest5, sStudentTest5, ONE);

        profile1 = new Profile("Stark", "Tony", new Date("6/16/2010"));
        Student fStudentTest6 = new Student(profile1, Major.MATH, 200);
        profile2 = new Profile("Stark", "Pepper", new Date("7/1/2010"));
        Student sStudentTest6 = new Student(profile2, Major.BAIT, 150);
        studentTestResult(fStudentTest6, sStudentTest6, ONE);
    }
    */
    /*
    /**
     * A helper method for testing the student test bed main method.
     * Checks if expected and actual output are same or not.
     *
     * @param student1       student1 that is being used for comparison in a test case.
     * @param student2       student2 that is bing used for comparison in a test case.
     * @param expectedOutput the output that is being compared with the actual output the test case returns.


    private static void studentTestResult(Student student1, Student student2, int expectedOutput) {
        int actualOutput = student1.compareTo(student2);

        System.out.println("First Student: " + student1.toString());
        System.out.println("Second Student: " + student2.toString());
        System.out.println("compareTo() returns " + actualOutput);

        if (expectedOutput == ZERO) {
            if (actualOutput == ZERO) {
                System.out.println("PASS.\n");
            } else {
                System.out.println("FAIL.\n");
            }
        } else if (expectedOutput == NEGATIVE_ONE) {
            if (actualOutput < ZERO) {
                System.out.println("PASS.\n");
            } else {
                System.out.println("FAIL.\n");
            }
        } else if (expectedOutput == ONE) {
            if (actualOutput > ZERO) {
                System.out.println("PASS.\n");
            } else {
                System.out.println("FAIL.\n");
            }
        }
    }

     */


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

        if (!(studentToCompare instanceof Student)) {
            return false;
        }
        Student student = (Student) studentToCompare;

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
