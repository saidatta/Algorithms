package Leetcode.Math;

/**
 * https://leetcode.com/problems/integer-to-roman/#/description
 *
 * Created by venkatamunnangi on 4/5/17.
 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        if(num <= 0) {
            return "";
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[num/1000] + hundreds[(num%1000)/100] + tens[(num%100)/10] + ones[num%10];
    }
}