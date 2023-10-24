package leetcode.math.numbertheory;

/**
 * https://leetcode.com/problems/powx-n/
 *
 * Created by venkatamunnangi on 2/7/17.
 */
public class PowerOfN {

    // https://www.youtube.com/watch?v=g9YQyYi4IQQ
    public double myPow(double x, int n) {
        // Base case: if x is 0, then any power of 0 will be 0 (except 0^0, which is undefined)
        if (Double.compare(x, 0) == 0) {
            return 0;
        }
        // Base case: x raised to the power of 0 is always 1
        else if (n == 0) {
            return 1;
        }
        // Base case: x raised to the power of 1 is x
        else if (n == 1) {
            return x;
        }
        // Handle edge case when n is Integer.MIN_VALUE.
        // Integer.MIN_VALUE is -2147483648, but the positive of this number cannot be represented as an int.
        // To deal with this, we increment n by 1, calculate the power, and then divide by x.
        else if (n == Integer.MIN_VALUE) {
            return (1 / x) * myPow(x, n + 1);
        }
        // If n is negative, take the reciprocal of x and negate n
        else if (n < 0) {
            // Math.abs
            n = -n;
            x = 1 / x;
        }

        // If n is even, calculate x^(n/2) and square the result.
        // If n is odd, calculate x^(n/2), square the result, and multiply by x.
        // This reduces the time complexity from O(n) to O(log(n)).
        return ((n % 2 == 0) ? 1 : x) * myPow(x * x, n / 2);
    }


    public static void main(String[] args) {
        PowerOfN powerOfN = new PowerOfN();
        System.out.println(powerOfN.myPow(2.0, 10));
    }


    public double myPowSlow(double x, int n) {
        if (x == 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else if (n < 0) {
            return 1 / x;
        }

        double ans = x;
        for (int i = 1; i <= n; i++) {
            ans *= x;
        }
        return ans;
    }
}
