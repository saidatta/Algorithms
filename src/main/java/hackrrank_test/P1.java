package hackrrank_test;

import java.util.Stack;

/**
 * Created by venkatamunnangi on 9/23/17.
 */
public class P1 {
    private static String solution() {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(char ch : sb.toString().toCharArray()) {
            if(ch == ' ')  {
                while(!stack.empty()) {
                    sb.append(stack.pop());
                }
                sb.append(ch);
            } else {
                stack.push(ch);
            }
        }

        while(!stack.empty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
