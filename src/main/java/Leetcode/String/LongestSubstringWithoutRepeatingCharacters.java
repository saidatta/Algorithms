package Leetcode.String;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/#/description
 *
 # Examples:

 # Given "abcabcbb", the answer is "abc", which the length is 3.

 # Given "bbbbb", the answer is "b", with the length of 1.

 # Given "pwwkew", the answer is "wke", with the length of 3.
 *
 * Created by venkatamunnangi on 11/29/16.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        String sb = "";
        int max = 0;
        // o(n)
        for(int i = 0; i < s.length() ; i++) {
            char x = s.charAt(i);
            if(!sb.contains(""+x)) {
                sb += (s.charAt(i));
            } else {
                if(max < sb.length()) {
                    max = sb.length();
                }

                // find the repeating occurence in max string.
                int index = sb.indexOf(x);
                // then substring after that.
                sb = sb.substring(index + 1) + x;
            }
        }

        if(max < sb.length()) {
            return sb.length();
        }

        return max;
    }
}
