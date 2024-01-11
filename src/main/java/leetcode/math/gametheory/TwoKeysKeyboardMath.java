package leetcode.math.gametheory;

// https://leetcode.com/problems/2-keys-keyboard/editorial/
class TwoKeysKeyboardMath {
    // prime factorization
    public int minSteps(int n) {
        int totalOperations = 0;
        int factor = 2; // Starting from the smallest prime factor

        // Factorize the number n
        while (n > 1) {
            // If factor divides n, it is one of the prime factors.
            // We keep dividing n by factor (simulating the group of operations)
            // and add the factor to the total operations.
            while (n % factor == 0) {
                totalOperations += factor;
                n /= factor;
            }
            factor++; // Move to the next potential factor
        }

        return totalOperations;
    }
}

