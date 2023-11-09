package leetcode.array.schedules;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/single-threaded-cpu/description/
// https://www.youtube.com/watch?v=RR1n-d4oYqE
public class SingleThreadCPU {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;

        // Create an array of tasks with their indices.
        int[][] indexedTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            indexedTasks[i] = new int[]{tasks[i][0], tasks[i][1], i};
        }
        // Sort the tasks based on enqueueTime.
        Arrays.sort(indexedTasks, Comparator.comparingInt(a -> a[0]));

        // Priority queue to order tasks based on processing time and if processing time is same, smallest index.
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);

        int[] result = new int[n];
        int j = 0, t = 0;
        for (int[] task : indexedTasks) {
            // While tasks are available and can be executed before the current task's enqueue time.
            while (!pq.isEmpty() && (j == n || task[0] > t)) {
                int[] nextTask = pq.poll();
                result[j++] = nextTask[2];
                t += nextTask[1];
            }
            // Adjust the time and add the current task to the priority queue.
            t = Math.max(t, task[0]);
            pq.offer(task);
        }

        // Process any remaining tasks.
        while (!pq.isEmpty()) {
            result[j++] = pq.poll()[2];
        }

        return result;
    }
}
