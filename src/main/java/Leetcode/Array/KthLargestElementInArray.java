package Leetcode.Array;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/#/description
 *
 * Created by venkatamunnangi on 4/2/17.
 */
public class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {

        final PriorityQueue<Integer> pq = new PriorityQueue<>(k+1);
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public int findKthLargest2(int[] a, int k) {
        int n = a.length;
        // n-k+1 smallest because a typical qs will be ascending.
        int p = quickSelect(a, 0, n - 1, n - k + 1);
        return a[p];
    }

    // return the index of the kth smallest number
    int quickSelect(int[] a, int lo, int hi, int k) {
        // use quick sort's idea
        // put nums that are <= pivot to the left
        // put nums that are  > pivot to the right
        int i = lo, j = hi, pivot = a[hi];
        while (i < j) {
            if (a[i++] > pivot) {
                swap(a, --i, --j);
            }
        }
        swap(a, i, hi);

        // count the nums that are <= pivot from lo
        int m = i - lo + 1;

        // pivot is the one!
        if (m == k) {
            return i;
        } else if (m > k) {
            // pivot is too big, so it must be on the left
            return quickSelect(a, lo, i - 1, k);
        } else {
            // pivot is too small, so it must be on the right
            return quickSelect(a, i + 1, hi, k - m);
        }
    }

    void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
