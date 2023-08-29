package leetcode.string;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/longest-palindrome/#/description
 * <p>
 * Created by venkatamunnangi on 5/16/17.
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        HashSet<Character> hs = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (hs.contains(s.charAt(i))) {
                hs.remove(s.charAt(i));
                count++;
            } else {
                hs.add(s.charAt(i));
            }
        }
        if (!hs.isEmpty()) {
            return count * 2 + 1;
        }
        return count * 2;
    }
}
