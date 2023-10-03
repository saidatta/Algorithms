package leetcode.string;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/valid-anagram/#/description
 *
 * Created by venkatamunnangi on 4/2/17.
 */
public class ValidAnagram {
    public boolean isAnagramUnicode(String s, String t) {
        // If the strings are of different lengths, they cannot be anagrams.
        if (s.length() != t.length()) {
            return false;
        }

        // Use a HashMap to keep track of character frequencies.
        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();

        // Populate the character frequencies for the string 's'.
        for (char charFromS : s.toCharArray()) {
            charFrequencyMap.put(charFromS, charFrequencyMap.getOrDefault(charFromS, 0) + 1);
        }

        // Check the characters in 't' against the map.
        for (char charFromT : t.toCharArray()) {
            // If the character from 't' is not in the map, then 's' and 't' are not anagrams.
            if (!charFrequencyMap.containsKey(charFromT)) {
                return false;
            }

            // If the frequency of a character becomes 0 after decrementing, remove it from the map.
            if (charFrequencyMap.get(charFromT) == 1) {
                charFrequencyMap.remove(charFromT);
            } else {
                charFrequencyMap.put(charFromT, charFrequencyMap.get(charFromT) - 1);
            }
        }

        // If the map is empty, then 's' and 't' are anagrams.
        return charFrequencyMap.isEmpty();
    }


    public boolean isAnagramUnicode2(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        if (s.length() != t.length()) {
            return false;
        }

        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        String s1 = String.valueOf(c1);
        String s2 = String.valueOf(c2);

        return s1.equals(s2);
    }

    public boolean validAanagram2(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        if (s.length() != t.length()) {
            return false;
        }

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
            if (freq[c - 'a'] < 0) {
                return false;
            }
        }

        for (int i : freq) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }
}
