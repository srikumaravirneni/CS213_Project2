package manager;


import student.Profile;
import student.Student;

/**
 * Enrollment class that stores a list of enrolled students
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public Enrollment() {
        this.enrollStudents = new EnrollStudent[4];
        this.size = 4;
    }

    public void grow() {
        EnrollStudent[] arrayIncrease = new EnrollStudent[this.size + 4];
        for (int i = 0; i < this.size; i++) {
            arrayIncrease[i] = this.enrollStudents[i];
        }
        this.size += 4;
        this.enrollStudents = arrayIncrease;
    }


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
    } //add to the end of array

    public EnrollStudent findProfile ( Profile enrollmentStudent ) {
        for ( int i = 0; i < this.size; i++ ) {
            if ( enrollStudents[i] == null ) {
                return null;
            } else if ( enrollStudents[i].getProfile() == enrollmentStudent ) {
                return enrollStudents[i];
            }
        }
        return null;
    }

    public void updateEnrollment(int index, EnrollStudent student) {
        this.enrollStudents[index] = student;
    }

    public EnrollStudent getEnrollment (int index) {
        return enrollStudents[index];
    }


    public int findEnrollment ( EnrollStudent enrollmentStudent ) {
        for ( int i = 0; i < this.size; i++ ) {
            if ( enrollStudents[i] == null ) {
                return -1;
            } else if ( enrollStudents[i] == enrollmentStudent ) {
                return i;
            }
        }
        return -1;
    }

    public void rearrange (int currIndex){
        for ( int i = currIndex + 1; i < this.size - 1; i++ ) {
            if ( enrollStudents[i+1] == null ){
                enrollStudents[currIndex] = enrollStudents[i];
            }
        }
    }

    //move the last one in the array to replace the deleting index position
    public void remove (EnrollStudent enrollStudent) {
        int index = findEnrollment(enrollStudent);
        if ( index == -1 ) {
            return;
        } else {
            enrollStudents[index] = null;
        }
        rearrange(index);
    }

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

    public void print() {
        System.out.println("** Enrollment **");
        for ( int i = 0; i < this.size; i++ ) {
            if ( enrollStudents[i] == null ) {
                return;
            }
            System.out.println(enrollStudents[i].toString());
        }

        System.out.println("* end of enrollment *");

    } //print the array as is without sorting
}


