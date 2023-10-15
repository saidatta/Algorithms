package leetcode;

import java.util.LinkedList;
import java.util.Queue;


/**
 * https://leetcode.com/problems/number-of-islands/#/description
 *
 * Created by venkatamunnangi on 3/19/17.
 */
public class NumberOfIslands {
    interface SearchStrategy {
        void search(int x, int y, char[][] grid, boolean[][] visited);
    }

    class DepthFirstSearch implements SearchStrategy {
        private int n, m;

        @Override
        public void search(int x, int y, char[][] grid, boolean[][] visited) {
            if (x < 0 || y < 0 || x >= n || y >= m || visited[x][y] || grid[x][y] != '1') {
                return;
            }

            visited[x][y] = true;
            search(x - 1, y, grid, visited);
            search(x, y - 1, grid, visited);
            search(x + 1, y, grid, visited);
            search(x, y + 1, grid, visited);
        }
    }

    class BreadthFirstSearch implements SearchStrategy {
        private int n, m;

        private final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        @Override
        public void search(int x, int y, char[][] grid, boolean[][] visited) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{x, y});
            visited[x][y] = true;
            n = grid.length;
            m = grid[0].length;
            while(!queue.isEmpty()) {
                int[] curr = queue.poll();
                for(int[] dir : dirs) {
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    if(nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && grid[nx][ny] == '1') {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    class IslandCounter {
        private int n, m;

        public int numIslands(char[][] grid) {
            this.n = grid.length;
            this.m = grid[0].length;
            SearchStrategy searchStrategy = (n * m <= 5000) ? new DepthFirstSearch() : new BreadthFirstSearch();
            return numIslands(grid, searchStrategy);
        }

        public int numIslands(char[][] grid, SearchStrategy strategy) {
            // base cases
            if (grid == null) {
                return -1;
            }
            n = grid.length;
            if (n == 0) {
                return -1;
            }

            //initializations
            m = grid[0].length;
            int count = 0;
            boolean[][] visited = new boolean[n][m];  // to mark visited cells
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        strategy.search(i, j, grid, visited);
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
