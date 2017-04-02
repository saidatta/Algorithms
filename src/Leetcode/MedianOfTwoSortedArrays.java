package Leetcode;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
 *
 * Created by venkatamunnangi on 3/29/17.
 */
public class MedianOfTwoSortedArrays {
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
        int k = (n + m - 1) / 2;
        int left = 0, right = Math.min(k, n); // right is n, NOT n-1, this is important!!
        while (left < right) {
            int midA = (left + right) / 2;
            int midB = k - midA;
            if (A[midA] < B[midB])
                left = midA + 1;
            else
                right = midA;
        }

        // after binary search, we almost get the median because it must be between
        // these 4 numbers: A[left-1], A[left], B[k-left], and B[k-left+1]

        // if (n+m) is odd, the median is the larger one between A[left-1] and B[k-left].
        // and there are some corner cases we need to take care of.
        int a = Math.max(left > 0 ? A[left - 1] : Integer.MIN_VALUE, k - left >= 0 ? B[k - left] : Integer.MIN_VALUE);
        if (((n + m) & 1) == 1)
            return (double) a;

        // if (n+m) is even, the median can be calculated by
        //      median = (max(A[left-1], B[k-left]) + min(A[left], B[k-left+1]) / 2.0
        // also, there are some corner cases to take care of.
        int b = Math.min(left < n ? A[left] : Integer.MAX_VALUE, k - left + 1 < m ? B[k - left + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0;
    }

    public static void main(String [] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int[] num1 = {3}, num2 = {1,2}, num3 = {1,3}, num4 = {2,4};
        out.println(medianOfTwoSortedArrays.findMedianSortedArrays(num1, num2));
        out.println(medianOfTwoSortedArrays.findMedianSortedArrays(num3, num4));
        num3 = new int[0];
        out.println(medianOfTwoSortedArrays.findMedianSortedArrays(num3, num4));
        out.println(medianOfTwoSortedArrays.findMedianSortedArrays(num3, num4));
    }
}
