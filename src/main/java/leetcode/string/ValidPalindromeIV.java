package leetcode.string;

// https://leetcode.com/problems/valid-palindrome-iv/description/
public class ValidPalindromeIV {
    public boolean makePalindrome(String s) {
        int differences = 0;
        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                differences++;
            }
            i++;
            j--;
        }

        return differences <= 2;
    }
}
