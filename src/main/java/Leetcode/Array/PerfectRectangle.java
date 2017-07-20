package Leetcode.Array;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/perfect-rectangle/#/description
 *
 * Created by venkatamunnangi on 5/21/17.
 */
public class PerfectRectangle {
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) {
            return false;
        }

        int x1 = Integer.MAX_VALUE;
        int y1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y2 = Integer.MIN_VALUE;

        Set<String> points = new HashSet<>();

        int area = 0;

        for(int[] rect: rectangles) {
            x1 = Integer.min(rect[0], x1);
            y1 = Integer.min(rect[1], y1);
            x2 = Integer.max(rect[2], x2);
            y2 = Integer.max(rect[3], y2);

            String p1 = constructPoint(rect[0], rect[1]);
            String p2 = constructPoint(rect[0], rect[3]);
            String p3 = constructPoint(rect[2], rect[3]);
            String p4 = constructPoint(rect[2], rect[1]);

            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);

            if(!points.add(p1)) {
                points.remove(p1);
            }
            if(!points.add(p2)) {
                points.remove(p2);
            }
            if(!points.add(p3)) {
                points.remove(p3);
            }
            if(!points.add(p4)) {
                points.remove(p4);
            }
        }

        if(!points.contains(constructPoint(x1, y1)) || !points.contains(constructPoint(x1, y2)) || !points.contains(constructPoint(x2, y1)) || !points.contains(constructPoint(x2, y2)) || points.size() != 4) {
            return false;
        }

        return area == (x2-x1) * (y2-y1);
    }

    private String constructPoint(int x1, int y1) {
        return x1+","+y1;
    }

    public boolean isRectangleCover2(int[][] rectangles) {

        if (rectangles.length == 0 || rectangles[0].length == 0) return false;

        int x1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y1 = Integer.MAX_VALUE;
        int y2 = Integer.MIN_VALUE;

        HashSet<String> set = new HashSet<String>();
        int area = 0;

        for (int[] rect : rectangles) {
            x1 = Math.min(rect[0], x1);
            y1 = Math.min(rect[1], y1);
            x2 = Math.max(rect[2], x2);
            y2 = Math.max(rect[3], y2);

            area += (rect[2] - rect[0]) * (rect[3] - rect[1]);

            String s1 = rect[0] + " " + rect[1];
            String s2 = rect[0] + " " + rect[3];
            String s3 = rect[2] + " " + rect[3];
            String s4 = rect[2] + " " + rect[1];

            if (!set.add(s1)) set.remove(s1);
            if (!set.add(s2)) set.remove(s2);
            if (!set.add(s3)) set.remove(s3);
            if (!set.add(s4)) set.remove(s4);
        }

        if (!set.contains(x1 + " " + y1) || !set.contains(x1 + " " + y2) || !set.contains(x2 + " " + y1) || !set.contains(x2 + " " + y2) || set.size() != 4) return false;

        return area == (x2-x1) * (y2-y1);
    }

    public static void main(String... args) {
        PerfectRectangle perfectRectangle = new PerfectRectangle();
    }
}
