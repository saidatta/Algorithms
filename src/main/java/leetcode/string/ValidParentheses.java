package leetcode.string;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 *
 * Created by venkatamunnangi on 12/1/16.
 */
public class ValidParentheses {

    // ([)]
    public boolean isValid(String s) {
        if(s == null || s.isEmpty()) {
            return false;
        }
        Stack<Character> know = new Stack<>();
        String openings = "({[";
        String endings = ")}]";

        for(char c : s.toCharArray()) {
            if(endings.contains(Character.toString(c))) {
                if(know.empty()) {
                    return false;
                }
                if(c == ')' && know.peek() != '(')
                    return false;
                else if(c == ']' && know.peek() != '[')
                    return false;
                else if(c == '}' && know.peek() != '{')
                    return false;
                else
                    know.pop();
            } else {
                know.push(c);
            }
        }

        return know.empty();
    }

    public boolean isValid2(String s) {
        if(s == null || s.isEmpty()) {
            return false;
        }
        Stack<Character> matchingParenCache = new Stack<>();

        for(char c : s.toCharArray()) {
            if(c == '(') {
                matchingParenCache.push(')');
            } else if (c == '{')
                matchingParenCache.push('}');
            else if (c == '[')
                matchingParenCache.push(']');
            else if (matchingParenCache.empty() || matchingParenCache.pop() != c) {
                return false;
            }
        }

        return matchingParenCache.empty();
    }
}
