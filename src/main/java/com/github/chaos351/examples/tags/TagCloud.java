package com.github.chaos351.examples.tags;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

import java.util.Iterator;
import java.util.Set;

/**
 * A {@code TagCloud} (or weighted-list) is a collection of {@link Tag}s
 * whereby more frequently used tags appear in a larger font-size than less
 * frequently used tags.
 * <p/>
 * <h3>Creating Tag Clouds</h3>
 * {@code TagCloud}s are created using the builder pattern.
 * <p/>
 * <pre>{@code
 *   TagCloud cloud = TagCloud.builder()
 *           .minFontSize(12)
 *           .maxFontSize(32)
 *           .add("Apple", 101)
 *           .add("Raspberry", 400)
 *           .add("Strawberry", 856)
 *           .add("Banana", 27)
 *           .add("Pear", 87)
 *           .add("Orange", 8)
 *           .build();
 * }</pre>
 * <p/>
 * <h3>Iterating Tag Clouds</h3>
 * {@code TagCloud}s can be iterated over using the enhanced for-loop.
 * <p/>
 * <pre>{@code
 *   for (Tag tag : cloud) {
 *       ...
 *   }
 * }</pre>
 *
 * @author Steven Benitez
 * @see Tag
 */
public final class TagCloud implements Iterable<Tag> {
    private final Set<Tag> tagSet;

    private TagCloud(final Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public Iterator<Tag> iterator() {
        return tagSet.iterator();
    }

    /**
     * Creates a builder for creating a {@code TagCloud}. Builders should only
     * be used to create a single cloud.
     *
     * @return A new builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * A builder for creating a {@code TagCloud}.
     */
    public static class Builder {
        private static final int DEFAULT_MIN_FONT_SIZE = 10;
        private static final int DEFAULT_MAX_FONT_SIZE = 28;

        private final Multiset<String> tags = TreeMultiset.create();
        private int minFontSize = DEFAULT_MIN_FONT_SIZE;
        private int maxFontSize = DEFAULT_MAX_FONT_SIZE;

        Builder() {
        }

        /**
         * Adds the specified string to the cloud created when {@link #build()}
         * is called. Repeated calls to this method with the same string will
         * be cumulative, such that {@code add(tag, 10)} and
         * {@code add(tag, 15)} would be the same as calling
         * {@code add(tag, 25)}.
         *
         * @param tag    The tag to add.
         * @param weight The weight of the tag.
         * @return The builder instance (for chaining).
         */
        public Builder add(final String tag, final int weight) {
            tags.add(tag, weight);
            return this;
        }

        /**
         * Sets the minimum font-size that a tag can have. Defaults to 10.
         *
         * @param minFontSize The minimum font size.
         * @return The builder instance (for chaining).
         */
        public Builder minFontSize(final int minFontSize) {
            this.minFontSize = minFontSize;
            return this;
        }

        /**
         * Sets the maximum font-size that a tag can have. Defaults to 28.
         *
         * @param maxFontSize The maximum font size.
         * @return The builder instance (for chaining).
         */
        public Builder maxFontSize(final int maxFontSize) {
            this.maxFontSize = maxFontSize;
            return this;
        }

        /**
         * Builds the {@code TagCloud} using the configuration from this
         * builder.
         *
         * @return The {@code TagCloud}.
         */
        public TagCloud build() {
            ImmutableSet.Builder<Tag> builder = ImmutableSet.builder();

            int minOccurs = -1;
            int maxOccurs = -1;

            for (String tag : tags.elementSet()) {
                int o = tags.count(tag);
                if (minOccurs == -1 || minOccurs > o) {
                    minOccurs = o;
                }

                if (maxOccurs == -1 || maxOccurs < o) {
                    maxOccurs = o;
                }

                int fontSize = calculateFontSize(o, minOccurs, maxOccurs);
                Tag t = new Tag(tag, fontSize);
                builder.add(t);
            }

            return new TagCloud(builder.build());
        }

        /**
         * Used to calculate the font-size of a tag using a
         * <a href="http://blogs.dekoh.com/dev/2007/10/29/choosing-a-good-font-size-variation-algorithm-for-your-tag-cloud/">linear-mapping formula</a>.
         *
         * @param occurrences The number of times a tag occurs in the cloud.
         * @param minOccurs   The minimum number of occurrences of any tag in the cloud.
         * @param maxOccurs   The maximum number of occurrences of any tag in the cloud.
         * @return The calculated font size for the tag.
         */
        private int calculateFontSize(final int occurrences, final int minOccurs, final int maxOccurs) {
            double weight = (double) (occurrences - minOccurs) / (maxOccurs - minOccurs);
            return (int) (minFontSize + Math.round((maxFontSize - minFontSize) * weight));
        }
    }
}
