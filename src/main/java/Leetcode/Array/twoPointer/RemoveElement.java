package Leetcode.Array.twoPointer;

// https://leetcode.com/problems/remove-element/description/
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int m = 0;

        for (int i = 0 ; i < nums.length; i++) {
            if ( nums[i] != val ) {
                nums[m++]  = nums[i];
            }
        }

        return m;
    }
}
