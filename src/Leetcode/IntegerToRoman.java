package Leetcode;

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

        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}
