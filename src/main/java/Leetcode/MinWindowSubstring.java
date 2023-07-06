package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/#/description
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class MinWindowSubstring {
    public String minWindow(String source, String target) {
        Map<Character, Integer> charOccurrences = initializeOccurrencesMap(source);
        int numOfTargetCharsToBeFound = populateOccurrencesMap(charOccurrences, target);

        if (numOfTargetCharsToBeFound == -1) {
            return "";
        }

        int windowStart = 0;
        int windowEnd = 0;
        int minStart = 0;
        int minLen = Integer.MAX_VALUE;

        while (windowEnd < source.length()) {
            char currentChar = source.charAt(windowEnd);

            if (charOccurrences.containsKey(currentChar) && charOccurrences.get(currentChar) > 0) {
                numOfTargetCharsToBeFound--;
            }

            charOccurrences.computeIfPresent(currentChar, (key, val) -> val - 1);
            windowEnd++;

            while (numOfTargetCharsToBeFound == 0) {
                int currentWindowLength = windowEnd - windowStart;
                if (minLen > currentWindowLength) {
                    minLen = currentWindowLength;
                    minStart = windowStart;
                }

                char startChar = source.charAt(windowStart);
                charOccurrences.computeIfPresent(startChar, (key, val) -> val + 1);

                if (charOccurrences.containsKey(startChar) && charOccurrences.get(startChar) > 0) {
                    numOfTargetCharsToBeFound++;
                }

                windowStart++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : source.substring(minStart, minStart + minLen);
    }

    private Map<Character, Integer> initializeOccurrencesMap(String source) {
        Map<Character, Integer> occurrences = new HashMap<>();

        for (char c : source.toCharArray()) {
            occurrences.put(c, 0);
        }

        return occurrences;
    }

    private int populateOccurrencesMap(Map<Character, Integer> charOccurrences, String target) {
        for (char c : target.toCharArray()) {
            if (charOccurrences.containsKey(c)) {
                charOccurrences.computeIfPresent(c, (key, val) -> val + 1);
            } else {
                return -1;
            }
        }

        return target.length();
    }

    private void decrementCount(Map<Character, Integer> charOccurrences, char c) {
        charOccurrences.put(c, charOccurrences.get(c) - 1);
    }

    private void incrementCount(Map<Character, Integer> charOccurrences, char c) {
        charOccurrences.put(c, charOccurrences.get(c) + 1);
    }

    public static void main(String[] args) {
        MinWindowSubstring minWindowSubstring = new MinWindowSubstring();
        System.out.println(minWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
    }
}

