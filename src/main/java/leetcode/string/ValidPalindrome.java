package leetcode.string;

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

        int left = 0, right = n - 1;
        while (left < right) { // Move 2 pointers from each end until they collide
            while (!Character.isLetterOrDigit(s.charAt(left)) && left < right) {
                left++; // Increment left pointer if not alphanumeric
            }
            while (!Character.isLetterOrDigit(s.charAt(right)) && left < right) {
                right--; // Decrement right pointer if no alphanumeric
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false; // Exit and return error if not match
            }
            left++;
            right--;
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
