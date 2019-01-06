package com.dig.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Assumptions:
 * <p>
 * 1. Empty and Null input will be rejected.
 * 2. "     " white spaces are rejected.
 */
public class LongestWordFinder {

    private String sentence;
    private Logger logger = LogManager.getLogger(LongestWordFinder.class);

    public LongestWordFinder fromSentence(String sentence) {
        this.sentence = sentence;
        return this;
    }

    /**
     * Finds the longest word in the sentence
     */
    public Result findLongestWordAndLength() {
        Result result = null;

        if (Objects.isNull(sentence) || sentence.trim().isEmpty()) {
            logger.error("Empty or null user Input: '{}'", sentence);
            throw new IllegalArgumentException("Input cannot be null or empty! Provide a valid sentence.");
        }
        try {
            String longestWord = Arrays.stream(sentence.trim().split(" "))
                    .max(Comparator.comparingInt(String::length))
                    .orElseThrow(Exception::new);

            logger.debug("From sentence '{}' found longest word '{}' with length {}",
                    sentence,
                    longestWord,
                    longestWord.length());

            result = new Result()
                    .setLongestWord(longestWord)
                    .setLength(longestWord.length());
        } catch (Exception ex) {
            logger.error("Something went wrong!", ex);
        }
        return result;
    }
}
