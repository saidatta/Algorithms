package leetcode.heap;

import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-largest-element-in-a-stream/
public class KthLargestStream {
    private final PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargestStream(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);  // Reuse the add method for initialization
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        return minHeap.peek();
    }
}


