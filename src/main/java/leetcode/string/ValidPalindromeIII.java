package leetcode.string;

// https://leetcode.com/problems/valid-palindrome-iii/description/
public class ValidPalindromeIII {
    public boolean isValidPalindrome(String s, int k) {
        int[] memo = new int[s.length()];

        // To store the previous required values from memo.
        int temp, prev;

        // Generate all combinations of `i` and `j` in the correct order.
        for (int i = s.length() - 2; i >= 0; i--) {
            // 'prev' stores the value at memo[i+1][j-1];
            prev = 0;
            for (int j = i + 1; j < s.length(); j++) {
                // Store the value of memo[i+1][j] temporarily.
                temp = memo[j];

                // Case 1: Character at `i` equals character at `j`
                if (s.charAt(i) == s.charAt(j)) {
                    memo[j] = prev;
                } else {
                    // Case 2: Character at `i` does not equal character at `j`.
                    // Either delete character at `i` or delete character at `j`
                    // and try to match the two pointers using recursion.
                    // We need to take the minimum of the two results and add 1
                    // representing the cost of deletion.

                    // memo[j] will contain the value for memo[i+1][j]
                    // memo[j-1] will contain the value for memo[i][j-1]
                    memo[j] = 1 + Math.min(memo[j], memo[j - 1]);
                }

                // Copy the value of memo[i+1][j] to `prev`
                // For the next iteration when j=j+1
                // `prev` will hold the value memo[i+1][j-1];
                prev = temp;
            }
        }

        // Return true if the minimum cost to create a palindrome by only deleting the letters
        // is less than or equal to `k`.
        return memo[s.length() - 1] <= k;
    }

    public static void main(String[] args) {
        ValidPalindromeIII sol = new ValidPalindromeIII();
        System.out.println(sol.isValidPalindrome("abcdeca", 2)); // true
        System.out.println(sol.isValidPalindrome("abbababa", 1)); // true
    }
}
