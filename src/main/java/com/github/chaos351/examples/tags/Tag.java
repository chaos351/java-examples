package com.github.chaos351.examples.tags;

/**
 * Represents a tag in a tag cloud. A tag is comprised of a name and a
 * font-size. Instances of this class are created automatically be the
 * {@code TagCloud}.
 *
 * @author Steven Benitez
 * @see TagCloud
 */
public final class Tag {
    private final String name;
    private final int fontSize;

    /**
     * Instantiates a new {@code Tag} with the provided name and fontSize.
     *
     * @param name     The name of the tag.
     * @param fontSize The font-size of the tag.
     */
    public Tag(final String name, final int fontSize) {
        this.name = name;
        this.fontSize = fontSize;
    }

    /**
     * Gets the name of this tag.
     *
     * @return The name of this tag.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the font-size of this tag. The font size should be measured in
     * pixels (px) or points (pt).
     *
     * @return The font-size of this tag.
     */
    public int getFontSize() {
        return fontSize;
    }
}