package leetcode.array.binarysearch;

public class FindMinRotatedSortedArray {
//    The rotated sorted array can be split into two sorted sub-arrays. One of the sub-arrays will be a sorted sequence
//    without any "jump" (i.e., continuous increasing values), and the other will contain the "jump". The minimum
//    element of the entire array is the first element of the sub-array containing the "jump".

//    By using binary search, we can determine which half of the array contains the "jump" and hence continue the
//    search in that half.
//
//            Algorithm:
//
//    Initialize low to 0 and high to n-1 (the last index of the array).
//    While low < high:
//    Find the mid-point mid.
//    If nums[mid] > nums[high], this means the "jump" and the smallest element is in the right half.
//    So, set low = mid + 1.
//    Otherwise, the smallest element is in the left half (including mid), so set high = mid.
//    Return nums[low] which will be the smallest element.
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] > nums[high]) {
                // The minimum is in the right half
                low = mid + 1;
            } else {
                // The minimum is in the left half
                high = mid;
            }
        }

        return nums[low];
    }
}
