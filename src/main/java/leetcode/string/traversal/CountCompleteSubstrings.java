package leetcode.string.traversal;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/count-complete-substrings/
public class CountCompleteSubstrings {
    // Main method to count complete substrings in the given word
    public int countCompleteSubstrings(String word, int k) {
        int totalCompleteSubstrings = 0;

        // Iterate over possible unique character counts (from 1 to 26)
        for (int uniqueCharCount = 1; uniqueCharCount <= 26; uniqueCharCount++) {
            // Increment the total count by the count of complete substrings of a specific length
            totalCompleteSubstrings += countCompleteSubstrings(word, k, 0, uniqueCharCount * k);
        }

        return totalCompleteSubstrings;
    }

    // Helper method to count complete substrings of a specific length starting from a given index
    private int countCompleteSubstrings(String word, int k, int start, int length) {
        // If the remaining length of the word from 'start' is less than 'length', return 0
        if (word.length() - start < length) {
            return 0;
        }

        int[] charFrequencyMap = new int[26]; // Array to track the frequency of each character
        int validCharCount = 0; // Counts characters that have exactly 'k' occurrences in the current window
        int completeSubstringCount = 0; // Accumulates the count of complete substrings

        // Iterate through the word starting from 'start'
        for (int i = start; i < word.length(); i++) {
            // If adjacent characters have a difference greater than 2, recursively check the rest of the string
            if (i > start && Math.abs(word.charAt(i) - word.charAt(i - 1)) > 2) {
                return completeSubstringCount + countCompleteSubstrings(word, k, i, length);
            }

            // Update validCharCount and charFrequencyMap based on the current character
            validCharCount += charFrequencyMap[word.charAt(i) - 'a'] == k - 1 ? 1 : 0;
            validCharCount -= charFrequencyMap[word.charAt(i) - 'a']++ == k ? 1 : 0;

            // When the end of the window is reached
            if (i >= start + length - 1) {
                // Add to completeSubstringCount if all characters in the window have exactly 'k' occurrences
                completeSubstringCount += validCharCount == length / k ? 1 : 0;

                // Update validCharCount for the character leaving the window
                validCharCount += charFrequencyMap[word.charAt(i - length + 1) - 'a'] == k + 1 ? 1 : 0;
                validCharCount -= charFrequencyMap[word.charAt(i - length + 1) - 'a']-- == k ? 1 : 0;
            }
        }

        return completeSubstringCount;
    }

    public static void main(String[] args) {
        CountCompleteSubstrings solution = new CountCompleteSubstrings();
        System.out.println(solution.countCompleteSubstrings("gvgvvgv", 2)); // Output: 3
        System.out.println(solution.countCompleteSubstrings("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvarogvar", 230)); // Output: 3

        System.out.println(solution.countCompleteSubstrings("aaa", 1)); // Output: 3
        System.out.println(solution.countCompleteSubstrings("igigee", 2)); // Output: 3
        System.out.println(solution.countCompleteSubstrings("aaabbbccc", 3)); // Output: 6
    }
}
