package com.github.chaos351.examples.insults;

import com.github.chaos351.examples.insults.strategies.DefaultRandomStrategy;
import com.github.chaos351.examples.insults.strategies.RandomStrategy;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 6/7/13
 * Time: 1:41 PM
 *
 * @author Steven Benitez
 */
public class BetterInsultGenerator implements InsultGenerator {
    private String pattern = "You''re a {0}, {1} {2}!";
    private RandomStrategy strategy = new DefaultRandomStrategy();

    private List<String> nouns = new ArrayList<String>();
    private List<String> adjectives = new ArrayList<String>();

    public BetterInsultGenerator() {
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

    public BetterInsultGenerator(List<String> adjectives, List<String> nouns) {
        this.adjectives = adjectives;
        this.nouns = nouns;
    }

    @Override
    public String insultMe() {
        String adjective1 = pickRandom(adjectives);
        String adjective2 = pickRandom(adjectives);
        String noun = pickRandom(nouns);

        return MessageFormat.format(pattern, adjective1, adjective2, noun);
    }

    private String pickRandom(List<String> randoms) {
        return randoms.get(strategy.pickRandom(randoms.size()));
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setStrategy(RandomStrategy strategy) {
        this.strategy = strategy;
    }
}
