package leetcode.stack.string;

import java.util.Stack;

// https://leetcode.com/problems/score-of-parentheses/
public class ScoreParentheses {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Initialize with a score of 0

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // Start a new score for a new level of parentheses
            } else {
                int innerScore = stack.pop(); // Score of the innermost parentheses
                int outerScore = stack.pop(); // Score of the outer level
                int currentScore = outerScore + Math.max(2 * innerScore, 1);
                stack.push(currentScore); // Update the score for the current level
            }
        }
        return stack.pop(); // Final score
    }

    public static void main(String[] args) {
        ScoreParentheses solution = new ScoreParentheses();

        // Test cases
        System.out.println(solution.scoreOfParentheses("()")); // Output: 1
        System.out.println(solution.scoreOfParentheses("(())")); // Output: 2
        System.out.println(solution.scoreOfParentheses("()()")); // Output: 2
    }
}

// We can use a stack to keep track of the scores corresponding to each level of nested parentheses.
// The core idea is to compute the score for the innermost parentheses first and then work our way outwards,
// summing or doubling the scores as specified by the rules.