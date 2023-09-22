package leetcode.array.binarysearch;

/**
 * https://leetcode.com/problems/find-peak-element/
 *
 * Created by venkatamunnangi on 10/7/19.
 */
//It's not hard to show the correctness, just visualize the trend.
// If the next number is larger than current, it show an increasing curve,
// then the peak must in the right half, else in the left half. Since we know
// there's a local peak for sure.
public class FindPeakElement {

    /**
     * This method finds a peak element in the given array. An element is considered a peak if it is strictly
     * greater than its neighbors. It exploits the property that if an element, nums[i], is less than nums[i+1],
     * then a peak exists on the right of it; otherwise, on the left.
     *
     * @param nums The input array where we need to find a peak element.
     * @return The index of a peak element or -1 if the input array is null or empty.
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            // Default value indicating the array is empty or null
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] < nums[mid + 1]) {
                // Move to the right half as the peak is on the right
                low = mid + 1;
            } else {
                // Move to the left half as the peak is on the left
                high = mid;
            }
        }

        // 'low' will point to the peak in the end
        return low;
    }

    public static void main(String[] args) {
        FindPeakElement solution = new FindPeakElement();

        int[] testArray1 = {1, 2, 3, 1};
        int peakIndex1 = solution.findPeakElement(testArray1);
        System.out.println("Peak element index for array 1: " + peakIndex1);  // Expected output: 2

        int[] testArray2 = {1, 2, 1, 3, 5, 6, 4};
        int peakIndex2 = solution.findPeakElement(testArray2);
        System.out.println("Peak element index for array 2: " + peakIndex2);  // Expected output: Either 1 or 5
    }
}

