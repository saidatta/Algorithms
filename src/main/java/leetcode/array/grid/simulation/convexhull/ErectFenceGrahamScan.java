package leetcode.array.grid.simulation.convexhull;

import java.util.Arrays;
import java.util.Stack;

// https://leetcode.com/problems/erect-the-fence/
// https://www.youtube.com/watch?v=SBdWdT_5isI
// https://www.youtube.com/watch?v=B2AJoQSZf4M
public class ErectFenceGrahamScan {
    /**
     * Implements the Graham Scan algorithm to compute the convex hull of a set of points.
     * The method starts by finding the point with the lowest y-coordinate as the reference point.
     * It then sorts the other points based on the polar angle they make with this point and the x-axis.
     * Points are then iterated over, and a stack is used to keep track of the points forming the convex hull.
     * @return An array of coordinates representing the points on the convex hull.
     */
    public int[][] outerTrees(int[][] pointsArray) {
        if (pointsArray.length <= 1) {
            return pointsArray;
        }

        // Convert the input array to an array of Point records
        Point[] points = Arrays.stream(pointsArray)
                .map(p -> new Point(p[0], p[1]))
                .toArray(Point[]::new);

        // Find the bottom-left point to use as a reference
        Point referencePoint = bottomLeft(points);

        // Sort the points by polar angle with referencePoint; if collinear, sort by distance to referencePoint
        Arrays.sort(points, (p, q) -> {
            int orientationDiff = orientation(referencePoint, p, q) - orientation(referencePoint, q, p);
            if (orientationDiff == 0) {
                return distance(referencePoint, p) - distance(referencePoint, q);
            } else {
                return orientationDiff > 0 ? 1 : -1;
            }
        });

        // Handling collinear points
        int i = points.length - 1;
        while (i >= 0 && orientation(referencePoint, points[points.length - 1], points[i]) == 0) {
            i--;
        }
        for (int left = i + 1, right = points.length - 1; left < right; left++, right--) {
            Point temp = points[left];
            points[left] = points[right];
            points[right] = temp;
        }

        // Construct the convex hull using a stack
        Stack<Point> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);
        for (int j = 2; j < points.length; j++) {
            Point top = stack.pop();
            while (orientation(stack.peek(), top, points[j]) > 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(points[j]);
        }

        // Convert the stack back to int[][] for the output
        int[][] result = new int[stack.size()][2];
        for (int k = result.length - 1; k >= 0; k--) {
            Point p = stack.pop();
            result[k][0] = p.x;
            result[k][1] = p.y;
        }
        return result;
    }

    /**
     * Determines the orientation of the triplet (p, q, r).
     * This is crucial for understanding the direction of the turn formed by these points.
     * @return A positive value for counterclockwise orientation, negative for clockwise, and 0 for collinear.
     * The method uses the cross-product of vectors (q-p) and (r-q) to determine this.
     */
    private int orientation(Point p, Point q, Point r) {
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    }

    /**
     * Computes the squared Euclidean distance between two points.
     * This is used when sorting points by polar angle to keep the farthest point in case of collinear.
     */
    private int distance(Point p, Point q) {
        return (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
    }

    /**
     * Finds the point with the lowest y-coordinate; in case of a tie, selects the point with the lowest x-coordinate.
     * This point is used as the starting point for the Graham Scan algorithm and is guaranteed to be part of the convex hull.
     */
    private static Point bottomLeft(Point[] points) {
        Point bottomLeft = points[0];
        for (Point p : points) {
            if (p.y < bottomLeft.y || (p.y == bottomLeft.y && p.x < bottomLeft.x)) {
                bottomLeft = p;
            }
        }
        return bottomLeft;
    }

    record Point(int x, int y) {}
}