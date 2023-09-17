package leetcode.array.search;

// https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/description/
public class WinnerTicTacToe {
    public String tictactoe(int[][] moves) {
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diagonal1 = 0, diagonal2 = 0;

        for (int i = 0; i < moves.length; i++) {
            int val = (i % 2 == 0) ? 1 : -1;  // 1 for Player A and -1 for Player B
            int x = moves[i][0], y = moves[i][1];

            rows[x] += val;
            cols[y] += val;

            if (x == y) diagonal1 += val;  // main diagonal
            if (x + y == 2) diagonal2 += val;  // anti-diagonal

            // Check if a player has won
            if (rows[x] == 3 || cols[y] == 3 || diagonal1 == 3 || diagonal2 == 3)
                return "A";
            if (rows[x] == -3 || cols[y] == -3 || diagonal1 == -3 || diagonal2 == -3)
                return "B";
        }

        return moves.length == 9 ? "Draw" : "Pending";
    }

    public static void main(String[] args) {
        // Create an instance to call the non-static method
        WinnerTicTacToe game = new WinnerTicTacToe();

        int[][] moves1 = {{0,0},{2,0},{1,1},{2,1},{2,2}};
        int[][] moves2 = {{0,0},{1,1},{0,1},{0,2},{1,0},{2,0}};
        int[][] moves3 = {{0,0},{1,1},{2,0},{1,0},{1,2},{2,1},{0,1},{0,2},{2,2}};

        System.out.println("Result for moves1: " + game.tictactoe(moves1));
        System.out.println("Result for moves2: " + game.tictactoe(moves2));
        System.out.println("Result for moves3: " + game.tictactoe(moves3));
    }
}
