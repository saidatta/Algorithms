package leetcode.math.numbertheory;

/**
 * division without operator
 * https://leetcode.com/problems/divide-two-integers/
 *
 * Created by venkatamunnangi on 3/10/17.
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
            if (dividend == 0) {
                return 0;
            }
            boolean neg = (dividend < 0) ^ (divisor < 0);
            long m = Math.abs((long)dividend), n = Math.abs((long)divisor);
            long result = 0;
            while (m >= n) {
                int shift = 0;
                while (m > (n << shift + 1)) {
                    shift++;
                }
                m -= n << shift;
                result += (1L << shift);
            }
            result = (neg) ? -result : result;
            result = Math.min(Integer.MAX_VALUE, result);
            result = Math.max(Integer.MIN_VALUE, result);
            return (int)result;
    }

    public int divide2(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;

        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        long quotient = 0;

        while (ldividend >= ldivisor) {
            long temp = ldivisor, multiple = 1;
            while (ldividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            ldividend -= temp;
            quotient += multiple;
        }

        return (int) (sign * quotient);
    }
}
