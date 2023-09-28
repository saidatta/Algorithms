package leetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/trapping-rain-water-ii/description/
public class TrappingRainWaterII {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int rows = heightMap.length;
        int cols = heightMap[0].length;

        // Use a priority queue to store the cells by their height
        PriorityQueue<Cell> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));
        boolean[][] visited = new boolean[rows][cols];

        // Add all the border cells to the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    queue.offer(new Cell(i, j, heightMap[i][j]));
                    visited[i][j] = true;
                }
            }
        }

        int water = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] dir : DIRECTIONS) {
                int newRow = cell.row + dir[0];
                int newCol = cell.col + dir[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    water += Math.max(0, cell.height - heightMap[newRow][newCol]);
                    queue.offer(new Cell(newRow, newCol, Math.max(cell.height, heightMap[newRow][newCol])));
                }
            }
        }

        return water;
    }

    static class Cell {
        int row;
        int col;
        int height;

        Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        TrappingRainWaterII solution = new TrappingRainWaterII();

        int[][] terrain1 = {
                {2, 1, 2, 2, 1},
                {1, 3, 3, 3, 2},
                {3, 3, 0, 3, 1},
                {1, 1, 3, 1, 1}
        };

        int[][] terrain2 = {
                {2, 1, 2, 2, 1},
                {1, 3, 0, 1, 2},
                {3, 3, 0, 3, 1},
                {1, 1, 2, 1, 1}
        };

        System.out.println(solution.trapRainWater(terrain1));  // Outputs: 3
        System.out.println(solution.trapRainWater(terrain2));  // Outputs: 5
    }
}
