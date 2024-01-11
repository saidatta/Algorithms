package leetcode.string.traversal;

import java.util.HashMap;
import java.util.Map;

public class LargestSubstringTwoEqualCharacters {
    public int maxLengthBetweenEqualCharacters(String s) {
        Map<Character, Integer> firstOccurrence = new HashMap<>();
        int maxLength = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If the character is seen for the first time, store its index
            if (!firstOccurrence.containsKey(ch)) {
                firstOccurrence.put(ch, i);
            } else {
                // If the character is seen again, calculate the length of the substring
                // Subtract 1 to exclude the two equal characters
                maxLength = Math.max(maxLength, i - firstOccurrence.get(ch) - 1);
            }
        }

        return maxLength;
    }
}
