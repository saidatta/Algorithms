package leetcode.array.sorting;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/#/description
 *
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 *
 * Created by venkatamunnangi on 5/10/17.
 */
public class RemoveConsecutiveDuplicatesSortedArray {
    public int removeDuplicates(int[] nums) {
        int length = 1, k = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                length++;
                nums[k++] = nums[i];
            }
        }

        return length;
    }


}
