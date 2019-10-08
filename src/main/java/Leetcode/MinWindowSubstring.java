package Leetcode;

import java.util.HashMap;

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
        HashMap<Character, Integer> TgtOccurrencesInSource = new HashMap<>();
        for (char c : source.toCharArray()) {
            // occurrence map from source
            TgtOccurrencesInSource.put(c, 0);
        }

        for (char c : target.toCharArray()) {
            // map how many target occurences within source.
            if (TgtOccurrencesInSource.containsKey(c))
                TgtOccurrencesInSource.put(c, TgtOccurrencesInSource.get(c) + 1);
            else
                return "";
        }

        int windowStart = 0, windowEnd = 0, minStart = 0, minLen = Integer.MAX_VALUE,
                numOfTargetCharsNeedToBeFoundInSource = target.length();

        while (windowEnd < source.length()) {
            char c1 = source.charAt(windowEnd);

            // If char in src exists in target, decrease counter
            if (TgtOccurrencesInSource.get(c1) > 0) {
                numOfTargetCharsNeedToBeFoundInSource--;
            }

            // Decrease TgtOccurrencesInSource count. If char does not exist in target,
            // TgtOccurrencesInSource will be negative.
            TgtOccurrencesInSource.put(c1, TgtOccurrencesInSource.get(c1) - 1);
            windowEnd++;

            // When we found a valid window, move windowStart to find smaller window.
            // // when a valid window is found - numOfTargetCharsNeedToBeFoundInSource == 0.
            while (numOfTargetCharsNeedToBeFoundInSource == 0) {
                int currentWindowLength = windowEnd - windowStart;
                if (minLen > currentWindowLength) {
                    minLen = currentWindowLength;
                    minStart = windowStart;
                }

                char c2 = source.charAt(windowStart);
                TgtOccurrencesInSource.put(c2, TgtOccurrencesInSource.get(c2) + 1);

                // When char exists in target, increase counter.
                if (TgtOccurrencesInSource.get(c2) > 0) {
                    numOfTargetCharsNeedToBeFoundInSource++;
                }

                windowStart++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : source.substring(minStart, minStart + minLen);
    }

    public static void main(String [] args) {
        MinWindowSubstring minWindowSubstring = new MinWindowSubstring();
        System.out.println(minWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
    }
}
