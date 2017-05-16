package Leetcode.TODO;

/**
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/#/description
 * <p>
 * Created by venkatamunnangi on 5/15/17.
 */
public class ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int... A) {
        int n = A.length, beg = -1, end = -2, min = A[n - 1], max = A[0];
        for (int i = 1; i < A.length; i++) {
            max = Math.max(max, A[i]);
            min = Math.min(min, A[n - 1 - i]);
            if (A[i] < max) {
                end = i;
            }
            if (A[n - 1 - i] > min) {
                beg = n - 1 - i;
            }
        }
        return end - beg + 1;
    }

    public static void main(String... args) {
        ShortestUnsortedContinuousSubarray scs = new ShortestUnsortedContinuousSubarray();
        System.out.println(scs.findUnsortedSubarray(2, 6, 4, 8, 10, 9, 15));
    }
}
