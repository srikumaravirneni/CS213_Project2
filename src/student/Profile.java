import Date;

/**
 * This class handles the Profile object which contains first name, last name and dob from date object.
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob;

    private static final int ZERO = 0;

    /**
     * Returns last name parameter for the object.
     *
     * @return string representing last name.
     */
    public String getLname() {
        return lname;
    }

    /**
     * Returns the first name parameter for the object.
     *
     * @return string representing first name.
     */
    public String getFname() {
        return this.fname;
    }

    /**
     * Returns dob parameter for the object.
     *
     * @return string representing date dob object.
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Constructor for profile class.
     * The constructor creates a profile object after making necessary changes to lastName and firstName values.
     *
     * @param lastName  last name of the profile being created.
     * @param firstName first name of the profile being created.
     * @param dob       date object for the profile being created.
     */
    public Profile(String lastName, String firstName, Date dob) {

        lastName = lastName.toLowerCase();
        firstName = firstName.toLowerCase();

        char[] lName = lastName.toCharArray();
        char[] fName = firstName.toCharArray();

        lName[ZERO] = Character.toUpperCase(lName[ZERO]);
        fName[ZERO] = Character.toUpperCase(fName[ZERO]);

        lastName = String.valueOf(lName);
        firstName = String.valueOf(fName);

        this.lname = lastName;
        this.fname = firstName;
        this.dob = dob;

    }

    /**
     * The method compares two profiles and returns if the profiles are same or less than or greater than each other.
     *
     * @param other the object to be compared.
     * @return the comparison between two profiles, i.e. returns value less than if it is less than, value greater
     * than 0 if it is greater than, or return 0 if value is equal to others object value.
     */
    @Override
    public int compareTo(Profile other) {
        int lastNameCompare = this.lname.compareTo(other.getLname());
        int firstNameCompare = this.fname.compareTo(other.getFname());
        dob.compareTo(other.getDob());
        if (lastNameCompare == ZERO) {
            if (firstNameCompare == ZERO) {
                return this.dob.compareTo(other.getDob());
            } else {
                return firstNameCompare;
            }
        } else {
            return lastNameCompare;
        }
    }

    /**
     * This method overrides the superclass equals() methods and returns whether the given two profile objects are equal
     *
     * @param profileToCompare the profile that needs to be compared.
     * @return boolean true if equal false otherwise.
     */
    @Override
    public boolean equals(Object profileToCompare) {

        if (!(profileToCompare instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) profileToCompare;

        return profile.getFname().equals(fname) && profile.getLname().equals(lname) && profile.getDob().equals(dob);
    }

    /**
     * This method overrides the superclass toString() method and prints the string representation of the profile
     * object.
     *
     * @return string representation of profile object.
     */
    @Override
    public String toString() {
        return fname + " " + lname + " " + dob.toString();
    }
}