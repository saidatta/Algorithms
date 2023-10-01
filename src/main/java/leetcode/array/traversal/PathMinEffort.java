package leetcode.array.traversal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/path-with-minimum-effort/description/
public class PathMinEffort {

    // Define the possible directions in which we can move in the matrix.
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // differenceMatrix will store the maximum difference in height we've
        // encountered so far to reach each cell.
        int[][] differenceMatrix = new int[rows][cols];
        for (int[] eachRow : differenceMatrix) {
            Arrays.fill(eachRow, Integer.MAX_VALUE);
        }
        differenceMatrix[0][0] = 0;

        // Using a priority queue allows us to explore cells by their increasing maximum height differences.
        PriorityQueue<Cell> cellsQueue = new PriorityQueue<>(Comparator.comparing(cell -> cell.difference));
        boolean[][] visited = new boolean[rows][cols];

        cellsQueue.add(new Cell(0, 0, differenceMatrix[0][0]));

        while (!cellsQueue.isEmpty()) {
            Cell currentCell = cellsQueue.poll();
            visited[currentCell.x][currentCell.y] = true;

            // If we've reached the destination, return the effort for this cell.
            if (currentCell.x == rows - 1 && currentCell.y == cols - 1) {
                return currentCell.difference;
            }

            for (int[] direction : DIRECTIONS) {
                int adjacentX = currentCell.x + direction[0];
                int adjacentY = currentCell.y + direction[1];

                // Check if the adjacent cell is valid and has not been visited.
                if (isValidCell(adjacentX, adjacentY, rows, cols) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[currentCell.x][currentCell.y]);
                    int maxDifference = Math.max(currentDifference, differenceMatrix[currentCell.x][currentCell.y]);

                    // If the current path to the adjacent cell is better, update the cell's effort and enqueue it.
                    if (differenceMatrix[adjacentX][adjacentY] > maxDifference) {
                        differenceMatrix[adjacentX][adjacentY] = maxDifference;
                        cellsQueue.add(new Cell(adjacentX, adjacentY, maxDifference));
                    }
                }
            }
        }
        return differenceMatrix[rows - 1][cols - 1];
    }

    // Helper function to check if a cell is within the boundaries of the matrix.
    private boolean isValidCell(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    // Helper class to represent a cell.
    private static class Cell {
        final int x;
        final int y;
        final Integer difference;

        Cell(int x, int y, Integer difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }
    }

    public static void main(String[] args) {
        PathMinEffort pathSolver = new PathMinEffort();

        int[][] heights1 = {{1,2,2},{3,8,2},{5,3,5}};
        int[][] heights2 = {{1,2,3},{3,8,4},{5,3,5}};
        int[][] heights3 = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};

        System.out.println(pathSolver.minimumEffortPath(heights1));  // Expected output: 2
        System.out.println(pathSolver.minimumEffortPath(heights2));  // Expected output: 1
        System.out.println(pathSolver.minimumEffortPath(heights3));  // Expected output: 0
    }
}
