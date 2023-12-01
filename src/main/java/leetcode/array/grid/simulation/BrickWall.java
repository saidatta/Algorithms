package leetcode.array.grid.simulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/brick-wall/
public class BrickWall {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> edgeCounts = new HashMap<>();
        int maxEdges = 0;

        for (List<Integer> row : wall) {
            int edge = 0;
            for (int i = 0; i < row.size() - 1; i++) { // Exclude the last brick's edge
                edge += row.get(i);
                edgeCounts.put(edge, edgeCounts.getOrDefault(edge, 0) + 1);
                maxEdges = Math.max(maxEdges, edgeCounts.get(edge));
            }
        }

        return wall.size() - maxEdges;
    }

    public static void main(String[] args) {
        BrickWall solution = new BrickWall();
        List<List<Integer>> wall = List.of(
                List.of(1, 2, 2, 1),
                List.of(3, 1, 2),
                List.of(1, 3, 2),
                List.of(2, 4),
                List.of(3, 1, 2),
                List.of(1, 3, 1, 1)
        );
        System.out.println(solution.leastBricks(wall)); // Output: 2
    }
}
