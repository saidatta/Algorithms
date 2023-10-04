package leetcode.math.numbertheory;


//https://leetcode.com/problems/palindrome-number/description/
 public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        int reversed = reverse(x);
        return x == reversed;
    }

    private int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        return reversed;
    }
}
