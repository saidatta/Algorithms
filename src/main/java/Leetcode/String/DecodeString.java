package Leetcode.String;

import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/#/description
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 *
 * Created by venkatamunnangi on 4/30/17.
 */
public class DecodeString {
    public String decodeString(String input) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int index = 0;
        while (index < input.length()) {
            if (Character.isDigit(input.charAt(index))) {
                int count = 0;
                while (Character.isDigit(input.charAt(index))) {
                    // if double digit
                    count = 10 * count + input.charAt(index) - '0';
                    index++;
                }
                countStack.push(count);
            } else if (input.charAt(index) == '[') {
                resStack.push(res);
                res = "";
                index++;
            } else if (input.charAt(index) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                index++;
            } else {
                res += input.charAt(index++);
            }
        }
        return res;
    }
}
