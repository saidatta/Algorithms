package leetcode.array.twopointers;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-smaller/#/description
 *
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2. Because there are two triplets which sums are less than 2:
 *
 * [-2, 0, 1]
 * [-2, 0, 3]
 *
 * Follow up:
 * Could you solve it in O(n2) runtime?
 *
 * Created by venkatamunnangi on 5/10/17.
 */
public class ThreeSumSmaller {

    /**
     * We sort the array first. Then, for each element, we use the two pointer approach to find the number of triplet
     * s that meet the requirements.
     * Let me illustrate how the two pointer technique works with an example:
     * target = 2
     *  i  lo    hi
     * [-2, 0, 1, 3]
     * We use a for loop (index i) to iterate through each element of the array.
     * For each i, we create two pointers, lo and hi,
     * where lo is initialized as the next element of i, and hi is initialized at the end of the array.
     * If we know that nums[i] + nums[lo] + nums[hi] < target, then we know that since the array is sorted, we can
     * replace hi with any element from lo+1 to nums.length-1, and the requirements will still be met.
     * Just like in the example above, we know that since -2 + 0 + 3 < 2, we can replace hi (3) with 1, and
     * it would still work. Therefore, we can just add hi - lo to the triplet count.
     *
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                // If the sum is smaller than the target, then all elements from left to right will also work
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        ThreeSumSmaller solution = new ThreeSumSmaller();
        System.out.println(solution.threeSumSmaller(new int[]{-2, 0, 1, 3}, 2)); // Output: 2
        System.out.println(solution.threeSumSmaller(new int[]{}, 0));           // Output: 0
        System.out.println(solution.threeSumSmaller(new int[]{0}, 0));          // Output: 0
    }
}
