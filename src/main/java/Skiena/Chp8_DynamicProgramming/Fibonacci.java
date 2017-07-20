package Skiena.Chp8_DynamicProgramming;

/**
 * Created by venkatamunnangi on 1/26/17.
 */
public class Fibonacci {
    long fib_dp(int n) {
        long[] fibStorage = new long[Integer.MAX_VALUE];

        fibStorage[0] = 0;
        fibStorage[1] = 1;
        for(int i = 2;i<=n;i++) {
            fibStorage[i] = fibStorage[i-1]+fibStorage[i-2];
        }

        return fibStorage[n];
    }

    long fib_ultimate(int n) {
        long x1 = 0, x2 = 1, next = 1;
        for(int i = 2;i<n;i++) { // i != n, hence return is x1 + x2
            next = x1 + x2;
            x1 = x2;
            x2 = next;
        }
        return x1 + x2;
    }
}
