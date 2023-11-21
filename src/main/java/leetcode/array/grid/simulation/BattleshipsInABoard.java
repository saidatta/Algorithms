package leetcode.array.grid.simulation;

// https://leetcode.com/problems/battleships-in-a-board/
public class BattleshipsInABoard {
    public int countBattleships(char[][] board) {
        int count = 0;
        int rows = board.length, cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'X') {
                    if ((i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X')) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        BattleshipsInABoard solution = new BattleshipsInABoard();
        char[][] board1 = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}};
        char[][] board2 = {{'.'}};
        System.out.println(solution.countBattleships(board1)); // Output: 2
        System.out.println(solution.countBattleships(board2)); // Output: 0
    }
}

