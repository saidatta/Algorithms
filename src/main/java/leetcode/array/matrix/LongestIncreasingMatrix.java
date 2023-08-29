package leetcode.array.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix
 *
 * Created by venkatamunnangi on 9/28/19.
 */
public class LongestIncreasingMatrix {
    private final int[][] directions = {{1, 0}, {0, 1},{-1, 0}, {0, -1}};

    private boolean ispeak(int[][] matrix, boolean[][] marked, int i, int j) {
        if (i > 0 && !marked[i - 1][j] && matrix[i - 1][j] > matrix[i][j]) {
            return false;
        }
        if (i < matrix.length - 1 && !marked[i + 1][j] && matrix[i + 1][j] > matrix[i][j]) {
            return false;
        }
        if (j > 0 && !marked[i][j - 1] && matrix[i][j - 1] > matrix[i][j]) {
            return false;
        }
        return !(j < matrix[0].length - 1 && !marked[i][j + 1] && matrix[i][j + 1] > matrix[i][j]);
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int len = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] marked = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (ispeak(matrix, marked, i, j)) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            len++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();
                marked[p[0]][p[1]] = true;
                for (int j = 0; j < 4; j++) {
                    int r = p[0] + directions[j][0], c = p[1] + directions[j][1];
                    if (r >= 0 && r < matrix.length && c >= 0 && c < matrix[0].length && !marked[r][c] && ispeak(matrix, marked, r, c)) {
                        if (matrix[r][c] != matrix[p[0]][p[1]]) queue.add(new int[]{r, c});
                    }
                }
            }
        }
        return len;
    }

    public int longestIncreasingPathDFS(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[][] state = new int[matrix.length][matrix[0].length];
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(res, dfs(i, j, matrix, state, visited));
            }
        }
        return res;
    }

    public int dfs(int i, int j, int[][] matrix, int[][] state, boolean[][] visited) {
        if (state[i][j] > 0 ||  visited[i][j]) {
            return state[i][j];
        }

        int max = 0;
        for (int currentDirection = 0; currentDirection < directions.length; currentDirection++) {
            if (boundaryChecks(i, j, currentDirection, matrix)
                    && matrix[i + directions[currentDirection][0]][j + directions[currentDirection][1]] == matrix[i][j]) {
//                visited[i][j] = true;
                max = Math.max(max, dfs(i + directions[currentDirection][0], j + directions[currentDirection][1], matrix, state,visited));
//                visited[i][j] = false;
            }
        }
        visited[i][j] = false;

        state[i][j] = 1 + max;
        return state[i][j];
    }

    private boolean boundaryChecks(int i, int j, int currentDirection, int[][] matrix) {
        return i + directions[currentDirection][0] >= 0
                && i + directions[currentDirection][0] < matrix.length
                && j + directions[currentDirection][1] >= 0
                && j + directions[currentDirection][1] < matrix[0].length;
    }

    public static void main(String[] args) {
        int[][] nums = {
                {1, 1},
                {5, 5},
                {5, 5}
        };

        int[][] nums2 = {
                {5, 5},
                {5, 2}
        };

        LongestIncreasingMatrix longestIncreasingMatrix = new LongestIncreasingMatrix();

        System.out.println(longestIncreasingMatrix.longestIncreasingPathDFS(nums2));
    }
}
