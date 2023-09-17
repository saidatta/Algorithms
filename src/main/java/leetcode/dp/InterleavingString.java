package leetcode.dp;
/**
 * https://leetcode.com/problems/interleaving-string/#/description
 *
 * A class to check if a string s3 is formed by the interleaving of s1 and s2.
 */
public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        return isInterleaved(s1.toCharArray(), s2.toCharArray(), s3.toCharArray());
    }

    private boolean isInterleaved(char[] str1, char[] str2, char[] str3) {
        // If lengths of the two strings do not sum up to the third, return false
        if (str1.length + str2.length != str3.length) {
            return false;
        }

        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];

        for (int i = 0; i <= str1.length; i++) {
            for (int j = 0; j <= str2.length; j++) {
                // If both strings are empty
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                }
                // If str1 is empty
                else if (i == 0) {
                    dp[i][j] = (str3[i + j - 1] == str2[j - 1]) && dp[i][j - 1];
                }
                // If str2 is empty
                else if (j == 0) {
                    dp[i][j] = (str3[i + j - 1] == str1[i - 1]) && dp[i - 1][j];
                }
                // If current character of str3 matches current character of str1, but
                // not current character of str2
                else if (str3[i + j - 1] == str1[i - 1] && str3[i + j - 1] != str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j];
                }
                // If current character of str3 matches current character of str2, but
                // not current character of str1
                else if (str3[i + j - 1] != str1[i - 1] && str3[i + j - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i][j - 1];
                }
                // If current character of str3 matches both current characters of
                // str1 and str2
                else if (str3[i + j - 1] == str1[i - 1] && str3[i + j - 1] == str2[j - 1]) {
                    dp[i][j] = (dp[i - 1][j] || dp[i][j - 1]);
                }
            }
        }
        return dp[str1.length][str2.length];
    }

    /**
     * Determines whether s3 can be formed by interleaving s1 and s2.
     *
     * @param s1 First input string.
     * @param s2 Second input string.
     * @param s3 Target string to be checked.
     * @return true if s3 can be formed by interleaving s1 and s2, false otherwise.
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        // Quick checks
        if (s3.length() != s1.length() + s2.length()) return false;
        if (s3.length() == 0) return true;
        if (s1.length() == 0) return s3.equals(s2);
        if (s2.length() == 0) return s3.equals(s1);

        // Dynamic Programming table to store computed subproblems
        int[][] res = new int[s1.length()][s2.length()];

        return isPossible(s1, s2, s3, 0, 0, 0, res);
    }

    /**
     * Recursive function to check interleaving.
     *
     * @param s1 First input string.
     * @param s2 Second input string.
     * @param s3 Target string.
     * @param i Current index of s1.
     * @param j Current index of s2.
     * @param k Current index of s3.
     * @param res DP table for memoization.
     * @return true if interleaving is possible, false otherwise.
     */
    private boolean isPossible(String s1, String s2, String s3, int i, int j, int k, int[][] res) {

        // Base cases
        if (k == s3.length()) return i == s1.length() && j == s2.length();
        if (i >= s1.length()) return isPrefix(s2, s3, j, k);
        if (j >= s2.length()) return isPrefix(s1, s3, i, k);

        // If already computed, retrieve from table
        if (res[i][j] != 0) {
            return res[i][j] == 1;
        }

        // Checking interleaving with current character of s1
        if (s3.charAt(k) == s1.charAt(i) && isPossible(s1, s2, s3, i + 1, j, k + 1, res)) {
            res[i][j] = 1;
            return true;
        }

        // Checking interleaving with current character of s2
        if (s3.charAt(k) == s2.charAt(j) && isPossible(s1, s2, s3, i, j + 1, k + 1, res)) {
            res[i][j] = 1;
            return true;
        }

        // Interleaving not possible with current character of s1 or s2
        res[i][j] = -1;
        return false;
    }

    /**
     * Check if str starts with prefix starting from given indices.
     *
     * @param str The main string.
     * @param prefix The prefix string.
     * @param a Index to start from in the main string.
     * @param b Index to start from in the prefix.
     * @return true if str starts with prefix from the given indices, false otherwise.
     */
    private boolean isPrefix(String str, String prefix, int a, int b) {
        while (a < str.length() && b < prefix.length()) {
            if (str.charAt(a) != prefix.charAt(b)) return false;
            a++;
            b++;
        }
        return a == str.length() && b == prefix.length();
    }

    public static void main(String[] args) {
        InterleavingString interleavingString = new InterleavingString();

        System.out.println(interleavingString.isInterleave("a", "b", "a"));
    }
}
