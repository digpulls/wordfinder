package com.dig.tests;


import com.dig.core.BaseTest;
import com.dig.core.JsonSource;
import com.dig.core.LongestWordFinder;
import com.dig.core.Result;
import com.dig.tests.data.WordFinderTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordFinderTests extends BaseTest {

    /* Note: Ideally test data should be in parsed from external JSON files. And @JsonSource should take the resource path instead
     * But, for the sake of the test, I am including JSON content in the test class */

    @DisplayName("Test with valid input")
    @ParameterizedTest
    @JsonSource(type = WordFinderTestData.class, value = {
            "{sentence:'The cow jumped over the moon', " +
                    "expectedWord: 'jumped', " +
                    "testScenario: 'Basic test with sentence'}",
            "{sentence:'The cow jumped and lumped over the moon', " +
                    "expectedWord: 'jumped', " +
                    "testScenario: 'Sentence with multiple long words with same length'}",
            "{sentence:'\uD83E\uDD11 \uD83E\uDD11', " +
                    "expectedWord: '\uD83E\uDD11', " +
                    "testScenario: 'Checker can process Unicode'}",
            "{sentence:'Try with a superrrrrrrrrrrrLongggggggggggggggWorddddddddddddd', " +
                    "expectedWord: 'superrrrrrrrrrrrLongggggggggggggggWorddddddddddddd', " +
                    "testScenario: 'Check with a very long word'}",
            "{sentence:'Hey', " +
                    "expectedWord: 'Hey', " +
                    "testScenario: 'Check with a single word'}",
            "{sentence:'%20 $$ + ! # \" $ %', " +
                    "expectedWord: '%20', " +
                    "testScenario: 'Check with some special characters'}"

    })
    void canFindLongestWord(WordFinderTestData data) {
        getLogger().info("Executing test scenario: {}", data.getTestScenario());
        Result result = new LongestWordFinder().fromSentence(data.getSentence()).findLongestWordAndLength();

        assertEquals(data.getExpectedWord(), result.getLongestWord());
        assertEquals(Integer.valueOf(data.getExpectedWord().length()), result.getLength());
    }

    @DisplayName("Test with invalid input")
    @ParameterizedTest
    @ValueSource(strings = {
            "         ",    // Long whitespaces
            "",             // Empty String
            "null"          // Null value
    })
    void cannotFindLongestWord(String data) {
        getLogger().info("Executing test scenario with data: '{}'", data);
        assertThrows(IllegalArgumentException.class,
                () -> new LongestWordFinder()
                        .fromSentence("null".equals(data) ? null : data)
                        .findLongestWordAndLength());
    }
}
