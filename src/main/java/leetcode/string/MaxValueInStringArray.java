package leetcode.string;

/**
 * https://leetcode.com/problems/maximum-value-of-a-string-in-an-array/
 *
 * Approach
 * We keep running maximum for each string from the initial array. To check string value we do check each char,
 * if it's not within [0-9] limit it's letter so we return string length, otherwise we accumulate this char as next
 * digit in the number.
 *
 * Complexity
 * Time complexity:
 * O(n∗avg(stringlength))O(n*avg(string length))O(n∗avg(stringlength)) we check every string, for each string we check
 * eveery character. So overall it's proportional to. number of characters in all strings from array
 *
 * Space complexity:
 * O(1)O(1)O(1) few variable to keep state
 */


public class MaxValueInStringArray {
    public int maximumValue(String[] strs) {
        int max = 0;
        for (String s : strs) {
            max = Math.max(max, value(s));
        }
        return max;
    }

    int value(String s) {
        if (!s.chars().allMatch(Character::isDigit)) {
            return s.length();
        }
        return Integer.parseInt(s);
    }
}
