package manager;
import student.*;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

        if (inputText.equals("P")) {
            studentRoster.print();
        } else if ((inputText.substring(ZERO, ONE)).equals("S")){
                updateScholarship(inputText);
        } else if ((inputText.substring(ZERO, ONE)).equals("C")) {
            changeStudentHelper(inputText);
        } else if ((inputText.substring(ZERO, ONE)).equals("D")) {
            dropEnrollment(inputText);
        } else if ((inputText.substring(ZERO, ONE)).equals("E")) {
            enrollStudent(inputText);
        } else if ((inputText.substring(ZERO, TWO)).equals("AR")) {
            addResStudent(inputText);
        } else if ((inputText.substring(ZERO, TWO)).equals("AN")) {
            addNonResStudent(inputText);
        } else if ((inputText.substring(ZERO, TWO)).equals("AT")) {
            addTriStateStudent(inputText);
        } else if ((inputText.substring(ZERO, TWO)).equals("AI")) {
            addInternationalStudent(inputText);
        } else if (inputText.equals("PE")){
            enrollment.print();
        } else if (inputText.equals("PS")) {
            studentRoster.printByStanding();
        } else if (inputText.equals("PC")) {
            studentRoster.printBySchoolMajor();
        }  else if ((inputText.substring(ZERO, TWO)).equals("LS")) {
            String[] input = inputText.split("\\s+");
            try {
                File textFile = new File(input[1]);
                listStudent(textFile);
            } catch ( FileNotFoundException e ) {
                return;
            }
        }
    }




    public boolean validAmount(String userInput){
        int maxAmount = 10000;
        String[] studentInfo = userInput.split("\\s+");
        try {
            int amount = Integer.parseInt(studentInfo[4]);
            if ( amount > 10000  || amount <= 0) {
                System.out.println(amount+ ": invalid amount.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Amount is not an integer.");
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println ("0: invalid amount.");
            return false;
        }
    }

    public void updateScholarship(String inputText){

        String[] studentInfo = inputText.split("\\s+");
        Profile tempProfile = new Profile(studentInfo[2], studentInfo[1], new Date(studentInfo[3]));
        EnrollStudent student = enrollment.findProfile(tempProfile);
        int scholarship;

        if ( !isNumeric(studentInfo[4]) ){
            System.out.println("Amount is not an integer.");
            return;
        } else {
            scholarship = Integer.parseInt(studentInfo[4]);
        }

        if ( !( studentRoster.containsProfile(tempProfile) instanceof Resident ) ) {
            System.out.println(tempProfile.toString() + " (Non-Resident) is not eligible for the scholarship.");
            return;
        }

        if ( studentRoster.containsProfile(tempProfile) == null  ) {
            System.out.println(tempProfile.toString() + " is not in the roster.");
            return;
        } else if ( !validAmount(inputText) ) {
            return;
        }
        if ( studentRoster.containsProfile(tempProfile).getCreditCompleted() < 12 ) {
            System.out.println(tempProfile.toString() + " part time student is not eligible for the scholarship.");
            return;
        }

        studentRoster.updateScholarshipStudent(studentRoster.containsProfile(tempProfile), scholarship);
        System.out.println(tempProfile.toString() + ": scholarship amount updated.");

    }

    public void dropEnrollment(String inputText) {
        String[] studentInfo = inputText.split("\\s+");

        Profile tempProfile = new Profile(studentInfo[2], studentInfo[1], new Date(studentInfo[3]));
        EnrollStudent student = enrollment.findProfile(tempProfile);
        if ( student == null ) {
            System.out.println(tempProfile.toString() + " is not enrolled.");
            return;
        }
        enrollment.remove(student);
    }

    public void enrollStudent( String inputText ){
        String[] studentInfo = inputText.split("\\s+");
        int credits = Integer.parseInt(studentInfo[4]);
        Profile tempProfile = new Profile(studentInfo[2], studentInfo[1], new Date(studentInfo[3]));
        EnrollStudent student = new EnrollStudent(tempProfile, credits);

        int index = enrollment.findEnrollment(student);

        if ( index != 1 ) {
            enrollment.updateEnrollment(index, student);
            System.out.println(tempProfile.toString() + " enrolled " + credits + " credits");
            return;
        }
        System.out.println(tempProfile.toString() + " enrolled " + credits + " credits");
        enrollment.add(student);
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


    private void addResStudent(String details) {
        String[] studentInfo = details.split("\\s+");

        try{
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
            String major = studentInfo[4].toUpperCase();
            Profile resProfile = new Profile ( studentInfo[2], studentInfo[1], date );
            student.Resident resStudent = new student.Resident (resProfile, Major.valueOf(major), credits, 0);
            studentRoster.add(resStudent);
            EnrollStudent newEnroll = new EnrollStudent(resProfile, credits);
            enrollment.add(newEnroll);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data in command line.");
        }

    }

    private void addNonResStudent(String details){
        String[] studentInfo = details.split("\\s+");

        try {
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
            String major = studentInfo[4].toUpperCase();
            Profile nResProfile = new Profile ( studentInfo[2], studentInfo[1], date );
            NonResident nResStudent = new NonResident (nResProfile, Major.valueOf(major), credits);
            studentRoster.add(nResStudent);
            EnrollStudent newEnroll = new EnrollStudent(nResProfile, credits);
            enrollment.add(newEnroll);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing data in command line.");
        }

    }

    private boolean validState(String state) {
        if ( state.equals("NY") || state.equals("CT") ) {

            return true;
        }
        System.out.println(state + ": Invalid state code.");
        return false;
    }

    private void addTriStateStudent(String details){

        String[] studentInfo = details.split("\\s+");

        try {
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
            } else if ( !validState(studentInfo[6]) ) {
                return;
            }
            String major = studentInfo[4].toUpperCase();
            Profile triProfile = new Profile ( studentInfo[2], studentInfo[1], date );
            TriState triStudent = new TriState ( triProfile, Major.valueOf(major), credits, studentInfo[6] );
            studentRoster.add(triStudent);
            EnrollStudent newEnroll = new EnrollStudent(triProfile, credits);
            enrollment.add(newEnroll);
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println("Missing data in command line.");
        }

    }

    public boolean isStudyAbroad(String[] studentInfo) {

        try {
            if ( studentInfo[6].equals("true") ){
                return true;
            } else if ( studentInfo[6].equals("false") ) {
                return false;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }

    public void addInternationalStudent(String details) {
        String[] studentInfo = details.split("\\s+");
        boolean studyAbroad;
        try {
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
            } else if ( !validState(studentInfo[6]) ) {
                return;
            }
            studyAbroad = isStudyAbroad(studentInfo);
            String major = studentInfo[4].toUpperCase();
            Profile interProfile = new Profile ( studentInfo[2], studentInfo[1], date );
            International interStudent = new International ( interProfile, Major.valueOf(major), credits,
                    studyAbroad );
            studentRoster.add(interStudent);
            EnrollStudent newEnroll = new EnrollStudent(interProfile, credits);
            enrollment.add(newEnroll);
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println("Missing data in command line.");
        }

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


    private void listStudent(File textFile) throws FileNotFoundException {

        Scanner scanner = new Scanner(textFile);
        scanner.useDelimiter(",");
        while ( scanner.hasNextLine() ){
            String inputText = scanner.nextLine();
            String[] studentInfo = inputText.split(",");
            if ( studentInfo[0].equals("R") ) {
                String major = studentInfo[4].toUpperCase();
                Profile resProfile = new Profile ( studentInfo[2], studentInfo[1], new Date(studentInfo[3]) );
                Resident resStudent = new Resident (resProfile, Major.valueOf(major),
                        Integer.parseInt(studentInfo[5]), 0);
                studentRoster.add(resStudent);
            } else if ( studentInfo[0].equals("N") ) {
                String major = studentInfo[4].toUpperCase();
                Profile nresProfile = new Profile ( studentInfo[2], studentInfo[1], new Date(studentInfo[3]) );
                NonResident nresStudent = new NonResident (nresProfile, Major.valueOf(major),
                        Integer.parseInt(studentInfo[5]));
                studentRoster.add(nresStudent);
            } else if ( studentInfo[0].equals("T") ) {
                String major = studentInfo[4].toUpperCase();
                Profile triProfile = new Profile ( studentInfo[2], studentInfo[1], new Date(studentInfo[3]) );
                TriState triStudent = new TriState (triProfile, Major.valueOf(major),
                        Integer.parseInt(studentInfo[5]), studentInfo[6]);
                studentRoster.add(triStudent);
            } else if ( studentInfo[0].equals("I") ) {
                String major = studentInfo[4].toUpperCase();
                Profile interProfile = new Profile ( studentInfo[2], studentInfo[1], new Date(studentInfo[3]) );
                International interStudent = new International (interProfile, Major.valueOf(major),
                        Integer.parseInt(studentInfo[5]), Boolean.parseBoolean(studentInfo[6]) );
                studentRoster.add(interStudent);

            }
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
            } else if (inputText.substring(ZERO, ONE).equals("R") || inputText.substring(ZERO, ONE).equals("D") ||
                    inputText.equals("P") || inputText.equals("PS") || inputText.substring(ZERO, TWO).equals("E") ||
                    inputText.equals("PC") || inputText.substring(ZERO, TWO).equals("LS") ||
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

