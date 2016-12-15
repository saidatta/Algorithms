package Leetcode.String;

/**
 * Created by venkatamunnangi on 11/30/16.
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()) {
            return s;
        }

        int max = 0, start = 0,e = 0;

        for(int i = 0; i< s.length();i++) {
            if(isPalindrome(s, i - max - 1, s.length())) {
                if(i <  max) {

                }
            }
        }
        return "";
    }

    public boolean isPalindrome(String str, int s, int e) {
        if(s < 0) {
            return false;
        }
        char [] c = str.toCharArray();

        while (s < e) {
            if(c[s++] != c[e--]) {
                return false;
            }
        }
        return true;
    }
}
