package Leetcode.array;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 */
public class SubarraySumsDivisibleK {

    // Prefix Sum concept along with a bit of modulo arithmetic.
    public int subarraysDivByK(int[] array, int k) {

        // Initialize an array to store frequency counts for each modulo value
        int[] freqCountMod = new int[k];

        // Calculate cumulative sum and increment corresponding modulo counts
        int cumulativeSum = 0;
        for (int num : array) {
            // Make sure the sum is positive
            cumulativeSum += ((num % k) + k) % k;
            freqCountMod[cumulativeSum % k]++;
        }

        // result count = index 0 because of they're devisible by k.
        int result = freqCountMod[0];

        // Calculate the number of subarrays for each modulo value
        // The formula c * (c - 1) / 2 calculates the number of ways to choose 2 items from c items
        for (int count : freqCountMod) {
            result += (count * (count - 1)) / 2;
        }

        return result;
    }
}
