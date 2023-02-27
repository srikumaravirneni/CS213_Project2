package manager;
import java.util.Calendar;

/**
 * This class handles the Date object which contains day month and year fields and acts as the birthday parameter for
 * a student.
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class Date implements Comparable<Date> {
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int NEGATIVE_ONE = -1;
    private int year;
    private int month;
    private int day;

    /**
     * This method creates a date object that contains parameters for the today's date.
     * This method is a no parameter constructor for the date object.
     */
    public Date() {
        Calendar currentDate = Calendar.getInstance();
        this.day = currentDate.get(Calendar.DATE);
        this.year = currentDate.get(Calendar.YEAR);
        this.month = currentDate.get(Calendar.MONTH);
    }

    /**
     * This method creates a date object with a string input that contains the date month and year.
     *
     * @param date the date in mm/dd/yyyy format as a string.
     */
    public Date(String date) {
        String[] dateSplit = date.split("/");
        this.month = Integer.parseInt(dateSplit[0]);
        this.day = Integer.parseInt(dateSplit[1]);
        this.year = Integer.parseInt(dateSplit[2]);
    }

    /*

     * This main method is serving as a testBed to test the isValid() class.
     *
     * @param args

    public static void main(String[] args) {
        System.out.println("Date isValid() Testbed running... \n");
        boolean expectedOutput;
        boolean actualOutput;
        Date dateTest1 = new Date("11/21/800");
        expectedOutput = false;
        actualOutput = dateTest1.isValid();
        testResult(dateTest1, expectedOutput, actualOutput);
        Date dateTest2 = new Date("2/29/2003");
        expectedOutput = false;
        actualOutput = dateTest2.isValid();
        testResult(dateTest2, expectedOutput, actualOutput);
        Date dateTest3 = new Date("13/31/2003");
        expectedOutput = false;
        actualOutput = dateTest3.isValid();
        testResult(dateTest3, expectedOutput, actualOutput);
        Date dateTest4 = new Date("4/31/2003");
        expectedOutput = false;
        actualOutput = dateTest4.isValid();
        testResult(dateTest4, expectedOutput, actualOutput);
        Date dateTest5 = new Date("-1/2/2007");
        expectedOutput = false;
        actualOutput = dateTest5.isValid();
        testResult(dateTest5, expectedOutput, actualOutput);
        Date dateTest6 = new Date("1/2/2007");
        expectedOutput = true;
        actualOutput = dateTest6.isValid();
        testResult(dateTest6, expectedOutput, actualOutput);
        Date dateTest7 = new Date("5/1/1996");
        expectedOutput = true;
        actualOutput = dateTest7.isValid();
        testResult(dateTest7, expectedOutput, actualOutput);
    }


     * This is a helper method for the testBed to help determine whether the expected output matches the expected input.
     *
     * @param date           date object to test.
     * @param expectedOutput the output the user expects to see.
     * @param actualOutput   the output returned.

    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput) {
        System.out.println(date.toString());
        System.out.println("Date isValid() method returns " + actualOutput);

        if (actualOutput == expectedOutput) {
            System.out.println("PASS.\n");
        } else {
            System.out.println("FAIL.\n");
        }
    }
    */
    /**
     * This method calculates whether the objects year is a leap year or not.
     *
     * @return boolean true for leap year and false for not a leap year.
     */
    private boolean isLeap() {
        if (this.year % QUADRENNIAL == ZERO) {
            if (this.year % CENTENNIAL == ZERO) {
                return this.year % QUATERCENTENNIAL == ZERO;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * This method calculates whether the given day in the date field is within the bounds of the number of days in the
     * given month.
     *
     * @param monthLength the amount of days in a given month.
     * @return boolean indicates true if the objects day field is within limits or returns false otherwise.
     */
    public boolean rightLength(int monthLength) {

        return this.day <= monthLength && this.month >= ONE;
    }

    /**
     * This method checks whether the given year is greater than 1900 and whether the objects month is correct, if the
     * month is correct, the method checks whether day parameter is within bounds for the given month.
     *
     * @return boolean true if the date is valid and false otherwise
     */
    public boolean isValid() {
        boolean yearType = isLeap();
        int longMonth = 31;
        int shortMonth = 30;
        int leapMonth = 29;
        int nonLeap = 28;
        int minYear = 1900;


        if (this.year < minYear) {
            return false;
        }

        if (this.month == Calendar.JANUARY + 1 || this.month == Calendar.MARCH + 1 || this.month == Calendar.MAY + 1 ||
                this.month == Calendar.JULY + 1 || this.month == Calendar.AUGUST + 1 || this.month == Calendar.OCTOBER
                + 1 || this.month == Calendar.DECEMBER + 1) {
            return rightLength(longMonth);
        } else if (this.month == Calendar.APRIL + 1 || this.month == Calendar.JUNE + 1 ||
                this.month == Calendar.SEPTEMBER + 1 || this.month == Calendar.NOVEMBER + 1) {
            return rightLength(shortMonth);
        }
        if (isLeap() && this.month == Calendar.FEBRUARY + 1) {
            return rightLength(leapMonth);
        } else if (!isLeap() && this.month == Calendar.FEBRUARY + 1) {
            return rightLength(nonLeap);
        }
        return false;
    }

    /**
     * Returns the year parameter for the object.
     *
     * @return int representing the year.
     */
    public int getYear() {
        return year;
    }

    /**
     * This method is used to set the year parameter in the date object.
     *
     * @param year the year that the user wants to set.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Returns the day parameter of the date object.
     *
     * @return int returns the day parameter.
     */
    public int getDay() {
        return day;
    }

    /**
     * This object sets the day parameter of the date object.
     *
     * @param day the value to set in the date object.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Returns the month parameter of the date object.
     *
     * @return int returns the month parameter.
     */
    public int getMonth() {
        return month;
    }

    /**
     * This object sets the month parameter of the date object.
     *
     * @param month the value to set in the date object.
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * This method overrides the compareTo method in the object superclass and is used to compare two date objects.
     *
     * @param other the object to be compared.
     * @return 0 for equal to, -1 for less than, 1 for greater than.
     */
    @Override
    public int compareTo(Date other) {
        int lessThan = -1;
        int greaterThan = 1;
        int equal = 0;

        if (this.year == other.getYear()) {
            if (this.month == other.getMonth()) {
                if (this.day == other.getDay()) {
                    return equal;
                } else if (this.day < other.getDay()) {
                    return lessThan;
                } else {
                    return greaterThan;
                }
            } else if (this.month < other.getMonth()) {
                return lessThan;
            } else {
                return greaterThan;
            }
        } else if (this.year < other.getYear()) {
            return lessThan;
        } else {
            return greaterThan;
        }
    }

    /**
     * This method checks whether the given date is less than 16 years old.
     *
     * @param other the entered date that will be compared with the date object with no parameters since it represents
     *              the current date.
     * @return boolean true if the student is too young and false otherwise.
     */
    public boolean tooYoung(Date other) {

        int ageLimit = 16;

        if (this.year - other.getYear() == ageLimit) {
            if (this.month == other.getMonth()) {
                return this.day < other.getDay();
            } else return this.month <= other.getMonth();
        } else return this.year - other.getYear() < ageLimit;
    }

    /**
     * This method overrides the superclass equals() methods and returns whether the given two date objects are equal.
     *
     * @param dateToCompare the date that needs to be compared.
     * @return boolean true if equal false otherwise.
     */
    @Override
    public boolean equals(Object dateToCompare) {

        if (!(dateToCompare instanceof Date date)) {
            return false;
        }

        return this.day == date.getDay() && this.month == date.getMonth() && this.year == date.getYear();
    }

    /**
     * This method overrides the superclass toString() method and prints the string representation of the date object.
     *
     * @return string representation of date object
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

}
