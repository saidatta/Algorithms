package Leetcode.String;

import java.util.HashSet;
import java.util.Set;

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
            if(!sb.contains(Character.toString(x))) {
                sb += Character.toString(s.charAt(i));
            } else {
                if(max < sb.length()) {
                    max = sb.length();
                }

                // find the repeating occurence in max string.
                int index = sb.indexOf(x);
                // then substring after that occurence in the current result string.
                sb = sb.substring(index + 1) + x;
            }
        }

        if(max < sb.length()) {
            return sb.length();
        }

        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        int maxLen = 0;
        Set<Character> window = new HashSet<>();

        int left = 0, right = 0;
        while(right < s.length()) {
            while(window.contains(s.charAt(right)))
                window.remove(s.charAt(left++));
            window.add(s.charAt(right++));
            maxLen = Math.max(maxLen, right - left);
        }

        return maxLen;
    }

    class Solution {
        public long solution(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            //nndfddf
            String sb = "";
            int maxLen = 0;

            for (int i = 0; i < s.length(); i++) {
                char current = s.charAt(i);

                if (!sb.contains(Character.toString(current))) {
                    s += Character.toString(current);
                } else {
                    if (maxLen < sb.length()) {
                        maxLen = sb.length();
                    }

                    int firstOccurence = sb.indexOf(current);
                    sb = sb.substring(firstOccurence + 1) + current;
                }
            }

            if (maxLen < sb.length()) {
                maxLen = sb.length();
            }

            return maxLen;
        }
    }

    public static void main(String [] args) {
        LongestSubstringWithoutRepeatingCharacters longestSubstringWithoutRepeatingCharacters = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(longestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("pwwkew"));
    }
}
