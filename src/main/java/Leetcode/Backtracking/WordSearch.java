package Leetcode.Backtracking;

/**
 * https://leetcode.com/problems/word-search/#/description
 *
 * Created by venkatamunnangi on 7/6/17.
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist(board, y, x, w, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) {
            return true;
        }
        if (y<0 || x<0 || y == board.length || x == board[y].length) {
            // out of bounds
            return false;
        }
        if (board[y][x] != word[i]) {
            // if current character is not part of current word character.
            return false;
        }
        board[y][x] ^= 256; // marking visited
        boolean exist = exist(board, y, x+1, word, i+1)
                || exist(board, y, x-1, word, i+1)
                || exist(board, y+1, x, word, i+1)
                || exist(board, y-1, x, word, i+1);
        board[y][x] ^= 256; // swapping values back to original.
        return exist;
    }
}
