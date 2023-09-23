package leetcode.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-string-chain/editorial
public class LongestStringChain {
//    To solve this problem, you can use dynamic programming. The key insight is that if wordA is a predecessor of
//    wordB, then the maximum word chain length ending in wordB is one more than the maximum word chain length ending
//    in wordA.
//
//    Here's a step-by-step breakdown:
//
//    Sort the words by length because a shorter word can never be a predecessor of a longer word.
//    Use a hashmap (or dictionary in some languages) to keep track of the longest word chain for each word.
//    For each word, loop over each possible predecessor (by removing one letter from the word) and update the maximum
//    word chain length for the current word.
//    Keep track of the global maximum word chain length.
    public static int longestStrChain(String[] words) {
        // Sort words based on their lengths
        Arrays.sort(words, Comparator.comparingInt(String::length));

        // A map to store the longest chain with the word as the endpoint
        Map<String, Integer> dp = new HashMap<>();
        int longestWordChain = 0;

        for (String word : words) {
            // Initialize to 1 for every word as a word itself is a valid chain
            dp.put(word, 1);
            for (int i = 0; i < word.length(); i++) {
                // Construct a potential predecessor
                StringBuilder sb = new StringBuilder(word);
                String predecessor = sb.deleteCharAt(i).toString();
                if (dp.containsKey(predecessor) && (dp.get(predecessor) + 1 > dp.get(word))) {
                    dp.put(word, dp.get(predecessor) + 1);
                }
            }
            longestWordChain = Math.max(longestWordChain, dp.get(word));
        }

        return longestWordChain;
    }

    public static void main(String[] args) {
        // Test Example 1
        String[] words1 = {"a", "b", "ba", "bca", "bda", "bdca"};
        System.out.println(longestStrChain(words1)); // Expected output: 4

        // Test Example 2
        String[] words2 = {"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"};
        System.out.println(longestStrChain(words2)); // Expected output: 5

        // Test Example 3
        String[] words3 = {"abcd", "dbqca"};
        System.out.println(longestStrChain(words3)); // Expected output: 1
    }
}
