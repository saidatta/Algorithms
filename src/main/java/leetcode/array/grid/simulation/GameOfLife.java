package leetcode.array.grid.simulation;

// https://leetcode.com/problems/game-of-life/description/
public class GameOfLife {
    private static final int[] directions = {-1, 0, 1}; // Used to check for eight neighbors

    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }

        int m = board.length, n = board[0].length;

        // Iterate through every cell on the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = liveNeighborCount(board, i, j, m, n);

                // Rule 1 or Rule 3
                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    // mark them dead.
                    board[i][j] = -1;
                }
                // Rule 4
                if (board[i][j] == 0 && liveNeighbors == 3) {
                    // mark them alive
                    board[i][j] = 2;
                }
            }
        }

        // Convert the board to the next state
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }

    // Helper function to count live neighbors
    private int liveNeighborCount(int[][] board, int row, int col, int m, int n) {
        int liveCount = 0;
        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 && j == 0) continue; // Skip the cell itself
                int r = row + i, c = col + j;
                if (r >= 0 && r < m && c >= 0 && c < n && Math.abs(board[r][c]) == 1) {
                    liveCount++;
                }
            }
        }
        return liveCount;
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();

        // Sample grid for testing
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        // Print initial state of the board
        System.out.println("Initial Board:");
        printBoard(board);

        // Apply the rules of Game of Life
        game.gameOfLife(board);

        // Print updated state of the board
        System.out.println("\nBoard after Game of Life:");
        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}