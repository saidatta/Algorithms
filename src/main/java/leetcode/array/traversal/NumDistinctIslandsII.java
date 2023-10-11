package leetcode.array.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/number-of-distinct-islands-ii/
public class NumDistinctIslandsII {
    private int[][] grid;
    private boolean[][] seen;
    private ArrayList<Integer> currentShape;

    public int countDistinctIslands(int[][] grid) {
        this.grid = grid;
        this.seen = new boolean[grid.length][grid[0].length];
        Set<String> uniqueShapes = new HashSet<>();

        for (int r = 0; r < grid.length; ++r) {
            for (int c = 0; c < grid[0].length; ++c) {
                currentShape = new ArrayList<>();
                explore(r, c);
                if (!currentShape.isEmpty()) {
                    uniqueShapes.add(canonicalForm(currentShape));
                }
            }
        }

        return uniqueShapes.size();
    }

    private void explore(int r, int c) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length &&
                grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            currentShape.add(r * grid[0].length + c);
            explore(r + 1, c);
            explore(r - 1, c);
            explore(r, c + 1);
            explore(r, c - 1);
        }
    }

    // Generate a unique string representation for a given shape
    private String canonicalForm(ArrayList<Integer> shape) {
        String ans = "";
        int lift = grid.length + grid[0].length;
        int[] out = new int[shape.size()];

        // Calculating the canonical form of the shape
        for (int transform = 0; transform < 8; ++transform) {
            int t = 0;
            int[] xs = new int[shape.size()];
            int[] ys = new int[shape.size()];

            for (int z: shape) {
                int x = z / grid[0].length;
                int y = z % grid[0].length;
                xs[t] = transform <= 1 ? x : transform <= 3 ? -x : transform <= 5 ? y : -y;
                ys[t++] = transform <= 3 ? (transform % 2 == 0 ? y : -y) : (transform % 2 == 0 ? x : -x);
            }

            int minX = xs[0], minY = ys[0];
            for (int x: xs) minX = Math.min(minX, x);
            for (int y: ys) minY = Math.min(minY, y);

            for (int j = 0; j < shape.size(); ++j) {
                out[j] = (xs[j] - minX) * lift + (ys[j] - minY);
            }
            Arrays.sort(out);
            String candidate = Arrays.toString(out);
            if (ans.compareTo(candidate) < 0) ans = candidate;
        }
        return ans;
    }

    public static void main(String[] args) {
        NumDistinctIslandsII solver = new NumDistinctIslandsII();

        int[][] grid1 = {
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1}
        };
        System.out.println(solver.countDistinctIslands(grid1)); // Expected output: 1

        int[][] grid2 = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };
        System.out.println(solver.countDistinctIslands(grid2)); // Expected output: 1
    }
}
