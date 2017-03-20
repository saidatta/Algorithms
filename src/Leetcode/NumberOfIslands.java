package Leetcode;

/**
 * https://leetcode.com/problems/number-of-islands/#/description
 *
 * Created by venkatamunnangi on 3/19/17.
 */
public class NumberOfIslands {
    private int m,n;

    public int numIslands(char[][] grid) {
        if(grid == null) {
            return -1;
        }
        n = grid.length;
        if(n == 0) {
            return -1;
        }
        m = grid[0].length;
        int count = 0;

        for(int i = 0; i<n;i++) {
            for(int j = 0; j<m; j++) {
                if(grid[i][j] == '1') {
                    numIslandsHelper(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void numIslandsHelper(int x, int y, char[][] grid) {
        if(x < 0 || y < 0 || x >= n || y >= m || grid[x][y] != '1') {
            // if out of bounds or not part of island.
            return;
        }

        // once visited, mark that island as visited.
        grid[x][y] = '2';
        numIslandsHelper(x-1, y, grid);
        numIslandsHelper(x, y-1, grid);
        numIslandsHelper(x+1, y, grid);
        numIslandsHelper(x, y+1, grid);
    }
}
