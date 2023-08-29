package leetcode.dp;

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

    // N ^ 4?
    // great, rgeat
    // same string is scrambled?
    // upper case?
    // unicode?
    public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2)) {
            return true;
        }

        int[] f1 = new int[26];

        for(int i = 0; i<s1.length(); i++) {
            f1[s1.charAt(i) -'a']++;
            f1[s2.charAt(i) -'a']--;
        }

        for(int i = 0; i<26; i++) {
            if(f1[i] != 0) {
                return false;
            }
        }

        for(int i = 1; i < s1.length(); i++) {
            // left child - left child
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }

            // left child - right children
            if( isScramble(s1.substring(0,i), s2.substring(s1.length()-i)) &&
                    isScramble(s1.substring(i), s2.substring(0,s1.length()-i))) {
                return true;
            }
        }

        return false;
    }
}
