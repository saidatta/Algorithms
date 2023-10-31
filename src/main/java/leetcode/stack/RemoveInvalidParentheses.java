package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid2("lee(t(c)o)de)"));  // Output: "lee(t(c)o)de"
        System.out.println(minRemoveToMakeValid2("a)b(c)d"));        // Output: "ab(c)d"
        System.out.println(minRemoveToMakeValid2("))(("));           // Output: ""
    }

    public static String minRemoveToMakeValid(String s) {
        Set<Integer> toRemove = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(' -> stack.push(i);
                case ')' -> {
                    if (stack.isEmpty()) {
                        toRemove.add(i);
                    } else {
                        stack.pop();
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            toRemove.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!toRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String minRemoveToMakeValid2(String s) {
        // Pass 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        // Counter for "(" seen so far
        int openSeen = 0;
        // Counter to maintain the balance between "(" and ")"
        int balance = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // When "(" is encountered, increment counters
            if (c == '(') {
                openSeen++;
                balance++;
            }

            // When ")" is encountered, check if it is balanced
            if (c == ')') {
                // If there are no matching "(", skip this ")"
                if (balance == 0) {
                    continue;
                }

                // Else, decrement the balance as we found a matching pair
                balance--;
            }

            // Append the current character to the StringBuilder
            sb.append(c);
        }

        // Pass 2: Remove the rightmost "(" that are more than required for balance
        StringBuilder result = new StringBuilder();
        // Calculate how many "(" to keep for balance
        int openToKeep = openSeen - balance;

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);

            // When "(" is encountered, decide whether to keep it or not
            if (c == '(') {
                // Decrement the count of "(" to keep
                openToKeep--;

                // If no more "(" are required for balance, skip this "("
                if (openToKeep < 0) {
                    continue;
                }
            }

            // Append the current character to the result StringBuilder
            result.append(c);
        }

        // Convert result StringBuilder to String and return
        return result.toString();
    }

}
