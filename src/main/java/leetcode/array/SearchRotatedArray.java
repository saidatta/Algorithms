package leetcode.array;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 *
 * https://www.youtube.com/watch?v=U8XENwh8Oy8&list=PLot-Xpze53lfOdF3KwpMSFEyfE77zIwiP&index=11
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index,
 * other- wise return -1. You may assume no duplicate exists in the array.
 * Created by venkatamunnangi on 9/7/19.
 */
public class SearchRotatedArray {

    // 4 5 6 7 0 1 2
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    // if value exists within left subarray range.
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    // if value exists within right subarray range
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String [] args) {
        SearchRotatedArray searchRotatedArray = new SearchRotatedArray();
        System.out.println(searchRotatedArray.search(new int [] {4, 5, 6, 7, 0, 1, 2},4));
    }
}
