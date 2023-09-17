package leetcode.math.numbertheory;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/the-kth-factor-of-n/description/
public class KthFactorN {
    public static int kthFactor(int n, int k) {
        // Calculate the square root of n to iterate only up to this point.
        int sqrtN = (int) Math.sqrt(n);

        // Find factors up to sqrtN.
        for (int i = 1; i <= sqrtN; i++) {
            if (n % i == 0) {
                // Decrement k for each factor found.
                k--;
                if (k == 0) {
                    // If the kth factor is found, return it.
                    return i;
                }
            }
        }

        // Start the next loop.
        // If n is a perfect square, we skip its square root to avoid double counting.
        int start = (sqrtN * sqrtN == n) ? sqrtN - 1 : sqrtN;

        // Find factors above sqrtN using the properties of divisors.
        // If x is a divisor, then n/x is also a divisor.
//        We're here because the kth divisor is not yet found.
//        Although divisors already contains all "independent" divisors.
//                All other divisors are "paired" ones, i.e,
//                the kth divisor could be computed as N / divisors[len(divisors) - k].
//
//                But before that, we need a small correction for the case when
//                NNN is a perfect square. In that case, the divisor list contains a duplicate because N\sqrt{N}
//        N
//        appears two times. To skip it,
//                we have to increase kkk by one.
//
//        Return N / divisors[len(divisors) - k] if k <= len(divisors)
//        and -1 otherwise.
        for (int i = start; i > 0; i--) {
            if (n % i == 0) {
                k--;
                if (k == 0) {
                    return n / i;
                }
            }
        }

        // If the kth factor is not found, return -1.
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(kthFactor(12, 5));  // 3
        System.out.println(kthFactor(7, 2));   // 7
        System.out.println(kthFactor(4, 4));   // -1
    }
}
