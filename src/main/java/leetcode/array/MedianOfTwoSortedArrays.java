package leetcode.array;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
 *
 * Created by venkatamunnangi on 3/29/17.
 */
public class MedianOfTwoSortedArrays {
    // nums1 = [1,2], nums2 = [3,4]
    public double findMedianSortedArrays(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        // the following call is to make sure len(A) <= len(B).
        // yes, it calls itself, but at most once, shouldn't be
        // considered a recursive solution
        if (n > m) {
            return findMedianSortedArrays(B, A);
        }

        // now, do binary search
        int midPtOfTwoArrays = (n + m - 1) >>> 1;
        int left = 0, right = Math.min(midPtOfTwoArrays, n); // right is n, NOT n-1, this is important!!
        while (left < right) {
            int midA = (left + right) >>> 1;
            int midB = midPtOfTwoArrays - midA;
            if (A[midA] < B[midB]) {
                left = midA + 1;
            } else {
                right = midA;
            }
        }

        // after binary search, we almost get the median because it must be between
        // these 4 numbers: A[left-1], A[left], B[midPtOfTwoArrays-left], and B[midPtOfTwoArrays-left+1]

        // if (n+m) is odd, the median is the larger one between A[left-1] and B[midPtOfTwoArrays-left].
        // and there are some corner cases we need to take care of.
        int a = Math.max(
                left > 0 ? A[left - 1] : Integer.MIN_VALUE,
                midPtOfTwoArrays - left >= 0 ? B[midPtOfTwoArrays - left] : Integer.MIN_VALUE);

        // odd check.
        if (((n + m) & 1) == 1)
            return a;

        // if (n+m) is even, the median can be calculated by
        //      median = (max(A[left-1], B[midPtOfTwoArrays-left]) + min(A[left], B[midPtOfTwoArrays-left+1]) / 2.0
        // also, there are some corner cases to take care of.
        int b = Math.min(
                left < n ? A[left] : Integer.MAX_VALUE,
                midPtOfTwoArrays - left + 1 < m ? B[midPtOfTwoArrays - left + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0;
    }


    /**
     * Finds the median of two sorted arrays using binary search.
     *
     * @param nums1 - first sorted array.
     * @param nums2 - second sorted array.
     * @return the median of the two sorted arrays.
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // Ensure that nums1's length is smaller than or equal to nums2's.
        // This makes sure the binary search is always applied to the smaller array.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays2(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;
        int low = 0, high = x;

        // Binary search on the smaller array (nums1).
        while (low <= high) {
            int partitionX = (low + high) >>> 1;
            // Compute partition for nums2 based on partitionX.
            int partitionY = (x + y + 1) / 2 - partitionX;

            // Determine the elements immediately on the left and right
            // of the partition for both arrays.
            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];
            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            // Check if we have found the correct partitions.
            if (maxX <= minY && maxY <= minX) {
                // The partitions are correct.
                // Handle even and odd combined lengths differently.
                if ((x + y) % 2 == 0) {
                    // If even number of total elements, median is average of max left and min right.
                    return ((double) Math.max(maxX, maxY) + Math.min(minX, minY)) / 2;
                } else {
                    // If odd number of total elements, median is max of left.
                    return (double) Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                // maxX is too big, adjust the partition to the left.
                high = partitionX - 1;
            } else {
                // minX is too small, adjust the partition to the right.
                low = partitionX + 1;
            }
        }

        // If we reached here, the input arrays are not sorted.
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static void main(String [] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int[] num1 = {0,3,5}, num2 = {1,2,4}, num3 = {1,3}, num4 = {2,4};
        out.println(medianOfTwoSortedArrays.findMedianSortedArrays(num1, num2));
        out.println(medianOfTwoSortedArrays.findMedianSortedArrays(num3, num4));
        num3 = new int[0];
        out.println(medianOfTwoSortedArrays.findMedianSortedArrays(num3, num4));
        out.println(medianOfTwoSortedArrays.findMedianSortedArrays(num3, num4));
    }
}
