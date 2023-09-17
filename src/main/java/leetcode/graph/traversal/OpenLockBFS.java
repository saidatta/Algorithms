package leetcode.graph.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class OpenLockBFS {
    public static int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        if (deads.contains("0000")) return -1;
        if ("0000".equals(target)) return 0;

        q.offer("0000");
        visited.add("0000");
        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            moves++; // Increment the moves for this level.

            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                for (String next : getNextCombinations(curr)) {
                    if (next.equals(target)) return moves;
                    if (!visited.contains(next) && !deads.contains(next)) {
                        q.offer(next);
                        visited.add(next);
                    }
                }
            }
        }

        return -1; // If we're here, we can't open the lock.
    }

    // This function generates all possible neighbors for the current combination
    private static List<String> getNextCombinations(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            result.add(s.substring(0, i) + ((digit + 1) % 10) + s.substring(i + 1));
            result.add(s.substring(0, i) + ((digit + 9) % 10) + s.substring(i + 1));
        }
        return result;
    }

    public static void main(String[] args) {
        String[] deadends1 = {"0201","0101","0102","1212","2002"};
        String target1 = "0202";
        System.out.println(openLock(deadends1, target1)); // Output: 6

        String[] deadends2 = {"8888"};
        String target2 = "0009";
        System.out.println(openLock(deadends2, target2)); // Output: 1

        String[] deadends3 = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target3 = "8888";
        System.out.println(openLock(deadends3, target3)); // Output: -1
    }

}
