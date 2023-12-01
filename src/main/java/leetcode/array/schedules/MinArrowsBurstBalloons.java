package leetcode.array.schedules;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
public class MinArrowsBurstBalloons {
    private static final int BALLOON_START_IDX = 0;
    private static final int BALLOON_END_IDX = 1;
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        // Sort the balloons by their end coordinates
        Arrays.sort(points, Comparator.comparingInt(a -> a[BALLOON_END_IDX]));

        // Initialize the first arrow position to the end of the first balloon
        int arrowPos = points[0][BALLOON_END_IDX];
        int arrows = 1;  // We need at least one arrow

        // Iterate through the balloons
        for (int[] currentBalloon : points) {
            if (currentBalloon[BALLOON_START_IDX] > arrowPos) {
                // Shoot a new arrow
                arrows++;
                arrowPos = currentBalloon[BALLOON_END_IDX];
            }
        }

        return arrows;
    }

    public static void main(String[] args) {
        MinArrowsBurstBalloons solution = new MinArrowsBurstBalloons();
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println(solution.findMinArrowShots(points));  // Output: 2
    }
}


// The "Minimum Number of Arrows to Burst Balloons" problem requires finding the smallest number of arrows that must be
// shot vertically (upwards) to burst all given balloons. Each balloon is represented as a horizontal segment of the
// x-axis, and an arrow shot at any x-coordinate will burst all balloons whose x-range it intersects.
//
//        Approach:
//        This problem is a variation of the classic interval scheduling problem and can be solved efficiently using a
//        greedy algorithm. The key idea is to sort the balloons (intervals) by their end coordinates and then use as
//        few arrows as possible by shooting them at the end coordinates of the balloons.
//
//        Sort Balloons: Sort the array of balloons (points) based on their end coordinates (xend).
//        Find Minimum Arrows: Initialize the count of arrows to 1 (as at least one arrow is needed) and set an arrow position at the end coordinate of the first balloon. Then iterate through the sorted balloons:
//        If a balloon starts after the current arrow position, it means a new arrow is needed. Increment the arrow count and update the arrow position to this balloon's end coordinate.
//        If a balloon starts before or at the current arrow position, it can be burst by the current arrow, and no action is needed.
//        Return the Count: The final count of arrows is the minimum number needed to burst all balloons.
//        Example Explanation:
//        Example 1: points = [[10,16],[2,8],[1,6],[7,12]]
//        After sorting: [[1,6],[2,8],[7,12],[10,16]]
//        Shoot an arrow at x = 6, bursting balloons [1,6] and [2,8].
//        Shoot another arrow at x = 12, bursting the remaining balloons [7,12] and [10,16].
//        Total arrows needed: 2.
//        Example 2: points = [[1,2],[3,4],[5,6],[7,8]]
//        Since no balloons overlap, an arrow is needed for each, totaling 4 arrows.
//        Example 3: points = [[1,2],[2,3],[3,4],[4,5]]
//        After sorting: [[1,2],[2,3],[3,4],[4,5]] (same order)
//        Shoot an arrow at x = 2, bursting balloons [1,2] and [2,3].
//        Shoot another arrow at x = 4, bursting the remaining balloons [3,4] and [4,5].
//        Total arrows needed: 2.
//        This problem is an excellent example of a greedy approach, where making the locally optimal choice (shooting the arrow at the end of the current balloon) leads to a globally optimal solution.