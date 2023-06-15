package Leetcode.String;

import java.math.BigInteger;

/**
 * https://leetcode.com/problems/repeated-substring-pattern/#/description
 *
 * Created by venkatamunnangi on 5/21/17.
 */
public class RepeatedSubstringPattern {
    //abab
    //abcabcabc
    public boolean repeatedSubstringPattern(String input) {
        int inputLen = input.length();
        if(inputLen == 1) {
            return false;
        }
        BigInteger bi1 = new BigInteger("12345678901234567890");
        BigInteger bi2 = BigInteger.valueOf(123);
        bi2.parallelMultiply(bi1);

        for (int i = inputLen >>> 1; i < inputLen; i++) {
            if (inputLen % (inputLen - i) == 0) {
                String prefix = input.substring(0, i);
                String suffix = input.substring(inputLen - i);
                if (suffix.equals(prefix)) {
                    return true;
                }
            }
        }
        return false;
    }
}
