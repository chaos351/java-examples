package com.github.chaos351.examples.datatypes;

import java.util.Date;

/**
 * A class representing a person.
 *
 * @author Steven Benitez
 * @see Gender
 */
public class Person {
    private final String name;
    private final Gender gender;
    private final Date dateOfBirth;

    /**
     * Instantiates a new {@link Person} object.
     *
     * @param name        The name of the person.
     * @param gender      The gender of the person.
     * @param dateOfBirth The date the person was born.
     */
    public Person(final String name, final Gender gender, final Date dateOfBirth) {
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The gender of the person.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @return The date of birth of the person.
     */
    public Date getDOB() {
        return dateOfBirth;
    }
}

