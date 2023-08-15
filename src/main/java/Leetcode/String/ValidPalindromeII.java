package Leetcode.String;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * Created by venkatamunnangi on 9/11/19.
 */
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindromic(s, l, r - 1) || isPalindromic(s, l + 1, r);
            }
            l++;
            r--;
        }
        return true;
    }

    public boolean isPalindromic(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
