package Leetcode.Array;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
 *
 * Created by venkatamunnangi on 3/29/17.
 */
public class MedianOfTwoSortedArrays {
    // nums1 = [1,2], nums2 = [3,4]
    public double findMedianSortedArrays(int A[], int B[]) {
        int n = A.length;
        int m = B.length;
        // the following call is to make sure len(A) <= len(B).
        // yes, it calls itself, but at most once, shouldn't be
        // consider a recursive solution
        if (n > m) {
            return findMedianSortedArrays(B, A);
        }

        // now, do binary search
        int midPtOfTwoArrays = (n + m - 1) / 2;
        int left = 0, right = Math.min(midPtOfTwoArrays, n); // right is n, NOT n-1, this is important!!
        while (left < right) {
            int midA = (left + right) / 2;
            int midB = midPtOfTwoArrays - midA;
            if (A[midA] < B[midB])
                left = midA + 1;
            else
                right = midA;
        }

        // after binary search, we almost get the median because it must be between
        // these 4 numbers: A[left-1], A[left], B[midPtOfTwoArrays-left], and B[midPtOfTwoArrays-left+1]

        // if (n+m) is odd, the median is the larger one between A[left-1] and B[midPtOfTwoArrays-left].
        // and there are some corner cases we need to take care of.
        int a = Math.max(left > 0 ? A[left - 1] : Integer.MIN_VALUE
                , midPtOfTwoArrays - left >= 0 ? B[midPtOfTwoArrays - left] : Integer.MIN_VALUE);

        // odd check.
        if (((n + m) & 1) == 1)
            return a;

        // if (n+m) is even, the median can be calculated by
        //      median = (max(A[left-1], B[midPtOfTwoArrays-left]) + min(A[left], B[midPtOfTwoArrays-left+1]) / 2.0
        // also, there are some corner cases to take care of.
        int b = Math.min(left < n ? A[left] : Integer.MAX_VALUE
                , midPtOfTwoArrays - left + 1 < m ? B[midPtOfTwoArrays - left + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0;
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
