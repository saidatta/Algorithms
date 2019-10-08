package dailyCodingProblem;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * This problem was asked by Facebook.

 Given a list of integers, return the largest product that can be made by multiplying any three integers.

 For example, if the list is [-10, -10, 5, 2], we should return 500, since that's -10 * -10 * 5.

 You can assume the list has at least three integers.
 *
 * Created by venkatamunnangi on 9/29/19.
 */
public class Problem69 {
    public int maxProductTriplet(int [] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE,
                min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }
        return Math.max(max1*max2*max3, max1*min1*min2);
    }

    // [-10, -10, 5, 2,3,1  ]
    //O(NlogN) O(k)
    public int maximumProductTripletHeap(int[] nums) {
        PriorityQueue<Integer> positiveHeap = new PriorityQueue<>(); //minHeap
        PriorityQueue<Integer> negativeHeap = new PriorityQueue<>(Collections.reverseOrder()); // maxHeap
        for (int num : nums) {
            positiveHeap.offer(num);
            negativeHeap.offer(num);

            if (positiveHeap.size() > 3) {
                positiveHeap.poll();
            }
            if (negativeHeap.size() > 2) {
                negativeHeap.poll();
            }
        }
        int c1 = 1;
        int max = 0;
        while (!positiveHeap.isEmpty()) {
            max = positiveHeap.poll();
            c1 *= max;
        }
        while (!negativeHeap.isEmpty()) {
            max *= negativeHeap.poll();
        }
        return Math.max(c1, max);
    }
}
