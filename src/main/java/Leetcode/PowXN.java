package Leetcode;

/**
 * Created by venkatamunnangi on 2/7/17.
 */
public class PowXN {
    public double myPow(double x, int n ) {
        if(x == 0) {
            return 0;
        } else if(n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else if (n == Integer.MIN_VALUE) {
            return (1 / x) * myPow(x, n+1);
        } else if(n < 0) {
            n = -n;
            x = 1/x;
        }

        return (((n % 2 == 0) ? 1 : x) * myPow(x * x, n/2));
    }


    public double myPowSlow(double x, int n) {
        if(x == 0) {
            return 0;
        } else if(n == 0) {
            return 1;
        } else if(n == 1) {
            return x;
        } else if(n < 0) {
            return 1 / x;
        }

        double ans = x;
        for(int i = 1; i<=n;i++) {
            ans *= x;
        }
        return ans;
    }
}
