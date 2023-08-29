package leetcode.stack;

import java.util.*;

public class BasicCalculatorIII {

    public static void main(String[] args) {
        System.out.println(calculate("1*1"));                   // 2
        System.out.println(calculate("6-4/2"));                 // 4
        System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));  // 21
    }

    public static int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        s.chars().forEach(e -> queue.add((char) e));
        return evaluate(queue);
    }

    private static int evaluate(Queue<Character> queue) {
        int current = 0, result = 0, prev = 0;
        char operation = '+';

        while (!queue.isEmpty()) {
            char ch = queue.poll();

            if (Character.isDigit(ch)) {
                current = current * 10 + (ch - '0');
            }

            if (ch == '(') {
                current = evaluate(queue);
            }

            if (isOperator(ch) || ch == ')' || queue.isEmpty()) {
                if (operation == '+') {
                    result += prev;
                    prev = current;
                } else if (operation == '-') {
                    result += prev;
                    prev = -current;
                } else if (operation == '*') {
                    prev *= current;
                } else if (operation == '/') {
                    prev /= current;
                }

                operation = ch;
                current = 0;
                if (ch == ')') {
                    break;
                }
            }
        }
        return result + prev;
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}

