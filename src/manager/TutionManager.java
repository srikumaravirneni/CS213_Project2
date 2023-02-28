package manager;
import student.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Tuition manager class is the class that takes commands from the user and performs the necessary action on the roster
 * and enrollment object based on the command entered.
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

        if ( inputText.equals("P") ) {
            studentRoster.print();
        } else if ( inputText.equals("SE") ){
            finalAddPrint();
        } else if ( (inputText.substring(ZERO, ONE)).equals("S") ){
                updateScholarship(inputText);
        } else if ( (inputText.substring(ZERO, ONE)).equals("C") ) {
            changeStudentHelper(inputText);
        } else if ( (inputText.substring(ZERO, ONE)).equals("D") ) {
            dropEnrollment(inputText);
        } else if ( (inputText.substring(ZERO, ONE)).equals("E") ) {
            enrollStudent(inputText);
        } else if ( (inputText.substring(ZERO, ONE)).equals("R") ) {
            dropRoster(inputText);
        } else if ( (inputText.substring(ZERO, TWO)).equals("AR") ) {
            addResStudent(inputText);
        } else if ( (inputText.substring(ZERO, TWO)).equals("AN") ) {
            addNonResStudent(inputText);
        } else if ( (inputText.substring(ZERO, TWO)).equals("AT") ) {
            addTriStateStudent(inputText);
        } else if ( (inputText.substring(ZERO, TWO)).equals("AI") ) {
            addInternationalStudent(inputText);
        } else if ( inputText.equals("PT") ){
                printWithTuition();
        } else if ( inputText.equals("PE") ){
            enrollment.print();
        } else if (inputText.equals("PS")) {
            studentRoster.printByStanding();
        } else if (inputText.equals("PC")) {
            studentRoster.printBySchoolMajor();
        }  else if ( (inputText.substring(ZERO, TWO)).equals("LS") ) {
            String[] input = inputText.split("\\s+");
            try {
                File textFile = new File(input[1]);
                listStudent(textFile);
                System.out.println("Students loaded to the roster.");
            } catch ( FileNotFoundException e ) {
                return;
            }
        }
    }

    /**
     * The method prints the students in the enrollment array after adding thee enrolled credits to total credits.
     */
    public void finalAddPrint() {
        EnrollStudent[] tempArray = enrollment.getEnrollStudents();
        int size = enrollment.getSize();

        for ( int i = 0; i < size; i++ ){
            if ( tempArray[i] == null ) {
                break;
            }
            int getCredEnrolled = tempArray[i].getCreditsEnrolled();
            int totalCred = getCredEnrolled + studentRoster.containsProfile(tempArray[i].getProfile()).
                    getCreditCompleted();
            studentRoster.updateStudentCreds(tempArray[i].getProfile(), totalCred);
        }
        System.out.println("Credit completed has been updated.");
        System.out.println("** list of students eligible for graduation **");
        Student[] tempRoster = studentRoster.getRoster();
        for ( int i = 0; i < tempRoster.length; i++ ) {
            if ( tempRoster[i] == null ) {
                break;
            }
            if ( tempRoster[i].getCreditCompleted() >= 120 ) {
                System.out.println(tempRoster[i].toString());
            }
        }
    }

    /**
     * Prints student enrollment array along with tuition
     */
    public void printWithTuition(){
        DecimalFormat dFormat = new DecimalFormat("#,###.00");
        if ( studentRoster.empty() ) {
            System.out.println("Student roster is empty!");
            return;
        }
        EnrollStudent[] studentArr = enrollment.getEnrollStudents();
        int arrSize = enrollment.getSize();
        System.out.println("** Tuition due **");
        for ( int i = 0; i < arrSize; i++ ) {
            if ( studentArr[i] == null ) {
                break;
            }
            Profile tempProfile = studentArr[i].getProfile();
            String status = studentRoster.containsProfile(tempProfile).getStatus();
            int enrolledCred = studentArr[i].getCreditsEnrolled();
            double tuitionDue = studentRoster.containsProfile(tempProfile).tuitionDue(enrolledCred);
            System.out.println( tempProfile.toString() + " (" + status + ")" + " enrolled " + enrolledCred +
                    " credits: "  + "tuition due: $" + dFormat.format(tuitionDue) );
        }
        System.out.println("* end of tuition due *");

    }

    /**
     * The method checks if the amount entered is valid or not and returns true or false.
     * @param userInput the amount entered by user.
     * @return false if the amount entered is not valid otherwise true.
     */
    public boolean validAmount(String userInput){
        int maxAmount = 10000;
        String[] studentInfo = userInput.split("\\s+");
        try {
            int amount = Integer.parseInt(studentInfo[4]);
            if ( amount > 10000  || amount <= 0 ) {
                System.out.println(amount+ ": invalid amount.");
                return false;
            }
            return true;
        } catch ( NumberFormatException e ) {
            System.out.println("Amount is not an integer.");
            return false;
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println ("0: invalid amount.");
            return false;
        }
    }

    /**
     * The method updates scholarship for the enrolled student.
     * @param inputText the value that needs to be added to the scholarship.
     */
    public void updateScholarship(String inputText){

        String[] studentInfo = inputText.split("\\s+");

        try {
            Profile tempProfile = new Profile(studentInfo[2], studentInfo[1], new Date(studentInfo[3]));
            EnrollStudent student = enrollment.findProfile(tempProfile);
            int scholarship;
            if (studentRoster.containsProfile(tempProfile) == null) {
                System.out.println(tempProfile.toString() + " is not in the roster.");
                return;
            } else if (!isNumeric(studentInfo[4])) {
                System.out.println("Amount is not an integer.");
                return;
            } else  {
                scholarship = Integer.parseInt(studentInfo[4]);
            }
            if (!(studentRoster.containsProfile(tempProfile).getStatus().equals("Resident"))) {
                System.out.println(tempProfile.toString() + " (Non-Resident) is not eligible for the scholarship.");
                return;
            } else if ( student.getCreditsEnrolled() < 12) {
                System.out.println(tempProfile.toString() + " part time student is not eligible for the scholarship.");
                return;
            } else if (!validAmount(inputText)) {
                return;
            }
            if (studentRoster.containsProfile(tempProfile).getCreditCompleted() < 12) {
                System.out.println(tempProfile.toString() + " part time student is not eligible for the scholarship.");
                return;
            }
            studentRoster.updateScholarshipStudent(studentRoster.containsProfile(tempProfile), scholarship);
            System.out.println(tempProfile.toString() + ": scholarship amount updated.");
        }catch( ArrayIndexOutOfBoundsException e ){
            System.out.println("Missing data in line command.");
        }
    }

    /**
     *
     * @param student the credits of student that needs to be verified if they are valid or not.
     * @return true if number of credits is valid otherwise false.
     */
    public boolean correctCredits(EnrollStudent student){
        int minCredits = 3;
        int maxCredits = 24;
        int minCreditInternational = 12;

        Profile tempProfile = student.getProfile();
        if ( studentRoster.containsProfile(tempProfile) == null ) {
            return true;
        }
            String status = studentRoster.containsProfile(tempProfile).getStatus();
        if ( status.equals("Resident") ) {
            if (student.getCreditsEnrolled() < minCredits || student.getCreditsEnrolled() > maxCredits) {
                System.out.println("(" + status + ") " + student.getCreditsEnrolled() + ": invalid credit hours.");
                return false;
            }
        } else if ( status.equals("Non-Resident") ) {
            if (student.getCreditsEnrolled() < minCredits || student.getCreditsEnrolled() > maxCredits) {
                System.out.println("(" + status + ") " + student.getCreditsEnrolled() + ": invalid credit hours.");
                return false;
            }
        } else if ( status.equals("Tri-state CT")  || status.equals("Tri-state NY") ) {
            if (student.getCreditsEnrolled() < minCredits || student.getCreditsEnrolled() > maxCredits) {
                System.out.println("(" + status + ") " + student.getCreditsEnrolled() + ": invalid credit hours.");
                return false;
            }
        } else if ( status.equals("International studentstudy abroad") ) {
            if (student.getCreditsEnrolled() < minCredits || student.getCreditsEnrolled() > minCreditInternational ) {
                System.out.println("(" + status + ") " + student.getCreditsEnrolled() + ": invalid credit hours.");
                return false;
            }
        } else if ( status.equals("International student") ) {
            if (student.getCreditsEnrolled() < minCreditInternational || student.getCreditsEnrolled() > maxCredits) {
                System.out.println("(" + status + ") " + student.getCreditsEnrolled() + ": invalid credit hours.");
                return false;
            }
        }
        return true;
    }

    /**
     * Method enrolls the student into enrollment array if it is valid and doesn't preexist.
     * @param inputText The input value of a student that needs to be added.
     */
    public void enrollStudent( String inputText ){
        String[] studentInfo = inputText.split("\\s+");

        try{

        int credits;
        if ( !(isNumeric(studentInfo[4])) ) {
            System.out.println("Credits enrolled is not an integer.");
            return;
        } else {
            credits = Integer.parseInt(studentInfo[4]);
        }
        Profile tempProfile = new Profile(studentInfo[2], studentInfo[1], new Date(studentInfo[3]));
        EnrollStudent student = new EnrollStudent(tempProfile, credits);

        if ( !correctCredits(student) ) {
            return;
        }

        if ( studentRoster.containsProfile(tempProfile) == null ) {
            System.out.println("Cannot enroll: " + tempProfile.toString() + " is not in the roster.");
            return;
        }

        int index = enrollment.findEnrollmentProfile(tempProfile);
        if ( index != -1 ) {
            enrollment.updateEnrollment(index, credits);
            System.out.println(tempProfile.toString() + " enrolled " + credits + " credits");
            return;
        }
        enrollment.add(student);
        System.out.println(tempProfile.toString() + " enrolled " + credits + " credits");
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println("Missing data in line command.");
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

        if ( date.compareTo(currentDate) >= ZERO || !date.isValid() ) {
            System.out.println("DOB invalid: " + date.toString() + " not a valid calendar date!");
            return false;
        } else if ( currentDate.tooYoung(date) ) {
            System.out.println("DOB invalid: " + date.toString() + " younger than 16 years old.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * The student that needs to be dropped from enrollment array.
     * @param inputText the student entered by user that needs to be removed from enrollment if it exists.
     */
    public void dropEnrollment(String inputText) {
        String[] studentInfo = inputText.split("\\s+");

        if ( enrollment.empty() ) {
            System.out.println("Enrollment is empty!");
            return;
        }

        Profile tempProfile = new Profile(studentInfo[2], studentInfo[1], new Date(studentInfo[3]));
        EnrollStudent student = enrollment.findProfile(tempProfile);
        if ( student == null ) {
            System.out.println(tempProfile.toString() + " is not enrolled.");
            return;
        }
        enrollment.remove(student);
        System.out.println(tempProfile.toString() + " dropped.");
    }

    /**
     * The student that needs to be removed from roster if it exists.
     * @param inputText The student that needs to be removed from roster if it exists.
     */
    public void dropRoster(String inputText) {
        String[] studentInfo = inputText.split("\\s+");

        if ( studentRoster.empty() ) {
            System.out.println("Student roster is empty!");
            return;
        }

        Profile tempProfile = new Profile(studentInfo[2], studentInfo[1], new Date(studentInfo[3]));
        EnrollStudent student = enrollment.findProfile(tempProfile);
        if ( studentRoster.containsProfile(tempProfile) == null ) {
            System.out.println(tempProfile.toString() + " is not in the roster.");
            return;
        }
        studentRoster.remove(studentRoster.containsProfile(tempProfile));
        System.out.println(tempProfile.toString() + " removed from the roster.");
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

    /**
     * Checks if the credits entered is valid or not.
     * @param credits credits value that needs to be checked.
     * @return true if credits value is true otherwise false.
     */
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
        for ( Major majorTypes : Major.values() ) {
            if ( majorTypes.name().equals(major.toUpperCase()) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adding a resident student to the roster if it doesn't exist.
     * @param details the student details entered by user that needs to be added.
     */
    private void addResStudent(String details) {
        String[] studentInfo = details.split("\\s+");

        try{
            Date date = new Date(studentInfo[3]);
            int credits;

            if ( !isNumeric(studentInfo[5]) ) {
                System.out.println("Credits completed invalid: not an integer!");
                return;
            } else {
                credits = Integer.parseInt(studentInfo[5]);
            }
            if ( !isPositive(credits) ) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return;
            } else if ( !dateCheck(date) ) {
                return;
            } else if ( !validMajor(studentInfo[4]) ) {
                return;
            }
            String major = studentInfo[4].toUpperCase();
            Profile resProfile = new Profile ( studentInfo[2], studentInfo[1], date );
            student.Resident resStudent = new student.Resident (resProfile, Major.valueOf(major), credits, 0);
            if ( studentRoster.contains(resStudent) ) {
                System.out.println( resProfile.toString() + " is already in the roster.");
                return;
            }
            studentRoster.add(resStudent);
            EnrollStudent newEnroll = new EnrollStudent(resProfile, credits);
            System.out.println( resProfile.toString() + " added to the roster.");
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println("Missing data in line command.");
        }
    }

    /**
     * Adding a non-resident student to the roster if it doesn't exist.
     * @param details the student details entered by user that needs to be added.
     */
    private void addNonResStudent(String details){
        String[] studentInfo = details.split("\\s+");
        try {
            Date date = new Date(studentInfo[3]);
            int credits;

            if ( !isNumeric(studentInfo[5]) ) {
                System.out.println("Credits completed invalid: not an integer!");
                return;
            } else {
                credits = Integer.parseInt(studentInfo[5]);
            }
            if ( !isPositive(credits) ) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return;
            } else if ( !dateCheck(date) ) {
                return;
            } else if ( !validMajor(studentInfo[4]) ) {
                return;
            }
            String major = studentInfo[4].toUpperCase();
            Profile nResProfile = new Profile ( studentInfo[2], studentInfo[1], date );
            NonResident nResStudent = new NonResident (nResProfile, Major.valueOf(major), credits);
            if ( studentRoster.contains(nResStudent) ) {
                System.out.println( nResProfile.toString() + " is already in the roster.");
                return;
            }
            studentRoster.add(nResStudent);
            EnrollStudent newEnroll = new EnrollStudent(nResProfile, credits);
            System.out.println( nResProfile.toString() + " added to the roster.");
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println("Missing data in line command.");
        }

    }


    /**
     * Helper method to check if the tristate entered is valid or not.
     * @param state the state entered by user that needs to be checked.
     * @return true if state is valid, otherwise false.
     */
    private boolean validState(String state) {
        if ( state.equals("NY") || state.equals("CT") ) {

            return true;
        } else {
            System.out.println(state + ": Invalid state code.");
            return false;
        }

    }

    /**
     * Checks if all the parameters are present in details when adding a tristate student.
     * @param details the details od student that needs to be checked.
     * @return true if all parameters exist in details otherwise false.
     */
    private boolean triStateError(String details) {
        String[] studentInfo = details.split("\\s+");
        String state;
        String lName;
        String fName;
        Date date;
        String major;
        String credits;

        try {
            lName = studentInfo[2];
            fName = studentInfo[1];
            date = new Date(studentInfo[3]);
            credits = studentInfo[5];
            major = studentInfo[4];
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println("Missing data in line command.");
            return true;
        }
        try {
            state = studentInfo[6];
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println("Missing the state code.");
            return true;
        }
        return false;
    }

    /**
     * Adding a tristate student to the roster
     * @param details the student details entered by user that needs to be added.
     */
    private void addTriStateStudent(String details){
        String[] studentInfo = details.split("\\s+");

            if( triStateError(details) ) {
                return;
            }
            int creditsTaken;
            String state = studentInfo[6].toUpperCase();
            String major = studentInfo[4];
            Date date = new Date(studentInfo[3]);
            if ( !isNumeric(studentInfo[5]) ) {
                System.out.println("Credits completed invalid: not an integer!");
                return;
            } else {
                creditsTaken = Integer.parseInt(studentInfo[5]);
            }
            if ( !isPositive(creditsTaken) ) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return;
            } else if ( !dateCheck(new Date(studentInfo[3])) ) {
                return;
            } else if ( !validMajor(studentInfo[4]) ) {
                return;
            } else if ( !validState(state) ) {
                return;
            }
            major = studentInfo[4].toUpperCase();
            Profile triProfile = new Profile ( studentInfo[2], studentInfo[1], date );
            TriState triStudent = new TriState ( triProfile, Major.valueOf(major), creditsTaken, state );
            if ( studentRoster.contains(triStudent) ) {
                System.out.println( triProfile.toString() + " is already in the roster.");
                return;
            }
            studentRoster.add(triStudent);
            EnrollStudent newEnroll = new EnrollStudent(triProfile, creditsTaken);
            System.out.println( triProfile.toString() + " added to the roster.");
    }

    /**
     * Checks if the student is study abroad international student or not.
     * @param studentInfo the student that needs to be checked.
     * @return true if student is a study abroad student otherwise false.
     */
    public boolean isStudyAbroad(String[] studentInfo) {

        try {
            if ( studentInfo[6].equals("true") ){
                return true;
            } else if ( studentInfo[6].equals("false") ) {
                return false;
            }
            return false;
        } catch ( ArrayIndexOutOfBoundsException e ) {
            return false;
        }

    }

    /**
     * Adding an international student to the roster
     * @param details the student details entered by user that needs to be added.
     */
    public void addInternationalStudent(String details) {
        String[] studentInfo = details.split("\\s+");
        boolean studyAbroad;
        try {
            Date date = new Date(studentInfo[3]);
            int credits;
            if ( !isNumeric(studentInfo[5]) ) {
                System.out.println("Credits completed invalid: not an integer!");
                return;
            } else {
                credits = Integer.parseInt(studentInfo[5]);
            }
            if ( !isPositive(credits) ) {
                System.out.println("Credits completed invalid: cannot be negative!");
                return;
            } else if ( !dateCheck(date) ) {
                return;
            } else if ( !validMajor(studentInfo[4]) ) {
                return;
            }
            studyAbroad = isStudyAbroad(studentInfo);
            String major = studentInfo[4].toUpperCase();
            Profile interProfile = new Profile ( studentInfo[2], studentInfo[1], date );
            International interStudent = new International ( interProfile, Major.valueOf(major), credits,
                    studyAbroad );
            if ( studentRoster.contains(interStudent) ) {
                System.out.println( interProfile.toString() + " is already in the roster.");
                return;
            }
            studentRoster.add(interStudent);
            EnrollStudent newEnroll = new EnrollStudent(interProfile, credits);
            System.out.println( interProfile.toString() + " added to the roster.");
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println("Missing data in line command.");
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

        if ( studentRoster.containsProfile(profile) == null ) {
            System.out.println(profile.toString() + " " + "is not in the roster.");

        } else if ( validMajor(studentInfo[4]) ) {
            studentRoster.changeMajor(profile, major);
            System.out.println(profile.toString() + " major changed to " + major);

        } else {
            System.out.println("Major code invalid:" + " " + major);
        }
    }

    /**
     * The method reads student from a text file and adds them to roster.
     * @param textFile the file that has the list of students that is being added to roster.
     * @throws FileNotFoundException if the file doesn't exist.
     */
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
                EnrollStudent resEnroll = new EnrollStudent(resProfile, Integer.parseInt(studentInfo[5]));
                studentRoster.add(resStudent);
            } else if ( studentInfo[0].equals("N") ) {
                String major = studentInfo[4].toUpperCase();
                Profile nResProfile = new Profile ( studentInfo[2], studentInfo[1], new Date(studentInfo[3]) );
                NonResident nresStudent = new NonResident (nResProfile, Major.valueOf(major),
                        Integer.parseInt(studentInfo[5]));
                EnrollStudent nResEnroll = new EnrollStudent(nResProfile, Integer.parseInt(studentInfo[5]));
                studentRoster.add(nresStudent);
            } else if ( studentInfo[0].equals("T") ) {
                String major = studentInfo[4].toUpperCase();
                Profile triProfile = new Profile ( studentInfo[2], studentInfo[1], new Date(studentInfo[3]) );
                TriState triStudent = new TriState (triProfile, Major.valueOf(major),
                        Integer.parseInt(studentInfo[5]), studentInfo[6]);
                EnrollStudent triEnroll = new EnrollStudent(triProfile, Integer.parseInt(studentInfo[5]));
                studentRoster.add(triStudent);
            } else if ( studentInfo[0].equals("I") ) {
                String major = studentInfo[4].toUpperCase();
                Profile interProfile = new Profile ( studentInfo[2], studentInfo[1], new Date(studentInfo[3]) );
                International interStudent = new International (interProfile, Major.valueOf(major),
                        Integer.parseInt(studentInfo[5]), Boolean.parseBoolean(studentInfo[6]) );
                EnrollStudent interEnroll = new EnrollStudent(interProfile, Integer.parseInt(studentInfo[5]));
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

        while ( input.hasNextLine() ) {

            String inputText = input.nextLine();

            try {
                if ( inputText.equals("") ) {
                    continue;
                } else if ( inputText.substring(ZERO, ONE).equals("Q")  ) {
                    System.out.println("Tuition Manager terminated.");
                    System.exit(ZERO);
                } else if ( inputText.substring(ZERO, ONE).equals("R") || inputText.substring(ZERO, ONE).equals("D") ||
                        inputText.equals("P") || inputText.equals("PS") ||  inputText.equals("SE") || inputText.substring(ZERO, ONE).equals("E") ||
                        inputText.substring(ZERO, ONE).equals("S") || inputText.equals("PC") || inputText.substring(ZERO, TWO).equals("LS") ||
                        inputText.substring(ZERO, ONE).equals("C") || inputText.substring(ZERO, TWO).equals("AR") ||
                        inputText.substring(ZERO, TWO).equals("AN") || inputText.substring(ZERO, TWO).equals("AT") ||
                        inputText.substring(ZERO, TWO).equals("AI") || inputText.substring(ZERO, TWO).equals("PT") ||
                        inputText.substring(ZERO, TWO).equals("PE") ) {
                    input(inputText);
                } else {
                    System.out.println(inputText + " " + "is an invalid command!");
                }
            } catch ( StringIndexOutOfBoundsException e ) {
                System.out.println(inputText + " " + "is an invalid command!");
            }


        }
    }
}

