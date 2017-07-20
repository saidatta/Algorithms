package Leetcode.String;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-anagram/#/description
 *
 * Created by venkatamunnangi on 4/2/17.
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null) {
            return false;
        }

        if(s.length() != t.length()) {
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
        if(s == null || t == null) {
            return false;
        }

        if(s.length() != t.length()) {
            return false;
        }

        int[] freq = new int[26];

        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for(char c : t.toCharArray()) {
            freq[c - 'a']--;
            if(freq[c - 'a'] < 0) {
                return false;
            }
        }

        for(int i : freq) {
            if( i != 0) {
                return false;
            }
        }

        return true;
    }
}
