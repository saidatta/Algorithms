package Leetcode;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/valid-palindrome/#/description
 *
 * Created by venkatamunnangi on 3/28/17.
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }

        int n = s.length();

        for (int i = 0, j = n - 1; i < j; i++, j--) { // Move 2 pointers from each end until they collide
            while (!Character.isLetterOrDigit(s.charAt(i)) && i < j) {
                i++; // Increment left pointer if not alphanumeric
            }
            while (!Character.isLetterOrDigit(s.charAt(j)) && i < j) {
                j--; // Decrement right pointer if no alphanumeric
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false; // Exit and return error if not match
            }
        }

        return true;
    }

    public static void main(String [] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        out.println(validPalindrome.isPalindrome("abcd"));
        out.println(validPalindrome.isPalindrome("a."));
        out.println(validPalindrome.isPalindrome(null));
        out.println(validPalindrome.isPalindrome(""));
        out.println(validPalindrome.isPalindrome("dbbd"));
        out.println(validPalindrome.isPalindrome("dbbbd"));
        out.println(validPalindrome.isPalindrome("dbabd"));
        out.println(validPalindrome.isPalindrome("dbaxbd"));

    }
}
