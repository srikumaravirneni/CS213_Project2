package manager;

import student.Profile;

/**
 * This class keeps a track of the credits that a student has enrolled for
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    public EnrollStudent ( Profile profile, int creditsEnrolled ) {
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    public Profile getProfile () {
        return this.profile;
    }

    public int getCreditsEnrolled () {
        return this.creditsEnrolled;
    }

    @Override
    public boolean equals (Object enrollment ){

        if (!(enrollment instanceof EnrollStudent)) {
            return false;
        }

        EnrollStudent student = (EnrollStudent) enrollment;

        return this.profile.equals(student.getProfile()) && this.creditsEnrolled == student.getCreditsEnrolled();
    }

    @Override
    public String toString () {
        return profile.toString() + ": credits enrolled: " +  this.creditsEnrolled;
    }

}
