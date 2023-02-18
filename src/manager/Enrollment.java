package manager;

public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;
    public void add(EnrollStudent enrollStudent){} //add to the end of array
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
        return false;
    }
    public void print() {} //print the array as is without sorting
}
