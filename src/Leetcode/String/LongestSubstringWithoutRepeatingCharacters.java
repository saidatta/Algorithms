package Leetcode.String;

/**
 * Created by venkatamunnangi on 11/29/16.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        String sb = "";
        int max = 0;
        for(int i = 0; i < s.length() ; i++) {
            char x = s.charAt(i);
            if(!sb.contains(""+x)) {
                sb += (s.charAt(i));
            } else {
                if(max < sb.length()) {
                    max = sb.length();
                }

                int index = sb.indexOf(x);
                sb = sb.substring(index + 1) + x;
            }
        }

        if(max < sb.length()) {
            return sb.length();
        }

        return max;
    }
}
