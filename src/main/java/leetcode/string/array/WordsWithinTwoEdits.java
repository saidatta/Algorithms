package leetcode.string.array;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/words-within-two-edits-of-dictionary/description/
public class WordsWithinTwoEdits {
    /**
     * Main method to find queries that can be transformed into a dictionary word within two edits.
     *
     * @param queries The array of query words.
     * @param dictionary The array of dictionary words.
     * @return List of query words that can match dictionary words within two edits.
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> matchingQueries = new ArrayList<>();
        char[][] dictionaryChars = convertToCharArray(dictionary);

        for (String query : queries) {
            if (isMatchWithinTwoEdits(query.toCharArray(), dictionaryChars)) {
                matchingQueries.add(query);
            }
        }

        return matchingQueries;
    }

    /**
     * Converts an array of strings to a 2D char array for efficient access.
     *
     * @param dictionary The array of dictionary words.
     * @return 2D char array representing the dictionary words.
     */
    private char[][] convertToCharArray(String[] dictionary) {
        char[][] dictionaryChars = new char[dictionary.length][dictionary[0].length()];

        for (int i = 0; i < dictionary.length; i++) {
            dictionaryChars[i] = dictionary[i].toCharArray();
        }

        return dictionaryChars;
    }

    /**
     * Checks if the query can be transformed into any word in the dictionary within two edits.
     *
     * @param queryChars The query word in char array form.
     * @param dictionaryChars The dictionary words in 2D char array form.
     * @return true if the query matches any dictionary word within two edits, false otherwise.
     */
    private boolean isMatchWithinTwoEdits(char[] queryChars, char[][] dictionaryChars) {
        for (char[] dictWordChars : dictionaryChars) {
            if (canTransformWithinTwoEdits(queryChars, dictWordChars)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the query can be transformed into the given dictionary word within two edits.
     *
     * @param queryChars The query word in char array form.
     * @param dictWordChars The dictionary word in char array form.
     * @return true if the query can match the dictionary word within two edits, false otherwise.
     */
    private boolean canTransformWithinTwoEdits(char[] queryChars, char[] dictWordChars) {
        int editCount = 0;
        for (int i = 0; i < queryChars.length; i++) {
            if (queryChars[i] != dictWordChars[i]) {
                editCount++;
                if (editCount > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordsWithinTwoEdits wordsWithinTwoEdits = new WordsWithinTwoEdits();
        String[] queries1 = {"word", "note", "ants", "wood"};
        String[] dictionary1 = {"wood", "joke", "moat"};
        System.out.println(wordsWithinTwoEdits.twoEditWords(queries1, dictionary1)); // Output: ["word", "note", "wood"]

        String[] queries2 = {"yes"};
        String[] dictionary2 = {"not"};
        System.out.println(wordsWithinTwoEdits.twoEditWords(queries2, dictionary2)); // Output: []
    }
}
