package leetcode.dp.string;

// https://leetcode.com/problems/palindrome-partitioning-ii/
public class PalindromePartitioningII {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int[] cut = new int[n];

        for (int j = 0; j < n; j++) {
            cut[j] = j; // max number of cuts
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;

                    // If string is palindrome from start (i = 0) no cuts are needed. Else, cut it where palindrome ends.
                    cut[j] = i == 0 ? 0 : Math.min(cut[j], cut[i - 1] + 1);
                }
            }
        }

        return cut[n - 1];
    }

    public static void main(String[] args) {
        PalindromePartitioningII solver = new PalindromePartitioningII();

        String test1 = "aab";
        String test2 = "a";
        String test3 = "ab";

        System.out.println("Minimum cuts for " + test1 + " : " + solver.minCut(test1)); // Expected output: 1
        System.out.println("Minimum cuts for " + test2 + " : " + solver.minCut(test2)); // Expected output: 0
        System.out.println("Minimum cuts for " + test3 + " : " + solver.minCut(test3)); // Expected output: 1
    }
}
