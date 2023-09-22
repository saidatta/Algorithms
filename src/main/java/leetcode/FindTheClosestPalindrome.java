package leetcode;

import java.util.Objects;

/**
 * https://leetcode.com/problems/find-the-closest-palindrome/description/
 */
public class FindTheClosestPalindrome {

    /**
     * Time complexity : O(l). Scanning, insertion, deletion,, mirroring takes O(l)O(l), where ll is the length of the
     * string.
     * Space complexity : O(l). Temporary variables are used to store the strings.
     *
     * @param s
     * @return
     */
    public String mirroring(String s) {
        String x = s.substring(0, s.length() / 2);
        return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse();
    }

    public String nearestPalindromic(String n) {
        if (Objects.equals(n, "1")) {
            return "0";
        }

        String a = mirroring(n);
        long diff1;
        diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
        if (diff1 == 0) {
            diff1 = Long.MAX_VALUE;
        }

        StringBuilder s = new StringBuilder(n);
        int i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '0') {
            s.replace(i, i + 1, "9");
            i--;
        }
        if (i == 0 && s.charAt(i) == '1') {
            s.delete(0, 1);
            int mid = (s.length() - 1) / 2;
            s.replace(mid, mid + 1, "9");
        } else {
            s.replace(i, i + 1, Character.toString((char) (s.charAt(i) - 1)));
        }
        String b = mirroring(s.toString());
        long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));


        s = new StringBuilder(n);
        i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '9') {
            s.replace(i, i + 1, "0");
            i--;
        }
        if (i < 0) {
            s.insert(0, "1");
        } else {
            s.replace(i, i + 1, Character.toString((char) (s.charAt(i) + 1)));
        }
        String c = mirroring(s.toString());
        long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));

        if (diff2 <= diff1 && diff2 <= diff3) {
            return b;
        }
        if (diff1 <= diff3 && diff1 <= diff2) {
            return a;
        } else {
            return c;
        }
    }

    /**
     * Time complexity - Square root n. 2 * sq.root(n) numbers could have been generated.
     * Space complexity - n
     *
     * @param n
     * @return
     */
    public String nearestPalindromicSlow(String n) {
        long num = Long.parseLong(n);
        for (long i = 1; ; i++) {
            if (isPalindrome(num - i)) {
                return Long.toString(num - i);
            }
            if (isPalindrome(num + i)) {
                return Long.toString(num + i);
            }
        }
    }

    private boolean isPalindrome(long x) {
        long t = x, rev = 0;
        while (t > 0) {
            rev = 10 * rev + t % 10;
            t /= 10;
        }
        return rev == x;
    }
}
