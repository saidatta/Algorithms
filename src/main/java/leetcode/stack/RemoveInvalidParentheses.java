package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));  // Output: "lee(t(c)o)de"
        System.out.println(minRemoveToMakeValid("a)b(c)d"));        // Output: "ab(c)d"
        System.out.println(minRemoveToMakeValid("))(("));           // Output: ""
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
}
