package leetcode.array.grid;

import java.util.Arrays;

// https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/
public class WidestVerticalArea {
    public int maxWidthOfVerticalArea(int[][] points) {
        int[] xCoordinates = new int[points.length];

        // Extract x-coordinates from the points
        for (int i = 0; i < points.length; i++) {
            xCoordinates[i] = points[i][0];
        }

        // Sort the x-coordinates
        Arrays.sort(xCoordinates);

        // Find the maximum distance between consecutive x-coordinates
        int maxWidth = 0;
        for (int i = 1; i < xCoordinates.length; i++) {
            maxWidth = Math.max(maxWidth, xCoordinates[i] - xCoordinates[i - 1]);
        }

        return maxWidth;
    }
}
