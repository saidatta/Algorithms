package Leetcode.Array;

/**
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
}
