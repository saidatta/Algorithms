package Leetcode.Design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/unique-word-abbreviation/#/description
 *
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
 * A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 *
 * Created by venkatamunnangi on 5/10/17.
 */
public class UniqueWordAbbrev {
    private final Map<String, Set<String>> abbrMap = new HashMap<>();

    public static void main(String... args) {
        UniqueWordAbbrev uniqueWordAbbrev = new UniqueWordAbbrev("deer", "door", "cake", "card");
        System.out.println(uniqueWordAbbrev.isUnique("dear"));
        System.out.println(uniqueWordAbbrev.isUnique("door"));
        System.out.println(uniqueWordAbbrev.isUnique("cart"));
        System.out.println(uniqueWordAbbrev.isUnique("cake"));
    }

    public UniqueWordAbbrev(String... dictionary) {
        for (String word : dictionary) {
            String abbr = getAbbr(word);
            abbrMap.computeIfAbsent(abbr, k -> new HashSet<>()).add(word);
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        Set<String> wordsWithAbbr = abbrMap.get(abbr);

        // If abbreviation is not present, or the only word having this abbreviation is the word itself, return true
        return wordsWithAbbr == null || (wordsWithAbbr.size() == 1 && wordsWithAbbr.contains(word));
    }

    private String getAbbr(String word) {
        if (word.length() <= 2) {
            return word;
        }
        // Compute abbreviation as first letter + length of the middle part + last letter
        return word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
    }
}
