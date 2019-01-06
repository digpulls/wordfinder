package com.dig.core;

public class Result {

    private String longestWord;
    private Integer length;

    public String getLongestWord() {
        return longestWord;
    }

    public Result setLongestWord(String longestWord) {
        this.longestWord = longestWord;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public Result setLength(Integer length) {
        this.length = length;
        return this;
    }
}
