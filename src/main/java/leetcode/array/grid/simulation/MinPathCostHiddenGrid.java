package leetcode.array.grid.simulation;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import leetcode.array.grid.traversal.DiagonalTraverseII.Pair;

class MinPathCostHiddenGrid {
    // Directions for DFS exploration
    private static final char[] DIRECTIONS = {'U', 'R', 'D', 'L'};
    // Corresponding steps for each direction
    private static final int[][] STEPS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    // Map to store the cost of each accessible cell
    private Map<Pair<Integer, Integer>, Integer> gridMap;
    // Store the target cell coordinates
    private Pair<Integer, Integer> target;

    public int findShortestPath(GridMaster master) {
        gridMap = new HashMap<>();
        // Perform DFS to explore the grid
        dfs(master, 0, 0);

        // If the target is not found during exploration, return -1
        if (target == null) return -1;

        // Use Dijkstra's algorithm to find the minimum path cost
        return dijkstra();
    }

    private void dfs(GridMaster master, int x, int y) {
        if (master.isTarget()) {
            target = new Pair<>(x, y);
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + STEPS[i][0];
            int nextY = y + STEPS[i][1];
            Pair<Integer, Integer> nextCell = new Pair<>(nextX, nextY);

            // Explore the next cell if it's not already visited and can move in that direction
            if (!gridMap.containsKey(nextCell) && master.canMove(DIRECTIONS[i])) {
                int cost = master.move(DIRECTIONS[i]);
                gridMap.put(nextCell, cost);
                dfs(master, nextX, nextY);
                // Move back to the original cell
                master.move(DIRECTIONS[(i + 2) % 4]);
            }
        }
    }

    private int dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            Pair<Integer, Integer> currentCell = new Pair<>(current[0], current[1]);
            int currentCost = current[2];

            // Check if we've reached the target cell
            if (currentCell.equals(target)) {
                return currentCost;
            }

            // Explore all accessible neighbors
            for (int[] step : STEPS) {
                int nextX = current[0] + step[0];
                int nextY = current[1] + step[1];
                Pair<Integer, Integer> nextCell = new Pair<>(nextX, nextY);

                // If the cell is valid and not yet visited, add it to the priority queue
                if (gridMap.containsKey(nextCell)) {
                    int nextCost = currentCost + gridMap.remove(nextCell);
                    pq.offer(new int[]{nextX, nextY, nextCost});
                }
            }
        }

        // No path found to the target
        return -1;
    }

  interface GridMaster {
      boolean canMove(char direction);
      int move(char direction);
      boolean isTarget();
  }
}
