package leetcode.TODO;

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
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }
        return list.toArray(new int[list.size()][]);
    }
}
