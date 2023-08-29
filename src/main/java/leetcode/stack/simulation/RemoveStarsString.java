package leetcode.stack.simulation;

import java.util.Stack;

/**
 * https://leetcode.com/problems/removing-stars-from-a-string/description/
 */
public class RemoveStarsString {
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '*' && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        StringBuilder result = new StringBuilder();
        for (Character c : stack) {
            result.append(c);
        }

//        return stack.stream().map(String::valueOf).collect(Collectors.joining());


        return result.toString();
    }

    public String removeStars2(String s) {
        int starCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                starCount++;
            }
        }

        int k = n - starCount * 2;
        if (k <= 0) {
            return "";
        }

        char[] result = new char[k];
        char[] input = s.toCharArray();
        int skipNext = 0;
        for (int i = n - 1, j = k - 1; i >= 0; i--) {
            if (input[i] == '*') {
                skipNext++;
            } else if (skipNext == 0) {
                result[j--] = input[i];
            } else {
                skipNext--;
            }
        }

        return new String(result);
    }

    public static void main(String[] args) {
        RemoveStarsString solution = new RemoveStarsString();
        System.out.println(solution.removeStars("abc**def*g"));  // Output: "bcef"
    }
}
