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

    //21 ms
    public int numDistinct2(String S, String T) {
        int sl = S.length();
        int tl = T.length();

        int [][] dp = new int[tl+1][sl+1];

        for(int i=0; i<=sl; ++i){
            dp[0][i] = 1;
        }

        for(int t=1; t<=tl; ++t){

            for(int s=1; s<=sl; ++s){
                if(T.charAt(t-1) != S.charAt(s-1)){
                    dp[t][s] = dp[t][s-1];
                }else{
                    dp[t][s] = dp[t][s-1] + dp[t-1][s-1];
                }
            }
        }

        return dp[tl][sl];
    }

    //15ms
    public int numDistinct_sdp(String S, String T) {
        int sl = S.length();
        int tl = T.length();

        int [] preComb = new int[sl+1];
        int [] comb = new int[sl+1];


        for(int i=0; i<=sl; i++)
            preComb[i] = 1;

        for(int t=1; t<=tl; ++t){
            for(int s=1; s<=sl; ++s){
                if(T.charAt(t-1) != S.charAt(s-1)){
                    comb[s] = comb[s-1];
                }else{
                    comb[s] = comb[s-1] + preComb[s-1];
                }
            }

            for(int i=0; i<=sl; ++i){
                preComb[i] = comb[i];
            }
        }

        return preComb[sl];
    }

    public static void main(String [] args) {
        DistinctSubsequences distinctSubsequences = new DistinctSubsequences();
        System.out.println(distinctSubsequences.numDistinct("rabbbit", "rabbit"));
    }
}
