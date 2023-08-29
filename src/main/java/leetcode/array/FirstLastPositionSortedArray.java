package leetcode.array;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Created by venkatamunnangi on 9/11/19.
 */
public class FirstLastPositionSortedArray {
    public int[] searchRange(int[] inputArray, int target) {
        int[] range = {inputArray.length, -1};
        searchRange(inputArray, target, 0, inputArray.length - 1, range);
        if (range[0] > range[1]) {
            range[0] = -1;
        }
        return range;
    }

    public void searchRange(int[] inputArray, int target, int left, int right, int[] range) {
        if (left > right) {
            return;
        }

        int mid = left + (right - left) / 2;
        if (inputArray[mid] == target) {
            if (mid < range[0]) {
                range[0] = mid;
                searchRange(inputArray, target, left, mid - 1, range);
            }
            if (mid > range[1]) {
                range[1] = mid;
                searchRange(inputArray, target, mid + 1, right, range);
            }
        } else if (inputArray[mid] < target) {
            searchRange(inputArray, target, mid + 1, right, range);
        } else {
            searchRange(inputArray, target, left, mid - 1, range);
        }
    }
}