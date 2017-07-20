package Leetcode.String;

/**
 * https://leetcode.com/problems/longest-common-prefix/#/description
 *
 * Created by venkatamunnangi on 12/2/16.
 */
public class LongestCommonPrefix {
        public String longestCommonPrefix(String[] strs) {
            if(strs.length == 0) {
                return "";
            }
            String longestCommonPrefix = strs[0];
            for(int i = 1 ;i<strs.length;i++) {
                longestCommonPrefix = lcpHelper(longestCommonPrefix, strs[i]);
            }

            return longestCommonPrefix;
        }

        String lcpHelper(String longestCommonPrefix, String targetString) {

            if(longestCommonPrefix.isEmpty() || targetString.isEmpty()) {
                return "";
            }

            // get shortest string length
            int len = longestCommonPrefix.length() > targetString.length() ? targetString.length() : longestCommonPrefix.length();

            StringBuilder ans = new StringBuilder();
            for(int i = 0; i < len; i++) {
                if (longestCommonPrefix.charAt(i) == targetString.charAt(i)) {
                    ans.append(longestCommonPrefix.charAt(i));
                } else {
                    return ans.toString();
                }
            }

            return (ans.length() == 0) ? "" :ans.toString();

        }
}
