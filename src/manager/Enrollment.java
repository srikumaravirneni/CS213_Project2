package manager;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;

    public void add(EnrollStudent enrollStudent){
        for ( int i = 0; i < this.size; i++ ) {
            if ( this.enrollStudents[i] == null ) {
                enrollStudents[i] = enrollStudent;
                break;
            }
        }
    } //add to the end of array

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
        for ( int i = 0; i < this.size; i++ ) {
            if ( enrollStudents[i] == null ) {
                return;
            }
            System.out.println(enrollStudents[i].toString());
        }

    } //print the array as is without sorting
}
