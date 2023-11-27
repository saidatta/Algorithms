package leetcode.string.slidingwindow;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
public class FindAllAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) {
            return result;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Count frequencies of characters in p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        // Sliding window over s
        for (int i = 0; i < s.length(); i++) {
            // Add current character to the current window
            sCount[s.charAt(i) - 'a']++;

            // Remove character left of the window
            if (i >= p.length()) {
                sCount[s.charAt(i - p.length()) - 'a']--;
            }

            // Check if a current window is an anagram of p
            if (i >= p.length() - 1 && isAnagram(sCount, pCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    private boolean isAnagram(int[] sCount, int[] pCount) {
        for (int i = 0; i < 26; i++) {
            if (sCount[i] != pCount[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FindAllAnagrams solution = new FindAllAnagrams();
        System.out.println(solution.findAnagrams("cbaebabacd", "abc")); // Output: [0, 6]
        System.out.println(solution.findAnagrams("abab", "ab")); // Output: [0, 1, 2]
    }
}
