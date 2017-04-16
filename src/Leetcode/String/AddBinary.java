package Leetcode.String;

/**
 * https://leetcode.com/problems/add-binary/#/description
 *
 * For example,
 * a = "11"
 * b = "1"
 *
 * Return "100".
 *
 * Created by venkatamunnangi on 12/9/16.
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        if(a == null || a.isEmpty()) {
            return b;
        }
        if( b == null || b.isEmpty()) {
            return a;
        }

        int i = a.length()-1, j = b.length() -1, sum=0, carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i>=0 || j >=0) {
            if(i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if(j >= 0) {
                sum += b.charAt(j--) - '0';
            }

            sb.append(sum % 2);
            sum = sum / 2;
            carry = sum;
        }

        if(carry != 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();

    }
}
