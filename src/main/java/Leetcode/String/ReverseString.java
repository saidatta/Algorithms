package Leetcode.String;

/**
 * 344
 * Created by venkatamunnangi on 11/29/16.
 */
public class ReverseString {
    public String reverseString(String s) {
        if(s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder s2 = new StringBuilder(s);
        return (s2.reverse()).toString();
    }
}
