package leetcode.array.schedules;

import java.util.Arrays;

import java.util.Arrays;

public class TaskScheduler {

    /**
     * Returns the least number of units of time for CPU to finish all tasks with cooldown.
     *
     * @param tasks Array of tasks where each letter represents a different task.
     * @param taskCooldown Cooldown period between two same tasks.
     * @return Minimum units of time to complete all tasks.
     */
    public int leastInterval(char[] tasks, int taskCooldown) {
        // Array to store the frequency of tasks
        int[] taskFrequencies = new int[26];

        // Count the frequency of each task
        for (char task : tasks) {
            taskFrequencies[task - 'A']++;
        }

        // Sort the array to get the frequency of the most common task
        Arrays.sort(taskFrequencies);

        // Index to find the last task that has the maximum frequency
        int maxFreqIndex = 25;
        while (maxFreqIndex >= 0 && taskFrequencies[maxFreqIndex] == taskFrequencies[25]) {
            maxFreqIndex--;
        }

        int blankSpotCount = 25 - maxFreqIndex;

        // Calculate the minimum time required considering the cooldown
        // Math.max is used to consider the case when there are more tasks than the ideal idle slots
        return Math.max(
                tasks.length, // The length of the tasks is the lower bound
                // Calculate the ideal time considering cool-downs
                // idle slots + max slots.
                // blank sequences *
                (taskFrequencies[25] - blankSpotCount) * (taskCooldown + 1) + blankSpotCount
        );
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        // Example 1
        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n1 = 2;
        System.out.println("Example 1: " + scheduler.leastInterval(tasks1, n1)); // Output: 8

        // Example 2
        char[] tasks2 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n2 = 0;
        System.out.println("Example 2: " + scheduler.leastInterval(tasks2, n2)); // Output: 6

        // Example 3
        char[] tasks3 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n3 = 2;
        System.out.println("Example 3: " + scheduler.leastInterval(tasks3, n3)); // Output: 16
    }
}

