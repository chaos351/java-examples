package com.github.chaos351.examples.insults;

import com.github.chaos351.examples.insults.strategies.DifferentRandomStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/7/13
 * Time: 1:24 PM
 *
 * @author Steven Benitez
 */
public class InsultMain {
    public static void main(String[] args) {
        List<String> nouns = new ArrayList<String>();
        List<String> adjectives = new ArrayList<String>();

        adjectives.add("stinky");
        adjectives.add("funky");
        adjectives.add("ugly");
        adjectives.add("idiotic");
        adjectives.add("big-headed");
        adjectives.add("ridiculous");
        adjectives.add("freaky");

        nouns.add("gnome");
        nouns.add("dummy");
        nouns.add("fatty");
        nouns.add("turd");
        nouns.add("crack-head");
        nouns.add("pea-brain");
        nouns.add("dope");
        nouns.add("jerk");
        nouns.add("idiot");

        InsultGenerator generator = new BetterInsultGenerator(adjectives, nouns);
        ((BetterInsultGenerator) generator).setPattern("You are such a {0}, {1} {2}!!");
        ((BetterInsultGenerator) generator).setStrategy(new DifferentRandomStrategy());
        for (int i = 0; i < 5; i++) {
            System.out.println(generator.insultMe());
        }
    }
}
