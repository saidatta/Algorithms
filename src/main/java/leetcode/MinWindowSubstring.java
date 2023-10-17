package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/#/description
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * This solution aims to find the minimum window substring in the given source string
 * which contains all characters of the target string.
 *
 *  Created by venkatamunnangi on 3/27/17.
 */
public class MinWindowSubstring {

    public String minWindow(String source, String target) {
        // Initialize a map to store the occurrence count of characters in source and target
        Map<Character, Integer> charOccurrences = initializeOccurrencesMap(source);
        // Populate the map with the occurrences count from the target string
        int numOfTargetCharsToBeFound = populateOccurrencesMap(charOccurrences, target);

        // If there's any character in target not found in source, return an empty string
        if (numOfTargetCharsToBeFound == -1) {
            return "";
        }

        int windowStart = 0;
        int windowEnd = 0;
        int minStart = 0;
        int minLen = Integer.MAX_VALUE;

        // Use two pointers to create a moving window
        while (windowEnd < source.length()) {
            char currentChar = source.charAt(windowEnd);

            // If the current character is in the target string, reduce the count of the remaining characters to be found
            if (charOccurrences.containsKey(currentChar) && charOccurrences.get(currentChar) > 0) {
                numOfTargetCharsToBeFound--;
            }

            // Decrease the count for the current character as it's considered
            charOccurrences.computeIfPresent(currentChar, (key, val) -> val - 1);
            windowEnd++;

            // If all characters from the target string have been found
            while (numOfTargetCharsToBeFound == 0) {
                int currentWindowLength = windowEnd - windowStart;

                // Update minimum window information if current window is smaller
                if (minLen > currentWindowLength) {
                    minLen = currentWindowLength;
                    minStart = windowStart;
                }

                char startChar = source.charAt(windowStart);
                // Increase the count for the starting character of the window as it's being removed
                charOccurrences.computeIfPresent(startChar, (key, val) -> val + 1);

                // If removing the current character affects the required occurrences, update the count of characters to
                // be found
                if (charOccurrences.containsKey(startChar) && charOccurrences.get(startChar) > 0) {
                    numOfTargetCharsToBeFound++;
                }

                windowStart++;  // Move the window start
            }
        }

        // If the minimum length hasn't been changed, that means no window was found
        return minLen == Integer.MAX_VALUE ? "" : source.substring(minStart, minStart + minLen);
    }

    // Initialize a map with characters from the source string
    private Map<Character, Integer> initializeOccurrencesMap(String source) {
        Map<Character, Integer> occurrences = new HashMap<>();

        for (char c : source.toCharArray()) {
            occurrences.put(c, 0);
        }

        return occurrences;
    }

    // Populate occurrences from the target string into the map
    private int populateOccurrencesMap(Map<Character, Integer> charOccurrences, String target) {
        for (char c : target.toCharArray()) {
            // If the character isn't in the source string, return -1
            if (!charOccurrences.containsKey(c)) {
                return -1;
            }
            charOccurrences.computeIfPresent(c, (key, val) -> val + 1);
        }

        return target.length();
    }

    public static void main(String[] args) {
        MinWindowSubstring minWindowSubstring = new MinWindowSubstring();
        System.out.println(minWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));  // Expected output: "BANC"
    }
}

