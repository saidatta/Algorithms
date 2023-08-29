package leetcode.string;

import java.util.List;

// https://leetcode.com/problems/check-if-a-string-is-an-acronym-of-words/
public class CheckAcronymWords {
    public boolean isAcronym(List<String> words, String s) {
        // Create a StringBuilder to accumulate the characters
        StringBuilder acronym = new StringBuilder();

        // Iterate through each word and append the first character to the acronym
        for(String word : words) {
            acronym.append(word.charAt(0));
        }

        // Compare the acronym to the string s and return the result
        return acronym.toString().equals(s);
    }
}
