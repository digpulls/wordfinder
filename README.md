# wordfinder

Main class at: 
[com.dig.core.LongestWordFinder](src/main/java/com/dig/core/LongestWordFinder.java)

Takes a sentence as input and returns an instance of [Result](src/main/java/com/dig/core/Result.java) with the longest word found in a sentence and it's length.

#### Usage:


    `Result result = new LongestWordFinder()
                        .fromSentence("The cow jumped over the moon.")
                        .findLongestWordAndLength()
    System.out.println(result.getLongestWord());
    System.out.println(result.getLength());`
    
    
#### Output:

`> jumped`

`> 6`

To run tests, import as a maven project and run:

`# mvn test`

#CirecleCI build: [master](https://circleci.com/gh/digpulls/wordfinder/tree/master)
