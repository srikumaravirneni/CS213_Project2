package manager;
import student.Profile;
import student.Student;
import student.Major;
import student.Resident;

/**
 * Creates a Student roster object and implements all the major functionalities like add, remove, modify and sorting.
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class Roster {

    private Student[] roster; //student roster array
    private int size; //size of student roster array
    private static final int NOT_FOUND = -1;

    private static final int ONE = 1;
    private static final int NEGATIVE_ONE = -1;
    private static final int ZERO = 0;

    public boolean empty() {
        if ( this.size < ONE ) {
            return true;
        }
         return false;
    }

    public Student[] getRoster() {
        return this.roster;
    }

    /**
     * Searches for the given student in the roster and returns its index in the array if present.
     *
     * @param student the student that needs to be searched for
     * @return if student is found will return the position of the student, returns NOT_FOUND otherwise.
     */
    private int find(Student student) {

        for (int i = ZERO; i < size; i++) {
            if (roster[i] == null) {
                break;
            }
            if (roster[i].equals(student)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public int getSize(){
        return this.size;
    }


    public boolean updateScholarshipStudent( Student rStudent, int scholarship ){
        for (int i = 0; i < roster.length; i++) {
            if ( roster[i] == null ) {
                break;
            }
            if (roster[i].equals(rStudent)) {
                Resident resStudent = (Resident) roster[i]; // cast the object to Subclass
                resStudent.setScholarship(scholarship); // call the subclass method
                return true;
            }
        }
        return false;
    }



/**
 * Method increases the array capacity by 4 if the array is full.
 */
    private void grow() {
        if (this.size == ZERO) {
            Student[] rosterIncrease = new Student[4];
            this.size += 4;
            this.roster = rosterIncrease;

        } else {
            Student[] rosterIncrease = new Student[this.size + 4];
            for (int i = 0; i < this.size; i++) {
                rosterIncrease[i] = this.roster[i];
            }
            this.size += 4;
            this.roster = rosterIncrease;

        }

    }

    /**
     * helper method to add student after last student object in roster array
     *
     * @param student student that needs to be added.
     */
    private void addStudent(Student student) {
        for (int i = ZERO; i < size; i++) {
            if (roster[i] == null) {
                roster[i] = student;
                break;
            }
        }
    }

    /**
     * Checks for the size of the roster array and calls the add student method for adding the student after
     * last student.
     *
     * @param student the student that needs to be added.
     * @return true if student has been added, false otherwise.
     */
    public boolean add(Student student) {

        if (this.size <= ZERO || this.roster == null || roster[size - 1] != null) {
            grow();
            addStudent(student);
            return true;
        } else {
            addStudent(student);
            return true;
        }
    }

    /**
     * Rearranges the roster when a student has been removed.
     *
     * @param position the position from which rearrangement of roster must be done.
     */
    private void rearrangeRoster(int position) {
        for (int i = position; i < size - 1; i++) {
            roster[i] = roster[i + 1];
        }
    }

    /**
     * Removes the student from the roster and calls rearrangeRoster method so that the roster is rearranged without
     * empty space.
     *
     * @param student the student that needs to be removed
     * @return true if the student has been found and removed, false otherwise.
     */
    public boolean remove(Student student) {
        if (this.size < ONE) {
            return false;
        }
        int i = find(student);
        if (i != NOT_FOUND) {
            roster[i] = null;
            rearrangeRoster(i);
            return true;
        }
        return false;
    }

    /**
     * This method creates an array that contains all the students from a given school.
     *
     * @param schoolName the school name from which the student names must be listed.
     * @return a Student array of students from that school.
     */
    public Student[] listBySchool(String schoolName) {

        Student[] schoolList = new Student[this.size];
        int counter = ZERO;

        for (int i = ZERO; i < this.size; i++) {

            if (roster[i] == null) {
                break;
            }

            String currentSchoolName = roster[i].getMajor().getSchoolName();

            if (currentSchoolName.equals(schoolName)) {
                schoolList[counter] = roster[i];
                counter++;
            }

        }
        return schoolList;
    }

    /**
     * Changes the major of student that has been entered.
     *
     * @param profile The profile for which major needs to be changed.
     * @param major   The new major that should be replaced with the existing major.
     */
    public void changeMajor(Profile profile, String major) {
        if (containsProfile(profile) == null) {
            return;
        }
        Student toChange = containsProfile(profile);
        int index = find(toChange);
        roster[index].setMajor(Major.valueOf(major));
    }

    /**
     * Checks if the student exists in the roster array.
     * @param student the student that needs to be checked for in the roster.
     * @return true if student exists in the roster, false otherwise.
     */
    public boolean contains(Student student) {
        if (this.size < ONE) {
            return false;
        }
        int i = ZERO;
        while (i < size) {
            if (this.roster[i] == null) {
                break;
            }
            if (roster[i].equals(student)) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Checks if the given profile exists in the student roster.
     *
     * @param profile the profile that needs to be searched for the roster.
     * @return true if profile exists, false otherwise.
     */
    public Student containsProfile(Profile profile) {

        if (this.size < ONE) {
            return null;
        }

        int i = ZERO;
        while (i < size) {

            if (this.roster[i] == null) {
                break;
            }

            if (roster[i].getProfile().equals(profile)) {
                return roster[i];
            }
            i++;
        }
        return null;
    }

    /**
     * Sorts the roster array by student last name, first name and DOB.
     *
     * @param students    the array that needs to be sorted.
     * @param arrayLength length of the array that is being sorted.
     * @return sorted array of students.
     */
    public Student[] sortByName(Student[] students, int arrayLength) {

        for (int i = ZERO; i < arrayLength - 1; i++) {

            if (students[i] == null) {
                break;
            }

            int pointer = i;

            for (int j = i + 1; j < arrayLength; j++) {
                if (students[j] == null) {
                    break;
                }
                Profile pointerProfile = students[pointer].getProfile();
                Profile studentProfile = students[j].getProfile();
                int compare = studentProfile.compareTo(pointerProfile);
                if (compare < ZERO) {
                    pointer = j;
                }
            }
            Student tempValue = students[pointer];
            students[pointer] = students[i];
            students[i] = tempValue;
        }
        return students;
    }

    /**
     * Sorts the roster array students first name, last name and dob using sortByName helper method
     */
    public void print() {

        if (this.size < ONE) {
            System.out.println("Student roster is empty!");
            return;
        }
        if (this.roster[1] == null) {
            System.out.println(this.roster[ZERO].toString());
            return;
        }

        System.out.println("** Student roster sorted by last name, first name, DOB **");
        Student[] sortedArray = sortByName(this.roster, size);

        for (Student student : sortedArray) {
            if (student == null) {
                break;
            }

            System.out.println(student.toString());
        }
        System.out.println("* end of roster *");
    }

    /**
     * Compare one major enum with other one.
     *
     * @param major1 first major that is being compared.
     * @param major2 second major that is being compared with first one.
     * @return value less than 0 if major 1 is less than major 2, value greater than 0 if major 1 is greater than
     * major 2 and zero if major 1 and major 2 are same.
     */
    public int compareMajor(Major major1, Major major2) {
        String schoolName1 = major1.getSchoolName();
        String schoolName2 = major2.getSchoolName();

        int schoolCompare = schoolName1.compareTo(schoolName2);

        if (schoolCompare == ZERO) {
            return major1.toString().compareTo(major2.toString());
        }
        return schoolCompare;
    }

    /**
     * print roster sorted by school, major
     */
    public void printBySchoolMajor() {

        if (this.size < ONE) {
            System.out.println("Student roster is empty!");
            return;
        }
        if (this.roster[ONE] == null) {
            System.out.println(this.roster[0].toString());
            return;
        }


        System.out.println("** Student roster sorted by school, major **");
        if (this.size < ONE) {
            System.out.println("Student roster is empty!");
            return;
        }
        if (roster[ONE] == null) {
            System.out.println(roster[ZERO].toString());
            return;
        }
        for (int i = ZERO; i < size - 1; i++) {
            if (roster[i] == null) {
                break;
            }
            int pointer = i;
            for (int j = i + 1; j < size; j++) {
                if (roster[j] == null) {
                    break;
                }
                Major tempMajor = roster[pointer].getMajor();
                Major iterateMajor = roster[j].getMajor();
                if (compareMajor(iterateMajor, tempMajor) < ZERO) {
                    pointer = j;
                }
            }
            Student tempStudent = roster[pointer];
            roster[pointer] = roster[i];
            roster[i] = tempStudent;
        }
        for (Student student : roster) {
            if (student == null) {
                break;
            }
            System.out.println(student.toString());
        }
        System.out.println("* end of roster *");
    }

    public void updateStudentCreds(Profile profile, int credits) {
        for ( int i = 0; i < this.size; i++ ) {
            if ( roster[i] == null ) {
                break;
            }
            if( roster[i].getProfile().equals(profile)) {
                roster[i].setCreditCompleted(credits);
            }
        }
    }


    /**
     * prints roster by standing.
     */
    public void printByStanding() {
        if (this.size < ONE) {
            System.out.println("Student roster is empty!");
            return;
        }
        if (roster[ONE] == null) {
            System.out.println(roster[0].toString());
            return;
        }
        System.out.println("** Student roster sorted by standing **");
        for (int i = ZERO; i < size - 1; i++) {
            if (roster[i] == null) {
                break;
            }
            int pointer = i;
            for (int j = i + 1; j < size; j++) {
                if (roster[j] == null) {
                    break;
                }
                String currentStanding = roster[j].standing(roster[j].getCreditCompleted());
                String tempStanding = roster[pointer].standing(roster[pointer].getCreditCompleted());
                if (currentStanding.compareTo(tempStanding) < ZERO) {
                    pointer = j;
                }
            }
            Student tempStudent = roster[pointer];
            roster[pointer] = roster[i];
            roster[i] = tempStudent;
        }


        for (Student student : roster) {
            if (student == null) {
                break;
            }

            System.out.println(student.toString());
        }
        System.out.println("* end of roster *");
    }

    public String getStatus(Profile profile) {
        for ( int i = 0; i < size; i++) {
            if ( roster[i].getProfile().equals(profile) ) {
                return roster[i].getStatus();
            }
        }
        return null;
    }

}
