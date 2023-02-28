package student;

/**
 * Enum class that stores major, major code and school name.
 *
 * @author Raghunandan Wable
 * @author Srikumar Avirneni
 */
public enum Major {
    CS("01:198", "SAS"),
    MATH("01:640", "SAS"),
    EE("14:332", "SOE"),
    ITI("04:547", "SC&I"),
    BAIT("33:136", "RBS");

    private final String majorCode;   // Major Code of the Major that that applies to the students Major
    private final String schoolName; // Name of the school that the major is in

    /**
     * This method creates a Major enum object that contains major code and school name for the major enum object
     *
     * @param majorCode  the code of major from major constant values
     * @param schoolName to school name of major from major constant values
     */
    Major(String majorCode, String schoolName) {
        this.majorCode = majorCode;
        this.schoolName = schoolName;
    }

    /**
     * Returns the major code parameter for the object.
     *
     * @return int representing the major code
     */
    public String getMajorCode() {
        return majorCode;
    }

    /**
     * Returns the school name parameter for the object.
     *
     * @return string representing the school name
     */
    public String getSchoolName() {
        return schoolName;
    }
}