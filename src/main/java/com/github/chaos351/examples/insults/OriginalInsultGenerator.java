package com.github.chaos351.examples.insults;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/7/13
 * Time: 1:25 PM
 *
 * @author Steven Benitez
 */
public class OriginalInsultGenerator implements InsultGenerator {
    private List<String> nouns = new ArrayList<String>();
    private List<String> adjectives = new ArrayList<String>();

    public OriginalInsultGenerator() {
        adjectives.add("stinky");
        adjectives.add("funky");
        adjectives.add("ugly");
        adjectives.add("fat");
        adjectives.add("big-headed");
        adjectives.add("buck-toothed");
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
    }

    @Override
    public String insultMe() {
        String adjective1 = pickRandom(adjectives);
        String adjective2 = pickRandom(adjectives);
        String noun = pickRandom(nouns);

        return "You're a " + adjective1 + ", " + adjective2 + " " + noun + "!";
    }

    private String pickRandom(List<String> randoms) {
        return randoms.get((int) (Math.random() * randoms.size()));
    }
}
