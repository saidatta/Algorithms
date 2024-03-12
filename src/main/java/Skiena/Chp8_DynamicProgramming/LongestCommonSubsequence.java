package Skiena.Chp8_DynamicProgramming;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * https://www.youtube.com/watch?v=Ua0GhsJSlWM
 * Created by venkatamunnangi on 2/21/17.
 */
public class LongestCommonSubsequence {

    // text1 = "abcde", text2 = "ace"
    public int lcsDynamic(char[] str1, char[] str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        int[][] result = new int[str1.length + 1][str2.length + 1];
        int max = 0;

        for (int i = 1; i < result.length; i++) {
            for (int j = 1; j < result[0].length; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    result[i][j] = result[i - 1][j - 1] + 1;
                } else {
                    result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]);
                }

                if (result[i][j] > max) {
                    max = result[i][j];
                }
            }
        }

        return max;
    }

    public int longestCommonSubsequence(String text1, String text2) {

        // Make a grid of 0's with text2.length() + 1 columns
        // and text1.length() + 1 rows.
        int[][] dpGrid = new int[text1.length() + 1][text2.length() + 1];

        // Iterate up each column, starting from the last one.
        for (int col = text2.length() - 1; col >= 0; col--) {
            for (int row = text1.length() - 1; row >= 0; row--) {
                // If the corresponding characters for this cell are the same...
                if (text1.charAt(row) == text2.charAt(col)) {
                    dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1];
                    // Otherwise they must be different...
                } else {
                    dpGrid[row][col] = Math.max(dpGrid[row + 1][col], dpGrid[row][col + 1]);
                }
            }
        }

        // The original problem's answer is in dp_grid[0][0]. Return it.
        return dpGrid[0][0];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String str1 = "ABCDGHLQR";
        String str2 = "AEDPHR";

        int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
        System.out.print(result);
    }

}
