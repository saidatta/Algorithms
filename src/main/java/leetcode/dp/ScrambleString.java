package leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/scramble-string/#/description
 *
 * We say that "rgtae" is a scrambled string of "great".
 *
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 *
 * Created by venkatamunnangi on 4/23/17.
 */
public class ScrambleString {

    private final Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        String key = s1 + " " + s2;
        if (memo.containsKey(key)) return memo.get(key);

        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int count : letters) {
            if (count != 0) return false;
        }

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))
                    || isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        ScrambleString scramble = new ScrambleString();
        System.out.println(scramble.isScramble("great", "rgeat")); // Output: true
        System.out.println(scramble.isScramble("abcde", "caebd")); // Output: false
        System.out.println(scramble.isScramble("a", "a")); // Output: true
    }
}
