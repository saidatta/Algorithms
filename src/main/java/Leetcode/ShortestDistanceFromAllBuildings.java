package Leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/#/description
 *
 * Created by venkatamunnangi on 4/30/17.
 */
public class ShortestDistanceFromAllBuildings {
    int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String... args) {
        int[][] a = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        ShortestDistanceFromAllBuildings shortestDistanceFromAllBuildings = new ShortestDistanceFromAllBuildings();
        System.out.println(shortestDistanceFromAllBuildings.shortestDistance(a));
    }

    public int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        // Initialize building list and accessibility matrix `grid`
        List<Tuple> buildings = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    buildings.add(new Tuple(i, j, 0));
                }
                grid[i][j] = -grid[i][j];
            }
        }
        // BFS from every building
        for (int k = 0; k < buildings.size(); ++k) {
            bfs(buildings.get(k), k, dist, grid, m, n);
        }
        // Find the minimum distance
        // when traversing shortestDistance is never negative.
        int shortestDistanceFromAllBuildings = -1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean allBuildingsHaveBeenIndexedAtPoint = grid[i][j] == buildings.size();
                if (allBuildingsHaveBeenIndexedAtPoint && (shortestDistanceFromAllBuildings < 0 || dist[i][j] < shortestDistanceFromAllBuildings)) {
                    shortestDistanceFromAllBuildings = dist[i][j];
                }
            }
        }
        return shortestDistanceFromAllBuildings;
    }

    public void bfs(Tuple root, int buildingIndex, int[][] dist, int[][] grid, int m, int n) {
        Queue<Tuple> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Tuple b = q.poll();
            // We are incrementing the distance that has been memorized from the previous points.
            dist[b.y][b.x] += b.dist;
            for (int i = 0; i < 4; ++i) {
                int x = b.x + dx[i], y = b.y + dy[i];
                if (y >= 0 && x >= 0 && y < m && x < n && grid[y][x] == buildingIndex) {
                    grid[y][x] = buildingIndex + 1;
                    q.add(new Tuple(y, x, b.dist + 1));
                }
            }
        }
    }

    class Tuple {
        public int y;
        public int x;
        public int dist;

        public Tuple(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }
}