package leetcode.array.grid.simulation;

import java.util.Arrays;

// https://leetcode.com/problems/candy-crush/description/
public class CandyCrush {
    public int[][] candyCrush(int[][] board) {
        int m = board.length, n = board[0].length;
        boolean shouldContinue = false;

        // Step 1: Mark candies for elimination
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = Math.abs(board[i][j]);
                if (val == 0) continue;

                // Check horizontally
                if (j < n - 2 && Math.abs(board[i][j + 1]) == val && Math.abs(board[i][j + 2]) == val) {
                    board[i][j] = board[i][j + 1] = board[i][j + 2] = -val;
                    shouldContinue = true;
                }

                // Check vertically
                if (i < m - 2 && Math.abs(board[i + 1][j]) == val && Math.abs(board[i + 2][j]) == val) {
                    board[i][j] = board[i + 1][j] = board[i + 2][j] = -val;
                    shouldContinue = true;
                }
            }
        }

        // Step 2: Crush marked candies
        if (shouldContinue) {
            // Step 3: Apply gravity
            for (int j = 0; j < n; j++) {
                int writeIndex = m - 1;
                for (int i = m - 1; i >= 0; i--) {
                    if (board[i][j] > 0) {
                        board[writeIndex--][j] = board[i][j];
                    }
                }
                while (writeIndex >= 0) {
                    board[writeIndex--][j] = 0;
                }
            }

            // Step 4: Repeat
            return candyCrush(board);
        }

        // Step 5: Return stable board
        return board;
    }

    public static void main(String[] args) {
        CandyCrush solution = new CandyCrush();
        int[][] board = {
                {110,5,112,113,114},
                {210,211,5,213,214},
                {310,311,3,313,314},
                {410,411,412,5,414},
                {5,1,512,3,3},
                {610,4,1,613,614},
                {710,1,2,713,714},
                {810,1,2,1,1},
                {1,1,2,2,2},
                {4,1,4,4,1014}
        };
        int[][] result = solution.candyCrush(board);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}

