package leetcode.array;

/**
 * https://leetcode.com/problems/sum-of-squares-of-special-elements/
 */
public class SumSquaresSpecialElements {
    public int sumOfSpecialSquares(int[] nums) {
        int sum = 0;
        int length = nums.length;

        for (int i = 1; i <= Math.sqrt(length); i++) {
            // check if 'i' is a divisor of 'length'
            if (length % i == 0) {
                // add square of the 'i-th' and 'length/i-th' element to the sum
                // 1-indexing is converted to 0-indexing by subtracting 1
                sum += nums[i - 1] * nums[i - 1];

                // check if the divisors are different to avoid counting twice
                if (i != length / i) {
                    sum += nums[length / i - 1] * nums[length / i - 1];
                }
            }
        }

        return sum;
    }
}
