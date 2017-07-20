package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/sliding-window-maximum/#/description
 *
 * We scan the array from 0 to n-1, keep "promising" elements in the deque.
 * The algorithm is amortized O(n) as each element is put and polled once.
 *
 * Created by venkatamunnangi on 4/2/17.
 */
public class SlidingWindowMaximum {
    /**
     *
     * @param a
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        // in the last iteration u need that one. So its n-k +1.
        int finalArraySize = n-k+1;
        int[] result = new int[finalArraySize];
        int resultIndex = 0;

        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0 ; i<n; i++) {
            if(!dq.isEmpty() && dq.peek() <= i -k) {
                // discard old data that after the window has moved
                // away from them.
                dq.poll();
            }

            // discard the old last items as at no future point
            // will those be considered as maximums.
            while(!dq.isEmpty() && a[dq.peekLast()] < a[i]) {
                dq.pollLast();
            }

            dq.offer(i);

            // if index reaches equal or greater than the window size.
            // we start adding maximums to the result array.
            if(i >= k-1) {
                result[resultIndex++] = a[dq.peek()];
            }
        }

        return result;
    }

    public static void main(String [] args) {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] arr = {1,3,-1,-3,5,3,6,7};
        for(int i : slidingWindowMaximum.maxSlidingWindow(arr, 3)) {
            System.out.println(i);
        }
    }
}
