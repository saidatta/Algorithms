package leetcode.array.binarysearch;

// https://leetcode.com/problems/minimum-replacements-to-sort-the-array/description/
public class MinReplacementSortArray {
    /**
     * Compute the minimum replacements required to convert the array into non-decreasing order.
     * @param nums Input array of integers.
     * @return Minimum replacements count.
     */
    public long minimumReplacement(int[] nums) {
        long maxProcessedValue = nums[nums.length - 1];
        long totalReplacements = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            long currentValue = nums[i];
            long replacementsForCurrentValue = (currentValue - 1) / maxProcessedValue;
            totalReplacements += replacementsForCurrentValue;
            maxProcessedValue = currentValue / (replacementsForCurrentValue + 1);
        }

        return totalReplacements;
    }

    public static void main(String[] args) {
        MinReplacementSortArray solution = new MinReplacementSortArray();

        int[] testArray1 = {3, 9, 3};
        int[] testArray2 = {1, 2, 3, 4, 5};

        System.out.println("Output for [3,9,3]: " + solution.minimumReplacement(testArray1));
        System.out.println("Output for [1,2,3,4,5]: " + solution.minimumReplacement(testArray2));
    }
}
