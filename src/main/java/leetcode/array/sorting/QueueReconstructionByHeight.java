package leetcode.array.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height/#/description
 * <p>
 * Suppose you have a random list of people standing in a queue. Each person is described
 * by a pair of integers (h, k), where h is the height of the person and k is the number of people
 * in front of this person who have a height greater than or equal to h.
 * <p>
 * Write an algorithm to reconstruct the queue
 * <p>
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 *
 * Created by venkatamunnangi on 5/14/17.
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        // Sort people based on their height in descending order
        // If heights are equal, sort by k value in ascending order
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        List<int[]> queue = new ArrayList<>();
        for (int[] person : people) {
            // Insert each person at the index specified by their k value
            queue.add(person[1], person);
        }

        return queue.toArray(new int[queue.size()][2]);
    }

    public static void main(String[] args) {
        QueueReconstructionByHeight solution = new QueueReconstructionByHeight();

        // Example input
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};

        // Reconstruct the queue
        int[][] reconstructedQueue = solution.reconstructQueue(people);

        // Print the reconstructed queue
        System.out.println("Reconstructed Queue:");
        for (int[] person : reconstructedQueue) {
            System.out.println(Arrays.toString(person));
        }
    }
}


//    1. Sort People: First, sort the array of people.Sort by height in descending order, and for equal heights, sort by
//       the k value in ascending order.
//    2. Initialize the Queue: Create an empty list to represent the queue.
//    3. Reconstruct the Queue: Iterate through the sorted people array and insert each person at the index specified by
//       their k value in the queue. Since the queue is initially empty, and we're inserting people in decreasing order
//       of height, each person's k value will correspond to their index in the queue.
//    4. Return the Reconstructed Queue: After processing all people, the queue will be reconstructed according to the
//       given conditions.