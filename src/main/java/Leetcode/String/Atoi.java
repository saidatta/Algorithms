package Leetcode.String;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/#/description
 *
 * Created by venkatamunnangi on 12/6/16.
 */
public class Atoi {
    public int myAtoi(String s) {
        int i = 0, sign = 1, result = 0;
        // 1. Check for white spaces
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        // 2. Check for signs
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i++) == '+') ? 1 : -1;
        }
        // 3. Convert number and avoid overflow
        while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            if (result > Integer.MAX_VALUE / 10 ||
                    (result == Integer.MAX_VALUE / 10 && s.charAt(i) - '0' > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + (s.charAt(i++) - '0');
        }
        return result * sign;
    }
}
