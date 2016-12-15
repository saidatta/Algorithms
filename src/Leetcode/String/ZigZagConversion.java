package Leetcode.String;

/**
 * 6
 * Created by venkatamunnangi on 11/29/16.
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if(s == null || s.isEmpty()) {
            return s;
        }
        int divident = numRows+1;

        int mid = numRows/2;

        int x = 0;
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<=s.length();i++) {
            if(i%(divident) == 0) {
                result.append(s.charAt(i));
            } else if (i % (divident) == numRows || i % (divident) == mid) {
                result.append(s.charAt(i));
            }
        }
        return "";
    }
}
