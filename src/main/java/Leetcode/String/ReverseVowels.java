package Leetcode.String;

import java.util.HashSet;
import java.util.Stack;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/#/description
 * <p>
 * Created by venkatamunnangi on 4/5/17.
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        HashSet<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        // reverse the  vowels while popping up
        Stack<Character> vStack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                vStack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (vowels.contains(c)) {
                sb.append(vStack.pop());
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
