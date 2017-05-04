package Leetcode.Design;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/#/description
 *
 *
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Created by venkatamunnangi on 5/3/17.
 */
public class FindMedianFromDataStream {
    // maxHeap queue is always larger or equal to minHeap queue
    PriorityQueue<Integer> minHeap, maxHeap;
    /** initialize your data structure here. */
    public FindMedianFromDataStream() {
        minHeap =  new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(1000, Collections.reverseOrder());
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        else return maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */