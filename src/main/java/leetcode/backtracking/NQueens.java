package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

// https://leetcode.com/problems/n-queens/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/n-queens/
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = createEmptyBoard(n);
        List<List<String>> solutions = new ArrayList<>();
        findSolutions(board, 0, solutions);
        return solutions;
    }

    private char[][] createEmptyBoard(int size) {
        char[][] board = new char[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(board[i], '.');
        }
        return board;
    }

    private void findSolutions(char[][] board, int colIndex, List<List<String>> solutions) {
        if (colIndex == board.length) {
            solutions.add(constructBoardRepresentation(board));
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (isPlacementValid(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                findSolutions(board, colIndex + 1, solutions);
                board[i][colIndex] = '.';
            }
        }
    }

    private boolean isPlacementValid(char[][] board, int x, int y) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < y; j++) {
                if (board[i][j] == 'Q' && (isDiagonalMatch(x, y, i, j) || x == i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDiagonalMatch(int x1, int y1, int x2, int y2) {
        return x1 + y1 == x2 + y2 || x1 - y1 == x2 - y2;
    }

    private List<String> constructBoardRepresentation(char[][] board) {
        return Arrays.stream(board)
                .map(String::new)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        NQueens solver = new NQueens();
        System.out.println(solver.solveNQueens(4));  // Sample Test
    }
}
