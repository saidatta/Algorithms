package leetcode.dp;

/**
 * https://leetcode.com/problems/integer-break/#/description
 *
 * Created by venkatamunnangi on 4/23/17.
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        if( n  == 2) {
            return 1;
        } else if(n == 3) {
            return 2;
        }

        int remainingNumber = n;

        int product = 1;
        while ( remainingNumber > 4) {
            product *= 3;
            remainingNumber -= 3;
        }

        product *= remainingNumber;
        return product;
    }
}
