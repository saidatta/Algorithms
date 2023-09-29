package leetcode.design.game;

/**
 * A design for the Tic Tac Toe game.
 * URL: https://leetcode.com/problems/design-tic-tac-toe/
 */
public class TicTacToe {
    private static final int PLAYER_ONE_CODE = 1;
    private static final int PLAYER_TWO_CODE = -1;

    private final int[] rows;
    private final int[] cols;
    private int diagonal;
    private int antiDiagonal;

    /**
     * Initializes the game board of size n x n.
     *
     * @param n Size of the board.
     */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    /**
     * Records a move in the Tic Tac Toe game.
     *
     * @param row    The row of the move.
     * @param col    The column of the move.
     * @param player The player making the move (1 or 2).
     * @return The winning player or 0 if no one wins yet.
     */
    public int move(int row, int col, int player) {
        int currentPlayer = (player == PLAYER_ONE_CODE) ? PLAYER_ONE_CODE : PLAYER_TWO_CODE;

        // Update the game state based on the move
        updateGameState(row, col, currentPlayer);

        // Check for a winner
        if (isWinningMove(row, col)) {
            return player;
        }

            // No one wins
            return 0;
    }

    private void updateGameState(int row, int col, int currentPlayer) {
        rows[row] += currentPlayer;
        cols[col] += currentPlayer;

        if (isDiagonalMove(row, col)) {
            diagonal += currentPlayer;
        }

        if (isAntiDiagonalMove(row, col)) {
            antiDiagonal += currentPlayer;
        }
    }

    private boolean isDiagonalMove(int row, int col) {
        return row == col;
    }

    private boolean isAntiDiagonalMove(int row, int col) {
        return col == (cols.length - row - 1);
    }

    private boolean isWinningMove(int row, int col) {
        int n = rows.length;

        return Math.abs(rows[row]) == n ||
                Math.abs(cols[col]) == n ||
                Math.abs(diagonal) == n ||
                Math.abs(antiDiagonal) == n;
    }
}

