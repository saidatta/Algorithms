package Leetcode.String;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/#/description
 *
 * Created by venkatamunnangi on 11/30/16.
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()) {
            return s;
        }
        int curLen = 0;
        int start = -1;
        char[] array = s.toCharArray();
        for(int i = 0; i < array.length; i++) {
            if(isPalindrome(array, i - curLen - 1, i)) {
                start = i - curLen - 1;
                curLen += 2;
            } else if (isPalindrome(array, i - curLen, i)) {
                start = i - curLen;
                curLen += 1;
            }
        }
        return new String(array, start, curLen);
    }

    private boolean isPalindrome(char[] c, int s, int e) {
        if(s < 0) {
            return false;
        }

        while (s < e) {
            if(c[s++] != c[e--]) {
                return false;
            }
        }
        return true;
    }
}
