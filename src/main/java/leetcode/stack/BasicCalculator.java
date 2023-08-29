package leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/#/description
 *
 * 1 + 1 = 2
 * 2-1 + 2 " = 3
 * (1+(4+5+2)-3)+(6+8)" = 23
 * 2-(3-5)
 *
 * Created by venkatamunnangi on 4/20/17.
 */
public class BasicCalculator {
    public int calculate(String s) {
        int len = s.length(), sign = 1, result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    sum = sum * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += sum * sign;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                // result * sign + previous result.
                result = result * stack.pop() + stack.pop();
            }

        }
        return result;
    }

    public int calculate2(String s) {
        int res = 0;
        char sign = '+';
        for (int i = 0, num = 0, pre = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ("+-*/".indexOf(c) >= 0 || i == s.length() - 1) {
                if (sign == '+') {
                    pre = num;
                } else if (sign == '-') {
                    pre = -num;
                } else if (sign == '*') {
                    res -= pre;
                    pre *= num;
                } else {
                    res -= pre;
                    pre /= num;
                }
                sign = c;
                num = 0;
                res += pre;
            }
        }

        return res;
    }
}
