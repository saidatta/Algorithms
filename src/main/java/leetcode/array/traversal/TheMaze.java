package leetcode.array.traversal;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/the-maze/
public class TheMaze {

    // Defining directions: LEFT, DOWN, RIGHT, UP
    private static final int[][] DIRECTIONS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int rows = maze.length;
        int cols = maze[0].length;

        // 2D boolean array to keep track of visited cells
        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] currentPos = queue.poll();

            // Check if we reached the destination
            if (currentPos[0] == destination[0] && currentPos[1] == destination[1]) {
                return true;
            }

            // Try all possible directions
            for (int[] direction : DIRECTIONS) {
                int newRow = currentPos[0];
                int newCol = currentPos[1];

                // Move in the current direction as far as possible. until u hit a wall.
                while (isValidMove(newRow + direction[0], newCol + direction[1], maze)) {
                    newRow += direction[0];
                    newCol += direction[1];
                }

                // Check if we have visited this position before
                if (!visited[newRow][newCol]) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }
        return false;
    }

    // Helper function to check if a move is valid (i.e., within the maze and not hitting a wall)
    private boolean isValidMove(int row, int col, int[][] maze) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] == 0;
    }

    public static void main(String[] args) {
        TheMaze solver = new TheMaze();

        int[][] maze1 = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] start1 = {0, 4};
        int[] destination1 = {4, 4};
        System.out.println(solver.hasPath(maze1, start1, destination1));  // Expected: true

        int[][] maze2 = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}
        };
        int[] start2 = {0, 4};
        int[] destination2 = {3, 2};
        System.out.println(solver.hasPath(maze2, start2, destination2));  // Expected: false

        int[][] maze3 = {
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0}
        };
        int[] start3 = {4, 3};
        int[] destination3 = {0, 1};
        System.out.println(solver.hasPath(maze3, start3, destination3));  // Expected: false
    }

}
