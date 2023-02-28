package manager;
import student.Profile;

/**
 * This class keeps a track of the credits that a student has enrolled for.
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class EnrollStudent {
    private final Profile profile; //the profile that the student is enrolling
    private int creditsEnrolled; //the number of credits enrolled be student.

    /**
     * The constructor for EnrollStudent object.
     * @param profile the profile of the student being enrolled.
     * @param creditsEnrolled the number of credits enrolled by the student being enrolled.
     */
    public EnrollStudent ( Profile profile, int creditsEnrolled ) {
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * Returns the profile of enrolling student.
     * @return profile of enrolling student
     */
    public Profile getProfile () {
        return this.profile;
    }

    /**
     * Returns the number of credits enrolled of enrolling student.
     * @return number of credits enrolled by the profile.
     */
    public int getCreditsEnrolled () {
        return this.creditsEnrolled;
    }

    /**
     * This method is used to set the credits enrolled parameter in the EnrollStudent object.
     * @param creditsEnrolled the number of credits that the profile has enrolled.
     */
    public void setCreditsEnrolled (int creditsEnrolled) {
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * This method overrides the superclass equals() method and returns whether the  given two enrollment objects are
     * equal.
     * @param enrollment the enrollment object that needs to be compared.
     * @return boolean true if equal otherwise false.
     */
    @Override
    public boolean equals (Object enrollment ){

        if ( !( enrollment instanceof EnrollStudent student ) ) {
            return false;
        }

        return this.profile.equals(student.getProfile()) && this.creditsEnrolled == student.getCreditsEnrolled();
    }

    /**
     * This method overrides the superclass toString() method and prints the string representation of the profile
     * object along with number of credits enrolled.
     * @return string representation of profile object along with number of credits enrolled.
     */
    @Override
    public String toString () {
        return profile.toString() + ": credits enrolled: " +  this.creditsEnrolled;
    }

}
