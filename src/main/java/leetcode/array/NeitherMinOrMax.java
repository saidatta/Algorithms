package leetcode.array;

/**
 * https://leetcode.com/problems/neither-minimum-nor-maximum/description/
 */
public class NeitherMinOrMax {
    public int findNonMinOrMax(int[] nums) {
        if(nums.length < 3) {
            return -1;
        }
        int minValue = nums[0];
        int maxValue = nums[0];
        int selectedNumber = -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minValue) {
                selectedNumber = minValue;
                minValue = nums[i];
            } else if (nums[i] > maxValue) {
                selectedNumber = maxValue;
                maxValue = nums[i];
            } else {
                return nums[i];
            }
        }

        return selectedNumber;
    }
}
