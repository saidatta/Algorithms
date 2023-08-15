package Leetcode.array;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order. You may assume no duplicates in the array.
 * <p>
 * Here are few examples.
 * [1,3,5,6], 5 -> 2
 * [1,3,5,6], 2 -> 1
 * [1,3,5,6], 7 -> 4
 * [1,3,5,6], 0 -> 0
 *
 * https://leetcode.com/problems/search-insert-position/
 *
 * Created by venkatamunnangi on 9/7/19.
 */
public class SearchInsertPosition {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0)
            return 0;
        return searchInsert(A, target, 0, A.length - 1);
    }

    public int searchInsert(int[] A, int target, int start, int end) {
        int mid = (start + end) / 2;
        if (target == A[mid])
            return mid;
        else if (target < A[mid])
            return start < mid ? searchInsert(A, target, start, mid - 1) : start;
        else
            return end > mid ? searchInsert(A, target, mid + 1, end) : (end + 1);
    }

    //o(logn)
    public int searchInsertIterative(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (target > nums[mid]) {
                i = mid + 1;
            } else if (target < nums[mid]) {
                j = mid - 1;
            } else {
                return mid;
            }
        }
        return i;
    }

    public static void main(String [] args) {
        int[] A = new int[]{2,3,5,6};

        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        System.out.println(searchInsertPosition.searchInsertIterative(A, 1));
    }
}
