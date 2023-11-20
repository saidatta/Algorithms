package leetcode.string.parentheses;

// https://leetcode.com/problems/remove-outermost-parentheses/
public class RemoveOutermostParentheses {

//    A primitive valid parentheses string starts with an opening parenthesis ( and ends with a closing parenthesis)
//    such that the parentheses are balanced. We can identify these primitive strings by incrementing the counter for
//    an opening parenthesis and decrementing it for a closing parenthesis. When the counter reaches zero, we have found
//    a primitive string.
    public String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int counter = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (counter > 0) {
                    result.append(c);
                }
                counter++;
            } else {
                counter--;
                if (counter > 0) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        RemoveOutermostParentheses solution = new RemoveOutermostParentheses();
        System.out.println(solution.removeOuterParentheses("(()())(())")); // Output: "()()()"
        System.out.println(solution.removeOuterParentheses("(()())(())(()(()))")); // Output: "()()()()(())"
        System.out.println(solution.removeOuterParentheses("()()")); // Output: ""
    }
}


