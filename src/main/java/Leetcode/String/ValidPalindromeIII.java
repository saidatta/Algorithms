package Leetcode.String;

// https://leetcode.com/problems/valid-palindrome-iii/description/
public class ValidPalindromeIII {

//    A string is a k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
//    This implies that the string and its reverse must have a common subsequence of length >= len(s) - k.
//
//    By finding the length of the longest common subsequence between s and its reverse, s_reverse, we can determine if
//    s is a k-palindrome by checking.
    // O(n**2), space -o(n)
    public boolean isValidPalindrome(String s, int k) {
        int n = s.length();
        return n - longestCommonSubseq(s, new StringBuilder(s).reverse().toString()) <= k;
    }

    private int longestCommonSubseq(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        ValidPalindromeIII sol = new ValidPalindromeIII();
        System.out.println(sol.isValidPalindrome("abcdeca", 2)); // true
        System.out.println(sol.isValidPalindrome("abbababa", 1)); // true
    }
}
