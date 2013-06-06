package com.github.chaos351.examples.datatypes;

import java.io.Serializable;
import java.text.ParseException;
import java.util.regex.Pattern;

/**
 * A Social Security Number.
 * <p/>
 * In the United States, a Social Security number (SSN) is a nine-digit number
 * issued to U.S. citizens, permanent residents, and temporary (working)
 * residents under section 205(c)(2) of the Social Security Act, codified as
 * 42 U.S.C. ¤ 405(c)(2). The number is issued to an individual by the Social
 * Security Administration, an independent agency of the United States
 * government. Its primary purpose is to track individuals for taxation
 * purposes.
 * <p/>
 * http://en.wikipedia.org/wiki/Social_security_number
 * http://www.ssnusa.com/html/ssn_nine_digit.html
 *
 * @author Steven Benitez
 */
public class SSN implements Comparable<SSN>, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * This only verifies that an SSN is of the correct format and does not
     * guarantee that the actual number is authentic. e.g., 000-00-0000 would
     * pass this check but is not valid.
     */
    private static final Pattern VALID_SSN_FORMAT
            = Pattern.compile("[0-9]{3}\\-[0-9]{2}\\-[0-9]{4}");

    // todo: should these be shorts?

    private final String area;
    private final String group;
    private final String serial;

    private transient String toString;

    /**
     * Opting for a private constructor for a few reasons:
     * (1) This class is immutable, so with no public constructor we have the
     * freedom to reuse instances in the future, for performance.
     * (2) Disallows subclassing.
     */
    private SSN(String area, String group, String serial) {
        this.area = area;
        this.group = group;
        this.serial = serial;
    }

    /**
     * This portion of the SSN indicates the state or territory in which the
     * holder resided at the time that the SSN was issued. Each state and
     * territory has ben assigned unique area numbers.
     *
     * @return The area portion of the SSN (first three digits).
     */
    public String getArea() {
        return area;
    }

    /**
     * This portion of the SSN has no special geographic or data significance
     * but merely serves to break the SSN numbers for a given state into
     * conveniently sized blocks for orderly issuance.
     * <p/>
     * This portion of the number is key in performing authenticity checks.
     *
     * @return The group portion of the SSN (middle two digits).
     */
    public String getGroup() {
        return group;
    }

    /**
     * This portion of the SSN is the serial numbers. They may be any four digit
     * number between 0001 to 9999, and represent a straight numerical sequence
     * of numbers within the group.
     * <p/>
     * No valid SSNs will have a serial number of 0000. Beyond this, any other
     * serial numbers in a valid group and area are potentially valid.
     *
     * @return The serial portion of the SSN (last four digits).
     */
    public String getSerial() {
        return serial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SSN ssn = (SSN) o;

        if (!area.equals(ssn.area)) {
            return false;
        }
        if (!group.equals(ssn.group)) {
            return false;
        }
        if (!serial.equals(ssn.serial)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = area.hashCode();
        result = 31 * result + group.hashCode();
        result = 31 * result + serial.hashCode();
        return result;
    }

    @Override
    public int compareTo(final SSN ssn) {
        if (ssn == null) {
            return -1;
        }

        return toString().compareTo(ssn.toString());
    }

    @Override
    public String toString() {
        if (toString == null) {
            toString = area + "-" + group + "-" + serial;
        }
        return toString;
    }

    /**
     * Returns an SSN instance for the given string.
     *
     * @param ssn The social security number to parse.
     * @return An SSN object holding the value represented by the string object.
     * @throws ParseException If the SSN is not properly formatted (e.g., not
     *                        XXX-XX-XXXX) or the last four digits are all zeros.
     */
    public static SSN valueOf(String ssn) throws ParseException {
        if (ssn == null) {
            throw new NullPointerException("You must specify an SSN!");
        }

        if (!VALID_SSN_FORMAT.matcher(ssn).matches()) {
            throw new ParseException("Error parsing SSN.", -1);
        }

        String[] segments = ssn.split("-");

        // sb: 0000 is the only guaranteed invalid serial portion of an SSN.
        if ("0000".equals(segments[2])) {
            throw new ParseException("An SSN cannot end in four zeros.", 8);
        }

        return new SSN(segments[0], segments[1], segments[2]);
    }
}

