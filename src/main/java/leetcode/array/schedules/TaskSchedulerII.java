package leetcode.array.schedules;

import java.util.HashMap;
import java.util.Map;

public class TaskSchedulerII {
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> startDay = new HashMap<>();
        long day = 0;

        for (int task : tasks) {
            day++;

            // If the current day is too early to complete the task,
            // fast-forward the day to the earliest day you can.
            if (startDay.getOrDefault(task, 0L) > day) {
                day = startDay.get(task);
            }

            // Update the earliest day you can complete the task.
            startDay.put(task, day + space + 1);
        }

        return day;
    }

    public static void main(String[] args) {
        TaskSchedulerII solution = new TaskSchedulerII();

        // Example 1
        int[] tasks1 = {1, 2, 1, 2, 3, 1};
        int space1 = 3;
        System.out.println("Minimum days for example 1: " + solution.taskSchedulerII(tasks1, space1));

        // Example 2
        int[] tasks2 = {5, 8, 8, 5};
        int space2 = 2;
        System.out.println("Minimum days for example 2: " + solution.taskSchedulerII(tasks2, space2));
    }
}
