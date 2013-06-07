package com.github.chaos351.examples.insults.strategies;

/**
 * A strategy for generating random numbers.
 *
 * @author Steven Benitez
 */
public interface RandomStrategy {
    /**
     * Picks a random number between 0 and size.
     *
     * @param size The size of a collection.
     * @return A random number.
     */
    int pickRandom(int size);
}
