package Leetcode.Array;

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

        int i = m-1,j=n-1;
        for (int k = m+n-1; k>=0;k--) {
            if((j<0) || (i>=0 && A[i]>=B[j])) {
                A[k] = A[i--];
            } else {
                A[k] = A[j--];
            }
        }
    }

    public void merge2(int[] nums1, int m, int[] nums2, int nx) {
        int n = nums2.length;
        int nums2i = n - 1;

        // since nums1 size is n+m
        int nums1i = nums1.length - n -1;

        int i = nums1.length - 1;


        while (nums2i >= 0) {

            if(nums1i >=0 &&  nums1[nums1i] > nums2[nums2i]) {
                nums1[i] = nums1[nums1i];
                nums1i--;
            } else {
                nums1[i] = nums2[nums2i];
                nums2i--;
            }

            i--;
        }
    }



}
