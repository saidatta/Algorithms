package leetcode.heap.slidingWindow;

import java.util.*;

/**
 * https://leetcode.com/problems/sliding-window-median/
 */
public class SlidingWindowMedian {

    /**
     * This function calculates the median of each window of size k sliding from left to right.
     * It maintains two heaps, a max heap for the left side of the median and a min heap for the right side.
     * As the window slides, it uses these heaps to efficiently calculate the median.
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        List<Double> medians = new ArrayList<>();
        Map<Integer, Integer> numberFrequency = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        int i = 0;

        // Initializes the heaps with the first k numbers.
        initializeHeaps(nums, k, maxHeap, minHeap, i);
        i += k;

        while (i <= nums.length) {
            // Appends the current median.
            medians.add(calculateMedian(k, maxHeap, minHeap));

            if (i == nums.length) {
                break;
            }

            int outgoingNumber = nums[i - k];
            int incomingNumber = nums[i];

            // Adjusts heaps after removing outgoingNumber and adding incomingNumber.
            balanceHeaps(outgoingNumber, incomingNumber, maxHeap, minHeap, numberFrequency);
            // Removes numbers from heaps that are outside the current window.
            removeInvalidNumbersFromHeaps(maxHeap, minHeap, numberFrequency);

            i++;
        }

        return medians.stream().mapToDouble(d -> d).toArray();
    }

    /**
     * Initializes the two heaps with the first k numbers.
     */
    private void initializeHeaps(int[] nums, int k,
                                 PriorityQueue<Integer> maxHeap,
                                 PriorityQueue<Integer> minHeap,
                                 int start) {

        for (int i = start; i < start + k; i++) {
            maxHeap.offer(nums[i]);
        }
        for (int j = 0; j < k / 2; j++) {
            minHeap.offer(maxHeap.poll());
        }
    }

    /**
     * Calculates and returns the current median based on the heaps.
     */
    private double calculateMedian(int k, PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        if (maxHeap.isEmpty()) {
            // This should not happen in a correctly managed window of k elements,
            // but we handle it just for safety.
            return 0;
        }

        if (k % 2 != 0) {
            return (double) maxHeap.peek();
        }

        // If minHeap is empty, we return the top of maxHeap as the median.
        double rightOfMedian = (minHeap.isEmpty() ? maxHeap.peek() : minHeap.peek());

        return ((double) maxHeap.peek() + rightOfMedian) * 0.5;
    }


    /**
     * Adjusts the balance of the heaps after sliding the window.
     */
    private void balanceHeaps(int outgoingNumber, int incomingNumber,
                              PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap,
                              Map<Integer, Integer> numberFrequency) {
        int balance = (outgoingNumber <= maxHeap.peek() ? -1 : 1);
        numberFrequency.put(outgoingNumber, numberFrequency.getOrDefault(outgoingNumber, 0) + 1);

        if (!maxHeap.isEmpty() && incomingNumber <= maxHeap.peek()) {
            balance++;
            maxHeap.offer(incomingNumber);
        } else {
            balance--;
            minHeap.offer(incomingNumber);
        }

        if (balance < 0) {
            maxHeap.offer(minHeap.poll());
        } else if (balance > 0) {
            minHeap.offer(maxHeap.poll());
        }
    }

    /**
     * Removes numbers from the heaps that are outside the current window.
     */
    private void removeInvalidNumbersFromHeaps(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap, Map<Integer, Integer> numberFrequency) {
        while (!maxHeap.isEmpty() && numberFrequency.getOrDefault(maxHeap.peek(), 0) > 0) {
            numberFrequency.put(maxHeap.peek(), numberFrequency.get(maxHeap.peek()) - 1);
            maxHeap.poll();
        }
        while (!minHeap.isEmpty() && numberFrequency.getOrDefault(minHeap.peek(), 0) > 0) {
            numberFrequency.put(minHeap.peek(), numberFrequency.get(minHeap.peek()) - 1);
            minHeap.poll();
        }
    }
}
