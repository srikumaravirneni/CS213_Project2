package manager;
import student.Profile;

/**
 * Enrollment class that stores a list of enrolled students
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class Enrollment {

    private EnrollStudent[] enrollStudents; //array that stored enrolled students
    private int size; //size of EnrollStudent array.

    /**
     * Method initializes the enrollStudents array with size of 4.
     */
    public Enrollment() {
        this.enrollStudents = new EnrollStudent[4];
        this.size = 4;
    }

    /**
     * Returns the EnrollStudent[] array
     *
     * @return EnrollStudent[] enrollStudents array
     */
    public EnrollStudent[] getEnrollStudents() {
        return enrollStudents;
    }

    /**
     * Returns the size of enrollStudents array
     * @return size of enrollStudents array
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns true or false based on enrollStudents array size.
     * @return true if size is less than one, else returns false.
     */
    public boolean empty() {
        return this.size < 1;
    }

    /**
     * Increases the size of enrollStudents array by 4.
     */
    public void grow() {
        EnrollStudent[] arrayIncrease = new EnrollStudent[this.size + 4];
        if ( this.size >= 0 ) {
            System.arraycopy(this.enrollStudents, 0, arrayIncrease, 0, this.size);
        }
        this.size += 4;
        this.enrollStudents = arrayIncrease;
    }

    /**
     * The method adds students to the end of enrollStudents array.
     * @param enrollStudent the student that needs to be enrolled/ added the enrollStudents array.
     */
    public void add(EnrollStudent enrollStudent){

        if ( this.enrollStudents[this.size -1] != null ) {
            grow();
        }
        for ( int i = 0; i < this.size; i++ ) {
            if ( this.enrollStudents[i] == null ) {
                enrollStudents[i] = enrollStudent;
                break;
            }
        }
    }

    /**
     * Tme method looks for the enrollmentStudent profile and returns the enrollStudent object.
     * @param enrollmentStudent the student that needs to be searched for.
     * @return the student of the profile that is being searched otherwise returns null.
     */
    public EnrollStudent findProfile ( Profile enrollmentStudent ) {
        for ( int i = 0; i < this.size; i++ ) {
            if ( enrollStudents[i] == null ) {
                return null;
            } else if ( enrollStudents[i].getProfile().equals( enrollmentStudent ) ) {
                return enrollStudents[i];
            }
        }
        return null;
    }

    /** Update the number of credits the student has enrolled.
     * @param index the index of the student object whose enrollment needs to be changed.
     * @param newCredits the number of credits that the currently enrolled credits need to be replaced with.
     */
    public void updateEnrollment(int index, int newCredits) {
        this.enrollStudents[index].setCreditsEnrolled(newCredits);
    }

    /**
     * Get enrollment of a specific index in EnrollStudent array.
     * @param index the index for which the enrollment must be returned.
     * @return the enrollStudents of the index value.
     */
    public EnrollStudent getEnrollment (int index) {
        return enrollStudents[index];
    }

    /**
     * The profile that needs to be searched for in the enrollStudents array.
     * @param profile the student profile that is being searched for.
     * @return the index of the profile in the enrollStudents array otherwise -1(if not found).
     */
    public int findEnrollmentProfile ( Profile profile ) {
        for ( int i = 0; i < this.size; i++ ) {
            if ( enrollStudents[i] == null ) {
                return -1;
            } else if ( enrollStudents[i].getProfile().equals(profile) ) {
                return i;
            }
        }
        return -1;
    }

    /**
     * The method looks for enrollmentStudent from enrollStudents array.
     * @param enrollmentStudent the enrolled student that needs to be searched for in the enrollStudents array.
     * @return the index of enrollmentStudent value in enrollStudents array.
     * */
    public int findEnrollment ( EnrollStudent enrollmentStudent ) {
        for ( int i = 0; i < this.size; i++ ) {
            if ( enrollStudents[i] == null ) {
                return -1;
            } else if ( enrollStudents[i].equals(enrollmentStudent) ) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Move the last one in the array to replace the deleting index position
     * @param currIndex the index where the student has been removed.
     */
    public void rearrange (int currIndex){

        int i = 0;
        while ( i < this.size - 1 ) {
            if ( this.enrollStudents[i] == null && i != currIndex ) {
                break;
            }
            i++;
        }
        this.enrollStudents[currIndex] = this.enrollStudents[i];
        this.enrollStudents[i] = null;

    }

    /**
     * Remove a student from enrollStudents array
     * @param enrollStudent the student that needs to be removed from the enrollStudents array.
     */
    //
    public void remove (EnrollStudent enrollStudent) {
        int index = findEnrollment(enrollStudent);
        if ( index == -1 ) {
            return;
        } else {
            enrollStudents[index] = null;
        }
        rearrange(index);
    }

    /**
     * Check if the enrollStudents array contains the enrollStudent value.
     * @param enrollStudent the student that needs to be checked for in enrollStudents arrray.
     * @return true is enrollStudents array contains the enrollStudent, otherwise false.
     */
    public boolean contains(EnrollStudent enrollStudent){
        for ( int i = 0; i < this.size; i++ ) {
            if ( enrollStudents[i] == null ) {
                return false;
            } else if ( enrollStudents[i] == enrollStudent ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Print the array as it is without sorting.
     */
    public void print() {

        if ( enrollStudents[0] == null) {
            System.out.println("Enrollment is empty!");
            return;
        }

        System.out.println("** Enrollment **");
        for ( int i = 0; i < this.size; i++ ) {
            if ( enrollStudents[i] == null ) {
                return;
            }
            System.out.println(enrollStudents[i].toString());
        }

        System.out.println("* end of enrollment *");

    }
}


