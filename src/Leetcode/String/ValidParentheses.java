package Leetcode.String;

import java.util.Stack;

/**
 * Created by venkatamunnangi on 12/1/16.
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if(s == null || s.isEmpty()) {
            return false;
        }
        Stack<Character> know = new Stack<>();
        String openings = "({[";
        String endings = ")}]";

        for(char c : s.toCharArray()) {
            if(endings.contains(c+"")) {
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
        Stack<Character> cache = new Stack<>();
        String openings = "({[";
        String endings = ")}]";

        for(char c : s.toCharArray()) {
            if(c == '(') {
                cache.push(')');
            } else if (c == '{')
                cache.push('}');
            else if (c == '[')
                cache.push(']');
            else if (cache.empty() || cache.pop() != c) {
                return false;
            }
        }
        return cache.empty();
    }
}
