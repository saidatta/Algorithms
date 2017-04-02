package Leetcode.Discuss;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://discuss.leetcode.com/topic/28289/rearrange-tasks
 *
 * Given a set of tasks like [A, A, B], and int k, which is the waiting time between two same tasks. Calculate the min
 * total execution time if you are allowed to rearrange the tasks. Assume the execution for each individual task is 1.
 *
 * In above example
 * A A B, k = 1, without rearrangement, the execution time would be 4:
 * A wait A B
 * 1  1   1 1
 * with rearrangement, the execution time would be 3:
 * A B A
 * 1 1 1
 *
 * Created by venkatamunnangi on 3/29/17.
 */
public class RearrangeTasks {
    /**
     * Idea:
     *
     * I guess this is a variation of this rearrange problem. Basically we need to put all the same tasks apart to
     * avoid as much waiting time as possible.
     *
     * After counting the frequency of each task, I sort them by their frequency, so we always try to handle the
     * high-frequency task first (by using a max heap), so the time complexity becomes O(nlog(n)).

     * I would first count the frequency of each character, get the max count d and try to use that to rearrange the
     * characters so that all the same characters become at least d distance away.
     *
     * If the input array is [A, A, A, B], in this case d is 3, first we get [A, '', '', A], since we still have a
     * A left, the array becomes [A, A, '', A], finally, put B in the array and we get [A, A, B, A], so there's only
     * one waiting time. Eventually go through the rearranged array and calculate the total time.
     * Btw, the solution returns the new tasks, eventually we can go through the new tasks and calculate the total time.
     *
     * Example:
     * input tasks: [A, A, A, A, C, D, E, E, B, B, B]
     * output tasks: [A, B, E, D, A, B, E, C, A, B, A] (no waiting time)
     *
     * @param tasks
     * @return
     */

    public char[] rearrangeTasks(char[] tasks) {
        int n = tasks.length;

        // step 1. go through the tasks and count the frequency
        Map<Character, Integer> map = new HashMap<>();

        for (char task : tasks) {
            map.put(task, map.containsKey(task) ? map.get(task) + 1 : 1);
        }

        // step 2. use a max heap to sort the tasks by frequency
        PriorityQueue<Task> heap = new PriorityQueue<>((a, b) -> b.frequency - a.frequency);

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            heap.offer(new Task(entry.getKey(), entry.getValue()));
        }

        // step 3. organize the tasks and put them apart by d distance
        // d is the highest frequency
        int d = heap.peek().frequency;
        // let's reset the tasks
        tasks = new char[n];
        // the next empty slot
        int i = 0;

        while (!heap.isEmpty()) {
            Task task = heap.poll();

            // locate the next empty slot
            while (i < n && tasks[i] != '\0') {
                i++;
            }

            for (int j = i; j < n && task.frequency > 0; j += d) {
                tasks[j] = task.id;
                task.frequency--;
            }

            if (task.frequency > 0) {
                // this task is not done yet, put it back
                heap.offer(task);
            }
        }

        // finally return the rearranged tasks
        return tasks;
    }

    // helper class
    class Task {
        char id;
        int frequency;

        Task(char i, int f) {
            id = i;
            frequency = f;
        }
    }

    public static void main(String [] args) {
        RearrangeTasks rearrangeTasks = new RearrangeTasks();
        char[] tasks = {'A', 'A', 'A', 'A', 'C', 'D', 'E', 'E', 'B', 'B', 'B'};
        System.out.println(rearrangeTasks.rearrangeTasks(tasks));
    }
}
