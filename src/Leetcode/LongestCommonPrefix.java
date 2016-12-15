package Leetcode;

/**
 * Created by venkatamunnangi on 12/2/16.
 */
public class LongestCommonPrefix {
        public String longestCommonPrefix(String[] strs) {
            if(strs.length == 0) return "";
            String prev = strs[0];
            for(int i = 1 ;i<strs.length;i++) {
                prev = lcpHelper(prev, strs[i]);
            }

            return prev;
        }

        String lcpHelper(String s1, String s2) {

            if(s1.isEmpty() || s2.isEmpty()) return "";

            int len = s1.length() > s2.length() ? s2.length() : s1.length();

            StringBuilder ans = new StringBuilder();
            for(int i = 0; i < len; i++) {
                if (s1.charAt(i) == s2.charAt(i))
                    ans.append(s1.charAt(i));
                else {
                    return ans.toString();
                }
            }
            return (ans.length() == 0) ?"":ans.toString();

        }
}
