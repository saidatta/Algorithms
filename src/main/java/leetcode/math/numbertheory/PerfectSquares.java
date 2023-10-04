package leetcode.math.numbertheory;

public class PerfectSquares {
    // Helper function to check if n is a perfect square
    private boolean isPerfectSquare(int n) {
        int root = (int) Math.sqrt(n);
        return root * root == n;
    }

    public int numSquares(int n) {

        // If n is less than 4, return n (as 1*1+1*1 or 2*2)
        if (n < 4)
            return n;

        // If n is a perfect square, return 1
        if (isPerfectSquare(n))
            return 1;

        // Reduce the 4's exponent from n
        while ((n & 3) == 0) // n%4 == 0
            n >>= 2; // n/=4

        // The result is 4 if and only if n can be written in the
        // form of (4^a) * (8b + 7). Please refer to Legendre's three-square theorem.
        if ((n & 7) == 7) // n%8 == 7
            return 4;

        // Check whether 2 is the result.
        int sqrtN = (int) Math.sqrt(n);
        for (int i = 1; i <= sqrtN; i++) {
            if (isPerfectSquare(n - i * i)) {
                return 2;
            }
        }

        // Otherwise, return 3
        return 3;
    }
}
