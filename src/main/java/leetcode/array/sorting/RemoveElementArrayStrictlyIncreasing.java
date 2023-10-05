package leetcode.array.sorting;

// https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/
public class RemoveElementArrayStrictlyIncreasing {
    public boolean canBeIncreasing(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int i = 1; i < n && count <= 1; i++) {
            if (nums[i] <= nums[i - 1]) {
                // when we find a non-increasing number.
                count++;

                if (i - 2 < 0 || nums[i - 2] < nums[i]) {
                    // Skip the prev number
                    continue;
                }

                if (i + 1 == n || nums[i - 1] < nums[i + 1]) {
                    // Skip the current number
                    i++;
                } else {
                    return false;  // Neither of the numbers can be skipped
                }
            }
        }

        return count <= 1;
    }

    public static void main(String[] args) {
        RemoveElementArrayStrictlyIncreasing solution = new RemoveElementArrayStrictlyIncreasing();
        int[] nums = {1, 2, 10, 5, 7};
        System.out.println(solution.canBeIncreasing(nums)); // Expected: true
    }
}
//    raverse through the array, and for each i, if nums[i] <= nums[i-1], attempt two fixes:
//        Skip nums[i-1] and see if nums[i-2] < nums[i] or i-2 is out of bounds.
//        Skip nums[i] and see if nums[i-1] < nums[i+1] or i+1 is out of bounds.
//        If either of these fixes works, continue with the loop.
//        If you encounter a second breach in the strictly increasing condition, return false, because we can only remove one element.