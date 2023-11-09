package leetcode.array.grid.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DiagonalTraverseII {

    /**
     * Find diagonal order traversal for a given 2D List.
     *
     * @param nums 2D List of integers.
     * @return Array of integers in diagonal traversal order.
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // A queue to manage traversal order
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        // Starting from the first element
        queue.offer(new Pair<>(0, 0));
        // List to store the final traversal result
        List<Integer> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.poll();
            int row = p.key();
            int col = p.value();
            // Add the current element to the result list
            ans.add(nums.get(row).get(col));

            // If we're at the first column and there's a row below, go to the next row
            if (col == 0 && row + 1 < nums.size()) {
                queue.offer(new Pair<>(row + 1, 0));
            }

            // If there's a column to the right, go to the next column in the current row
            if (col + 1 < nums.get(row).size()) {
                queue.offer(new Pair<>(row, col + 1));
            }
        }

        // Convert List to array
        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }

    // Inner class to represent a pair of integers
    public record Pair<U, V>(U key, V value) { }

    public static void main(String[] args) {
        DiagonalTraverseII solution = new DiagonalTraverseII();

        List<List<Integer>> nums = new ArrayList<>();
        nums.add(List.of(1, 2, 3, 4, 5));
        nums.add(List.of(6, 7));
        nums.add(List.of(8));
        nums.add(List.of(9, 10, 11));
        nums.add(List.of(12, 13, 14, 15, 16));

        int[] result = solution.findDiagonalOrder(nums);

        // Printing the result
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
