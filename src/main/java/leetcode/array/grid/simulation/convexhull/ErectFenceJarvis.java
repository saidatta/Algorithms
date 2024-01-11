package leetcode.array.grid.simulation.convexhull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/erect-the-fence/description/
// https://www.youtube.com/watch?v=Vu84lmMzP2o
public class ErectFenceJarvis {

    /**
     * Implements the Jarvis March algorithm (also known as the Gift Wrapping algorithm)
     * to find the Convex Hull of a set of points. The Convex Hull is the smallest convex boundary
     * that encloses all points. This method starts from the leftmost point and iteratively
     * selects the most counterclockwise point relative to the current point until it wraps
     * back to the starting point. The algorithm uses cross product to determine the relative
     * orientation of points and identifies collinear points that lie on the boundary.
     *
     * @param pointsArray Array of points where each point is represented as an array of two integers.
     * @return Coordinates of trees that are exactly located on the fence perimeter.
     */
    public int[][] outerTrees(int[][] pointsArray) {
        Point[] points = new Point[pointsArray.length];
        for (int i = 0; i < pointsArray.length; i++) {
            points[i] = new Point(pointsArray[i][0], pointsArray[i][1]);
        }

        Set<Point> hull = new HashSet<>();
        // If fewer than 4 points, all are part of the hull
        if (points.length < 4) {
            Collections.addAll(hull, points);

            return convertSetToArray(hull);
        }

        // Find the leftmost point as starting point
        int leftMostIndex = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i].x < points[leftMostIndex].x) {
                leftMostIndex = i;
            }
        }

        // Iterate to find all points on hull
        int p = leftMostIndex;
        do {
            // Next point in hull initialized to the one immediately next in the array
            int q = (p + 1) % points.length;
            for (int i = 0; i < points.length; i++) {
                if (hull.contains(points[i])) {
                    continue;
                }

                // Check if points[i] is more left than current 'q'
                // Using cross-product for orientation
                if (orientation(points[p], points[i], points[q]) < 0) {
                    q = i;
                }
            }

            // Add collinear points to hull
            for (int i = 0; i < points.length; i++) {
                if (i != p && i != q && orientation(points[p], points[i], points[q]) == 0
                                && inBetween(points[p], points[i], points[q])) {
                    hull.add(points[i]);
                }
            }

            // Add the most counterclockwise point to hull and move to next point
            hull.add(points[q]);
            p = q;
        } while (p != leftMostIndex);

        // Convert hull points to array and return
        return convertSetToArray(hull);
    }

    // Determines the orientation of triplet (p, q, r) using cross product.
    // If positive: counterclockwise, negative: clockwise, 0: collinear
    public int orientation(Point p, Point q, Point r) {
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    }

    // Checks if point 'i' is on the line segment 'pq'
    public boolean inBetween(Point p, Point i, Point q) {
        boolean isInXRange = (i.x >= p.x && i.x <= q.x) || (i.x <= p.x && i.x >= q.x);
        boolean isInYRange = (i.y >= p.y && i.y <= q.y) || (i.y <= p.y && i.y >= q.y);
        return isInXRange && isInYRange;
    }

    private int[][] convertSetToArray(Set<Point> points) {
        int[][] result = new int[points.size()][2];
        int i = 0;
        for (Point p : points) {
            result[i][0] = p.x;
            result[i][1] = p.y;
            i++;
        }
        return result;
    }

    record Point(int x, int y) {
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Point point)) {
                return false;
            }
            return x == point.x && y == point.y;
        }
    }

    public static void main(String[] args) {
        ErectFenceJarvis solution = new ErectFenceJarvis();
        int[][] trees1 = {{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}};
        System.out.println(
                Arrays.deepToString(solution.outerTrees(trees1))); // [[1, 1], [2, 0], [4, 2], [3, 3], [2, 4]]

        int[][] trees2 = {{1, 2}, {2, 2}, {4, 2}};
        System.out.println(Arrays.deepToString(solution.outerTrees(trees2))); // [[4, 2], [2, 2], [1, 2]]
    }
}
