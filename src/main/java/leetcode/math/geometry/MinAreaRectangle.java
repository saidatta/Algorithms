package leetcode.math.geometry;

import java.util.HashSet;
import java.util.Set;

public class MinAreaRectangle {
    private static final int MAX_COORDINATE = 40001;

    /**
     * Finds the minimum area of a rectangle formed by given points.
     * Considers each pair of points as the diagonal of potential rectangles.
     *
     * @param points Array of [x, y] coordinates.
     * @return The minimum area of a rectangle, or 0 if no rectangle is formed.
     */
    public int minAreaRect(int[][] points) {
        // A set for efficient lookups of point existence
        Set<Integer> pointSet = new HashSet<>();
        for (int[] point : points) {
            int encodedPoint = encodePoint(point[0], point[1]);
            pointSet.add(encodedPoint);
        }

        int minArea = Integer.MAX_VALUE;
        // Iterate over pairs of points to consider them as diagonals of rectangles
        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length; ++j) {
                // we select random 2 points here, e.g, (1,1) and (5,5). Then we try to look for (5,1) or (1,5)
                // this is valid since we are trying to find min area thats parallel to X Y axis.
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];

                // Check if the points form the diagonal of a rectangle (non-zero area and not on the same line)
                if (x1 != x2 && y1 != y2) {
                    // Check if the other two corners of the rectangle exist
                    if (pointSet.contains(encodePoint(x1, y2)) && pointSet.contains(encodePoint(x2, y1))) {
                        // Calculate area of the rectangle and update minArea if smaller
                        int area = Math.abs(x2 - x1) * Math.abs(y2 - y1);
                        minArea = Math.min(minArea, area);
                    }
                }
            }
        }

        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }

    /**
     * Encodes a point (x, y) into a single integer for efficient storage and lookup.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @return The encoded point as an integer.
     */
    private int encodePoint(int x, int y) {
        return MAX_COORDINATE * x + y;
    }

    public static void main(String[] args) {
        MinAreaRectangle solution = new MinAreaRectangle();
        System.out.println(solution.minAreaRect(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}})); // Output: 4
        System.out.println(solution.minAreaRect(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}})); // Output: 2
    }
}

// - The points are stored in a HashSet using an encoding scheme to handle large coordinates. Each point [x, y]
//    is encoded as 40001 * x + y. This encoding ensures a unique value for each point since 0 <= x, y <= 4 * 10^4.
// - The minArea variable is initialized to Integer.MAX_VALUE and updated if a smaller area is found.
// -  The nested loops iterate over all pairs of points to check if they can form diagonals of a rectangle.
// - For each pair of points, the code checks if the corresponding diagonal points exist in the pointSet.
// - If a valid rectangle is formed, its area is calculated and compared with the current minimum area.
// - The method returns the minimum area if a valid rectangle is found, or 0 if no rectangle can be formed.