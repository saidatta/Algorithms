package leetcode.design.game;

// https://leetcode.com/problems/minesweeper/description/
public class Minesweeper {
    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
            {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0], col = click[1];

        // If the user clicks on a mine ('M'), mark it as 'X' and return the board.
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        // Count adjacent mines.
        int adjacentMines = countAdjacentMines(board, row, col);

        // If there are no adjacent mines, update the cell to 'B' and update its neighbors.
        if (adjacentMines == 0) {
            board[row][col] = 'B';
            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0], newCol = col + direction[1];
                if (isValid(board, newRow, newCol) && board[newRow][newCol] == 'E') {
                    updateBoard(board, new int[]{newRow, newCol});
                }
            }
        } else {
            // If there are adjacent mines, update the cell with the number of adjacent mines.
            board[row][col] = (char) ('0' + adjacentMines);
        }

        return board;
    }

    private int countAdjacentMines(char[][] board, int row, int col) {
        int count = 0;
        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0], newCol = col + direction[1];
            if (isValid(board, newRow, newCol) && (board[newRow][newCol] == 'M' || board[newRow][newCol] == 'X')) {
                count++;
            }
        }
        return count;
    }

    private boolean isValid(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}
