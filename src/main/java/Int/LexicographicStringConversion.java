package Int;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LexicographicStringConversion {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        Set<Character> seen = new HashSet<>();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : s.toCharArray()) {
            count[c - 'a']--;

            if (seen.contains(c)) {
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
                seen.remove(stack.pop());
            }

            seen.add(c);
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "bbacbca";
        System.out.println(new LexicographicStringConversion().removeDuplicateLetters(input));  // Output: abc
    }

}
