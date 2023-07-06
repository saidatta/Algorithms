package Leetcode.Array.priorityQueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/total-cost-to-hire-k-workers/
 */
public class TotalCostHireK {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length, i = candidates, j = n - candidates - 1;
        long r = 0;

        int[] front = Arrays.copyOfRange(costs, 0, candidates);
        int[] rear = Arrays.copyOfRange(costs, Math.max(j + 1, candidates), n);

        PriorityQueue<Integer> frontHeap = new PriorityQueue<>();
        PriorityQueue<Integer> rearHeap = new PriorityQueue<>();

        for (int num : front) {
            frontHeap.offer(num);
        }
        for (int num : rear) {
            rearHeap.offer(num);
        }

        while (i <= j && k > 0) {
            if (frontHeap.peek() <= rearHeap.peek()) {
                r += frontHeap.poll();
                frontHeap.offer(costs[i]);
                i++;
            } else {
                r += rearHeap.poll();
                rearHeap.offer(costs[j]);
                j--;
            }
            k--;
        }

        if (k > 0) {
            while (!rearHeap.isEmpty()) {
                frontHeap.offer(rearHeap.poll());
            }
        }

        while (k > 0) {
            r += frontHeap.poll();
            k--;
        }

        return r;
    }
}
