package leetcode.string.stack;

import java.util.Stack;

public class RemoveDuplicateLetters {
    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        // Count of each character in the string
        int[] count = new int[26];
        // Whether the character is in the stack or not
        boolean[] inStack = new boolean[26];

        // Fill count array
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : s.toCharArray()) {
            // Reduce the count because we have seen this character
            count[c - 'a']--;

            if (inStack[c - 'a']) {
                // If the character is in stack, skip it
                continue;
            }

            // Pop characters from stack if they are greater than current character and
            // they will appear later in the string
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
                inStack[stack.pop() - 'a'] = false;
            }

            // Push the current character to stack
            stack.push(c);
            inStack[c - 'a'] = true;
        }

        // Convert the stack to a string
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String s1 = "bcabc";
        String s2 = "cbacdcbc";

        System.out.println("Input: " + s1 + " Output: " + removeDuplicateLetters(s1));
        System.out.println("Input: " + s2 + " Output: " + removeDuplicateLetters(s2));
    }
}
