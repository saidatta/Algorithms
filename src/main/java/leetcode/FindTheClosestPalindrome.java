package leetcode;

import java.util.Objects;

/**
 * https://leetcode.com/problems/find-the-closest-palindrome/description/
 */
public class FindTheClosestPalindrome {

    /**
     * Creates a palindrome by mirroring the first half of the input string.
     * If the input string has an odd length, the central character is kept unchanged.
     */
    private String createPalindrome(String input) {
        String firstHalf = input.substring(0, input.length() / 2);
        String middleChar = (input.length() % 2 == 1) ? Character.toString(input.charAt(input.length() / 2)) : "";

        return firstHalf + middleChar + new StringBuilder(firstHalf).reverse().toString();
    }

    /**
     * Finds the nearest palindromic number to the given input string.
     */
    public String nearestPalindromic(String n) {
        if ("1".equals(n)) {
            return "0";
        }

        String palindromeFromOriginal = createPalindrome(n);
        long differenceFromOriginal = calculateDifference(n, palindromeFromOriginal);

        String palindromeFromDecreased = createPalindrome(decrease(n));
        long differenceFromDecreased = calculateDifference(n, palindromeFromDecreased);

        String palindromeFromIncreased = createPalindrome(increase(n));
        long differenceFromIncreased = calculateDifference(n, palindromeFromIncreased);

        if (differenceFromDecreased <= differenceFromOriginal && differenceFromDecreased <= differenceFromIncreased) {
            return palindromeFromDecreased;
        }
        if (differenceFromOriginal <= differenceFromIncreased) {
            return palindromeFromOriginal;
        }
        return palindromeFromIncreased;
    }

    /**
     * Decreases the input string by 1.
     */
    private String decrease(String input) {
        StringBuilder sb = new StringBuilder(input);
        int i = (sb.length() - 1) / 2;

        while (i >= 0 && sb.charAt(i) == '0') {
            sb.setCharAt(i, '9');
            i--;
        }
        if (i == 0 && sb.charAt(i) == '1') {
            sb.delete(0, 1);
            sb.setCharAt((sb.length() - 1) / 2, '9');
        } else {
            sb.setCharAt(i, (char) (sb.charAt(i) - 1));
        }
        return sb.toString();
    }

    /**
     * Increases the input string by 1.
     */
    private String increase(String input) {
        StringBuilder sb = new StringBuilder(input);
        int i = (sb.length() - 1) / 2;

        while (i >= 0 && sb.charAt(i) == '9') {
            sb.setCharAt(i, '0');
            i--;
        }
        if (i < 0) {
            sb.insert(0, '1');
        } else {
            sb.setCharAt(i, (char) (sb.charAt(i) + 1));
        }
        return sb.toString();
    }

    /**
     * Calculates the difference between two strings as long values.
     */
    private long calculateDifference(String str1, String str2) {
        return Math.abs(Long.parseLong(str1) - Long.parseLong(str2));
    }
}
