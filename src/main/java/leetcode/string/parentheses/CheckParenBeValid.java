package leetcode.string.parentheses;

public class CheckParenBeValid {
    public static boolean canBeValid(String s, String locked) {
        int n = s.length();
        if ((n & 1) != 0) {
            return false;
        }

        int open = 0;
        int close = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(' || locked.charAt(i) == '0') {
                open++;
            } else {
                close++;
            }

            if (close > open) {
                return false;
            }
        }

        open = 0;
        close = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ')' || locked.charAt(i) == '0') {
                open++;
            } else {
                close++;
            }

            if (close > open) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canBeValid("))()))", "010100"));  // true
        System.out.println(canBeValid("()()", "0000"));      // true
        System.out.println(canBeValid(")", "0"));            // false
    }
}
