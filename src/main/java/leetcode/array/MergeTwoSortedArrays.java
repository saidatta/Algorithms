package leetcode.array;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 *
 * Created by venkatamunnangi on 9/7/19.
 */
public class MergeTwoSortedArrays {

    /**
     * Given two sorted integer arrays A and B, merge B into A as one sorted array.
     * Note: You may assume that A has enough space to hold additional elements from B.
     * The number of elements initialized in A and B are m and n respectively.
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int A[], int m, int B[], int n) {

        int i = m - 1, j = n - 1;
        for (int k = m + n - 1; k >= 0; k--) {
            if ((j < 0) || (i >= 0 && A[i] >= B[j])) {
                A[k] = A[i--];
            } else {
                A[k] = A[j--];
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // Initialize pointers to the end of both arrays
        int p1 = m - 1;
        int p2 = n - 1;

        // Pointer for current position in nums1
        int p = m + n - 1;

        // Compare and merge
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] < nums2[p2]) {
                nums1[p] = nums2[p2];
                p2--;
            } else {
                nums1[p] = nums1[p1];
                p1--;
            }
            p--;
        }

        // If there are elements left in nums2, copy them over
        // (no need to check for nums1 because we're writing in nums1)
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }


}
