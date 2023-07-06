package Leetcode.String;

/**
 * https://leetcode.com/problems/is-subsequence/description/
 */
public class IsSubseq {
    public boolean isSubsequence(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();

        if(sLength > tLength) {
            return false;
        }

        int i = 0, j = 0;
        while(i < sLength && j < tLength) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }

        return i == sLength;
    }
}
