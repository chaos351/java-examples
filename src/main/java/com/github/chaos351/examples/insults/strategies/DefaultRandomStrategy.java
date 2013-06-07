package com.github.chaos351.examples.insults.strategies;

/**
 * Date: 6/7/13
 * Time: 1:59 PM
 *
 * @author Steven Benitez
 */
public class DefaultRandomStrategy implements RandomStrategy {
    @Override
    public int pickRandom(int size) {
        return (int) (Math.random() * size);
    }
}
