package leetcode.array.binarysearch;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/
public class TaskAssignmentOptimizer {

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        // Sort tasks and workers' arrays
        Arrays.sort(tasks);
        Arrays.sort(workers);

        // Binary search to find the maximum number of tasks that can be assigned
        int low = 0, high = Math.min(tasks.length, workers.length);
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (canAssignTasks(mid, tasks, workers, pills, strength)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // Helper method to check if k tasks can be assigned
    private boolean canAssignTasks(int k, int[] tasks, int[] workers, int pills, int strength) {
        Deque<Integer> availableWorkers = new LinkedList<>();
        int workerIndex = workers.length - 1;

        // Iterate over tasks from hardest to easiest
        for (int i = k - 1; i >= 0; i--) {
            // Add all available workers that can do the task (with or without pills)
            while (workerIndex >= workers.length - k && (workers[workerIndex] >= tasks[i] || workers[workerIndex] + strength >= tasks[i])) {
                availableWorkers.addFirst(workers[workerIndex]);
                workerIndex--;
            }

            // If no workers are available, return false
            if (availableWorkers.isEmpty()) {
                return false;
            }

            // Assign the best-fit worker to the task
            if (availableWorkers.getLast() >= tasks[i]) {
                // Worker can do the task without a pill
                availableWorkers.pollLast();
            } else {
                // Use a pill if necessary
                if (pills <= 0) {
                    return false;
                }
                availableWorkers.pollFirst();
                pills--;
            }
        }
        return true;
    }
}

