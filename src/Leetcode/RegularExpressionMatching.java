package Leetcode;

import java.util.Stack;

/**
 * Created by venkatamunnangi on 10/2/16.
 */
public class RegularExpressionMatching {

        char STAR = '*';
        char DOT = '.';


    public boolean isMatch(String s, String p) {
        Stack<Character> se = new Stack<>();
        for(int i = 0 ;i<s.length();i++) {
            se.add(s.charAt(i));
        }

        //"aaa"
        //"ab*a*c* a"

        int i = p.length() -1;
        while(!p.isEmpty()) {
            char c = p.charAt(i);

            if(c != STAR && c != DOT) {
                if(!se.isEmpty() && se.peek() == c ) {
                    se.pop();
                } else {
                    return false;
                }
            } else if(c == DOT) {
                if(se.empty()) { // if no character
                    return false;
                }
                se.pop();
            } else {
                char precedingCharacter = p.charAt(--i);
                if(precedingCharacter != DOT) {
                    while(!se.isEmpty() && se.peek() == precedingCharacter) {
                        se.pop();
                    }
                } else {
                    while(!se.isEmpty()) {
                        se.pop();
                    }
                }
            }
            --i;
        }
        return (se.isEmpty());
    }




}
