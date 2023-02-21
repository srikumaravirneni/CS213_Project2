package manager;
import student.Profile;
import student.Student;
import java.util.Scanner;
import student.Major;

/**
 * Roster manager class is the class that takes commands from the user and performs the necessary action on the roster
 * object based on the command entered.
 * This class calls necessary methods from different classes to perform its operations.
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class TutionManager {
    private Roster studentRoster = new Roster(); //creating a Roster object to pass the student input to Roster class.
    private Enrollment enrollment = new Enrollment();
    private static final int ZERO = 0;
    private static final int NEGATIVE_ONE = -1;
    private static final int ONE = 1;
    private static final int TWO = 2;

    /**
     * Takes the input from main method, parses the command from input.
     * Based on the entered command calls necessary helper method in other class or in same class that execute
     * the command.
     *
     * @param inputText a line of text entered by user in terminal taken in by scanner
     */
    private void input(String inputText) {

        if ((inputText.substring(ZERO, TWO)).equals("AR")) {
            addResStudent(inputText);
        } else if ((inputText.substring(ZERO, TWO)).equals("AN")) {
            addNonResStudent(inputText);
        } else if ((inputText.substring(ZERO, TWO)).equals("AT")) {
            addTrisStateStudent(inputText);
        } else if ((inputText.substring(ZERO, TWO)).equals("AI")) {

        } else if ((inputText.substring(ZERO, ONE)).equals("D")) {
            dropStudentHelper(inputText);
        } else if (inputText.equals("PS")) {
            studentRoster.printByStanding();
        } else if (inputText.equals("PC")) {
            studentRoster.printBySchoolMajor();
        } else if ((inputText.substring(ZERO, ONE)).equals("P")) {
            studentRoster.print();
        } else if ((inputText.substring(ZERO, ONE)).equals("S")){

        } else if ((inputText.substring(ZERO, ONE)).equals("L")) {
            listStudentsSchool(inputText);
        } else if ((inputText.substring(ZERO, ONE)).equals("C")) {
            changeStudentHelper(inputText);
        }
    }

    /**
     * Checks if the date of birth entered by user is valid or is not too young.
     *
     * @param date the date entered by user that needs to be checked.
     * @return true if date is valid, false otherwise.
     */
    private boolean dateCheck(Date date) {
        Date currentDate = new Date();

        if (date.compareTo(currentDate) >= ZERO || !date.isValid()) {
            System.out.println("DOB invalid: " + date.toString() + " not a valid calendar date!");
            return false;
        } else if (currentDate.tooYoung(date)) {
            System.out.println("DOB invalid: " + date.toString() + " younger than 16 years old.");
            return false;
        } else {
            return true;
        }
    }


    /**
     * Checks if the credits entered by user is a positive integer or not.
     *
     * @param credits value entered by user that represents the number of credits
     * @return true if credits value is a positive integer, false otherwise.
     */
    private boolean isNumeric(String credits) {

        int creditsValue;

        try {
            creditsValue = Integer.parseInt(credits);
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }

    }

    private boolean isPositive( int credits ) {
        if ( credits < ZERO ) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the user entered a valid major or not.
     *
     * @param major major entered by user that needs to be checked.
     * @return true if the major is valid, false otherwise.
     */
    private boolean validMajor(String major) {
        for (Major majorTypes : Major.values()) {
            if (majorTypes.name().equals(major.toUpperCase())) {
                return true;
            }
        }
        System.out.println("Major code invalid: " + major);
        return false;
    }



    /**
     * Parses the entered details and adds the student if the student details are right and student doesn't
     * exist in the roster.
     *
     * @param details       a line of text entered by user in terminal that contain student details
     */
    private void addResStudent(String details) {

        String[] studentInfo = details.split("\\s+");

        Date date = new Date(studentInfo[3]);
        int credits;

        if (!isNumeric(studentInfo[5])) {
            System.out.println("Credits completed invalid: not an integer!");
            return;
        } else {
            credits = Integer.parseInt(studentInfo[5]);
        }
        if ( !isPositive(credits) ) {
            System.out.println("Credits completed invalid: cannot be negative!");
            return;
        } else if (!dateCheck(date)) {
            return;
        } else if (!validMajor(studentInfo[4])) {
            return;
        }

        Profile resStudent = new Profile ( studentInfo[2], studentInfo[1], date );
        student.Resident resStudent = new student.Resident (addNonResStudent);)
    }

    private void addNonResStudent(String details){
        String[] studentInfo = details.split("\\s+");
        Date date = new Date(studentInfo[3]);


        if ( !isNumeric(studentInfo[5]) ) {
            System.out.println("Credits completed invalid: not an integer!");
        } else if (!dateCheck(date)) {
            return;
        } else if (!validMajor(studentInfo[4])){
            return;
        }

        Profile profile = new Profile(studentInfo[2], studentInfo[1], date);

        student.NonResident nonresStudent = new student.NonResident(profile, Major.valueOf(studentInfo[4]), Integer.parseInt(studentInfo[5]));

    }

    private void addTrisStateStudent(String details){
        String[] studentInfo = details.split("\\s+");
        Date date = new Date(studentInfo[3]);


        if ( !isNumeric(studentInfo[5]) ) {
            System.out.println("Credits completed invalid: not an integer!");
        } else if (!dateCheck(date)) {
            return;
        } else if (!validMajor(studentInfo[4])){
            return;
        }

        Profile profile = new Profile(studentInfo[2], studentInfo[1], date);

        student.TriState tristateStudent = new student.TriState(profile, Major.valueOf(studentInfo[4]), Integer.parseInt(studentInfo[5]), studentInfo[6]);

    }
    /**
     * Modifies the student major if the new major that has been entered exists and is an appropriate valid value.
     *
     * @param details a line of text entered by user in terminal that contain student info along with other details.
     */
    private void changeStudentHelper(String details) {

        String[] studentInfo = details.split("\\s+");
        Profile profile = new Profile(studentInfo[2], studentInfo[1], new Date(studentInfo[3]));
        String major = studentInfo[4];

        if (studentRoster.containsProfile(profile) == null) {
            System.out.println(profile.toString() + " " + "is not in the roster.");
            return;
        } else if (validMajor(studentInfo[4])) {
            studentRoster.changeMajor(profile, major);
            System.out.println(profile.toString() + " major changed to " + profile.toString() + " " + major);
            return;
        } else {
            System.out.println("Major code invalid:" + " " + major);
            return;
        }
    }

    /**
     * Takes in the details entered by user, finds the school entered and prints the students from
     * the roster that are in the school.
     *
     * @param details a line of text entered by user in terminal that contain school name along with other details.
     */
    private void listStudentsSchool(String details) {
        String[] studentInfo = details.split("\\s+");
        String schoolName = studentInfo[1].toUpperCase();
        Student[] returnedList = studentRoster.listBySchool(schoolName);
        if (returnedList[ZERO] == null) {
            System.out.println("School doesn't exist: " + schoolName);
            return;
        }

        returnedList = studentRoster.sortByName(returnedList, returnedList.length);

        System.out.println("* Students in " + schoolName + " *");

        for (int i = ZERO; i < returnedList.length; i++) {

            if (returnedList[i] == null) {
                break;
            }
            System.out.println(returnedList[i].toString());
        }
        System.out.println("*end of list");
    }

    /**
     * The method takes in the student to be removed as input and removes the student if it exists in the roster.
     *
     * @param details the student to be removed
     */
    private void dropStudentHelper(String details) {

        String[] studentInfo = details.split("\\s+");
        Profile profile = new Profile(studentInfo[2], studentInfo[1], new Date(studentInfo[3]));
        // Student student = new Student(profile, null, 0);

        Student returnedStudent = studentRoster.containsProfile(profile);

        if (returnedStudent != null) {
            studentRoster.remove(returnedStudent);
           // System.out.println(student.getProfile().toString() + " " + "removed from the roster.");
        } else {
           // System.out.println(student.getProfile().toString() + " " + "is not in the roster.");
        }
    }


    /**
     * User interactive method that takes input from the user and passes it to input helper method that processes it.
     */
    public void run() {
        System.out.println("Tuition Manager running...");
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {

            String inputText = input.nextLine();
            if (inputText.equals("")) {
                continue;
            } else if (inputText.substring(ZERO, ONE).equals("R") || inputText.substring(ZERO, TWO).equals("D") ||
                    inputText.equals("P") || inputText.equals("PS") || inputText.substring(ZERO, TWO).equals("E") ||
                    inputText.equals("PC") || inputText.substring(ZERO, ONE).equals("L") ||
                    inputText.substring(ZERO, ONE).equals("C") || inputText.substring(ZERO, TWO).equals("AR") ||
                    inputText.substring(ZERO, TWO).equals("AN") || inputText.substring(ZERO, TWO).equals("AT") ||
                    inputText.substring(ZERO, TWO).equals("AI") || inputText.substring(ZERO, TWO).equals("PT") ||
                    inputText.substring(ZERO, ONE).equals("S")) {

                input(inputText);

            } else if (inputText.substring(ZERO, ONE).equals("Q")) {
                System.out.println("Tuition Manager terminated.");
                System.exit(ZERO);
            } else {
                System.out.println(inputText + " " + "is an invalid command!");
            }
        }
    }
}

