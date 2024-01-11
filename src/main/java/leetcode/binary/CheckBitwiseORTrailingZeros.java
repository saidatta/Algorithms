package leetcode.binary;

public class CheckBitwiseORTrailingZeros {
    public boolean hasTrailingZeros(int[] nums) {
        int n = nums.length;

        // Iterate over all pairs of numbers in the array
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Check if both numbers in the current pair are even
                // An even number has a 0 as the least significant bit
                if ((nums[i] & 1) == 0 && (nums[j] & 1) == 0) {
                    // If both are even, their bitwise OR will have trailing zeros
                    return true;
                }
            }
        }
        // If no such pair is found, return false
        return false;
    }
}
