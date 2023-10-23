package leetcode.dp.string;

/**
 * This problem aims to count all palindromic substrings within a given string.
 * A palindromic substring is a subset of characters in a string that reads the same backwards as forwards.
 *
 * Link to problem: https://leetcode.com/problems/palindromic-substrings/
 */
public class PalindromicSubstrings {

    /**
     * Count all palindromic substrings within the given string.
     *
     * @param s The input string
     * @return The count of palindromic substrings
     */
    public int countSubstrings(String s) {
        int totalPalindromes = 0;

        for (int i = 0; i < s.length(); i++) {
            // For odd-length palindromes, we use a single character as a center (e.g., "aba").
            totalPalindromes += countPalindromesAroundCenter(s, i, i);

            // For even-length palindromes, we use two consecutive characters as a center (e.g., "abba").
            totalPalindromes += countPalindromesAroundCenter(s, i, i + 1);
        }

        return totalPalindromes;
    }

    /**
     * Given a center (or centers in case of even-length), count all palindromes that can be constructed using this
     * center.
     *
     * @param str The input string
     * @param left The left boundary of the palindrome center
     * @param right The right boundary of the palindrome center
     * @return The count of palindromic substrings around the given center
     */
    private int countPalindromesAroundCenter(String str, int left, int right) {
        int palindromeCount = 0;

        // Expand around the center until we reach the boundary or find non-palindromic characters
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            // Move left pointer outwards
            left--;
            // Move right pointer outwards
            right++;

            // Current substring (bounded by left and right) is a palindrome
            palindromeCount++;
        }

        return palindromeCount;
    }
}
