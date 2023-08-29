package leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/description/
 */
public class MinRoundsTasks {
    public int minimumRounds(int[] tasks) {
        int result = 0;
        HashMap<Integer, Integer> m = new HashMap<>();

        for (int x : tasks) {
            m.put(x, m.getOrDefault(x, 0) + 1);
        }

        for (int x : m.values()) {
            if (x == 1) {
                return -1;
            }

            if (x % 3 == 1) {
                result += (x - 4) / 3 + 2;
            } else {
                result += x / 3 + (x % 3) / 2;
            }
        }

        return result;
    }

    public int minimumRounds2(int[] tasks) {
        int res = 0;
        // Use record to create a simple data class
        record Pair(int key, int value) {}

        // Use stream and collect to group by tasks and count frequencies
        var m = java.util.Arrays.stream(tasks)
                .mapToObj(x -> new Pair(x, 1))
                .collect(Collectors.toMap(Pair::key, Pair::value, Integer::sum));

        // Use switch expression to handle different cases of x
        for (int x : m.values()) {
            if (x == 1) {
                return -1;
            }

            res += switch (x % 3) {
                case 1 -> (x - 4) / 3 + 2;
                case 2 -> x / 3 + 1;
                default -> x / 3;
            };
        }

        return res;
    }
}
