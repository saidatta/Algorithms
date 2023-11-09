package leetcode.array.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date 03/24/2016
 *
 * @author Tushar Roy
 *
 * A group of two or more people wants to meet and minimize the total travel distance.
 * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * Find the total distance that needs to be travelled to reach this meeting point.
 *
 * Time complexity O(m*n)
 * Space complexity O(m + n)
 *
 * https://leetcode.com/problems/best-meeting-point/
 */
public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        return minDistance1D(rows) + minDistance1D(cols);
    }

    private int minDistance1D(List<Integer> points) {
        int distance = 0;
        int i = 0;
        int j = points.size() - 1;
        while (i < j) {
            distance += points.get(j) - points.get(i);
            i++;
            j--;
        }
        return distance;
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            for (int[] ints : grid) {
                if (ints[col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }

    public static void main(String[] args) {
        BestMeetingPoint bmp = new BestMeetingPoint();
        int[][] grid = {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        System.out.print(bmp.minTotalDistance(grid));
    }
}
