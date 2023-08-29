package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/#/description
 *
 * Created by venkatamunnangi on 5/9/17.
 */
public class MovingAverageFromDataStream {
    /**
     * Initialize your data structure here.
     */
    Queue<Integer> queue;
    int size;
    double prevSum = 0.0;

    public MovingAverageFromDataStream(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() >= size) {
            prevSum -= queue.remove();
        }

        prevSum += val;
        queue.add(val);
        return prevSum / queue.size();
    }

    public static void main(String... args) {
        MovingAverageFromDataStream movingAverage = new MovingAverageFromDataStream(3);
        System.out.println(movingAverage.next(1));    // Outputs: 1.0
        System.out.println(movingAverage.next(10));   // Outputs: 5.5
        System.out.println(movingAverage.next(3));    // Outputs: 4.66667
        System.out.println(movingAverage.next(5));    // Outputs: 6.0
    }
}
