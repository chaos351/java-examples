package com.github.chaos351.examples.insults.strategies;

import java.util.Random;

/**
 * Date: 6/7/13
 * Time: 2:01 PM
 *
 * @author Steven Benitez
 */
public class DifferentRandomStrategy implements RandomStrategy {
    private Random random = new Random();

    @Override
    public int pickRandom(int size) {
        return random.nextInt(size);
    }

    public static void main(String[] args) {
        DifferentRandomStrategy strategy = new DifferentRandomStrategy();
        for (int i = 0; i < 25; i++) {
            int value = strategy.pickRandom(5);
            System.out.println(value);
        }
    }
}
