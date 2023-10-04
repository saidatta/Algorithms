package leetcode.string.parentheses;

// https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
public class MaxNestingDepthParentheses {
    public int maxDepth(String s) {
        int currentDepth = 0;  // Current depth of nested parentheses.
        int maxDepth = 0;      // Maximum depth found so far.

        // Iterate through each character in the string.
        for (char c : s.toCharArray()) {
            if (c == '(') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (c == ')') {
                currentDepth--;
            }
        }

        return maxDepth;
    }

    public static void main(String[] args) {
        MaxNestingDepthParentheses sol = new MaxNestingDepthParentheses();
        System.out.println(sol.maxDepth("(1+(2*3)+((8)/4))+1"));  // Expected output: 3
        System.out.println(sol.maxDepth("(1)+((2))+(((3)))"));    // Expected output: 3
    }
}
