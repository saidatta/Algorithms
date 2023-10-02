package leetcode.array.binarysearch;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
public class FindMinRotatedSortedArrayII {
//    This problem is a bit trickier than the previous one because of the potential presence of duplicates. Duplicates
//    introduce ambiguity when deciding which half of the array to search, potentially causing the worst-case time
//    complexity to degrade to
//    O(n)
//    O(n). This worst-case scenario happens when we have a lot of duplicates, and we can't determine the half in which
//    the minimum resides just by comparing the middle element with the boundaries. In such cases, the algorithm might
//    end up linearly searching for the minimum.
//
//    The primary change from the previous solution is that when nums[mid] is equal to nums[high], we can't determine
//    the half where the minimum resides, so the only safe option is to reduce the high pointer by 1.
//
//    Here's the algorithm:
//
//    Initialize low to 0 and high to n-1 (the last index of the array).
//    While low < high:
//    Find the mid-point mid.
//    If nums[mid] > nums[high], this means the smallest element is in the right half. So, set low = mid + 1.
//    If nums[mid] < nums[high], this means the smallest element is in the left half. So, set high = mid.
//            If nums[mid] == nums[high], this means the smallest element could be in either half, but we can safely
//              reduce the high pointer by 1.
//    Return nums[low] which will be the smallest element.
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] > nums[high]) {
                // The minimum is in the right half
                low = mid + 1;
            } else if (nums[mid] < nums[high]) {
                // The minimum is in the left half
                high = mid;
            } else {
                // nums[mid] == nums[high], decrease high
                high--;
            }
        }

        return nums[low];
    }
}

//    To answer the follow-up question: The presence of duplicates can potentially affect the runtime complexity.
//    In the worst case (when there are many duplicates), the solution can degrade to O(n). However, in the
//    average case, the solution will still have a logarithmic behavior, i.e., O(logn)