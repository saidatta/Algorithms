package Leetcode.String;

/**
 * https://leetcode.com/problems/distinct-subsequences/#/description
 *
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * A subsequence of a string is a new string which is formed from the original string by
 * deleting some (can be none) of the characters without disturbing the relative positions
 * of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 *
 * Return 3.
 *
 * Created by venkatamunnangi on 4/5/17.
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        // array creation
        int[][] mem = new int[t.length()+1][s.length()+1];

        // filling the first row: with 1s
        for(int j=0; j<=s.length(); j++) {
            mem[0][j] = 1;
        }

        // the first column is 0 by default in every other rows but the first, which we need.

        for(int i=0; i<t.length(); i++) {
            for(int j=0; j<s.length(); j++) {
                if(t.charAt(i) == s.charAt(j)) {
                    mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
                } else {
                    mem[i+1][j+1] = mem[i+1][j];
                }
            }
        }

        return mem[t.length()][s.length()];
    }

    public static void main(String [] args) {
        DistinctSubsequences distinctSubsequences = new DistinctSubsequences();
        //System.out.println(distinctSubsequences.numDistinct());
    }
}
