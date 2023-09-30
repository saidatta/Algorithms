package leetcode.array.schedules;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/interval-list-intersections/
public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        // List to store the intersections
        List<int[]> intersections = new ArrayList<>();

        // Pointers for arrays A and B
        int i = 0, j = 0;

        // Loop until one of the arrays is exhausted
        while (i < A.length && j < B.length) {
            // Calculate the potential intersection
            int start = Math.max(A[i][0], B[j][0]);
            int end = Math.min(A[i][1], B[j][1]);

            // If the calculated interval is valid, add it to the result
            if (start <= end) {
                intersections.add(new int[]{start, end});
            }

            // Move the pointer which is pointing to the interval
            // that ends first (to explore the next potential overlap)
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        // Convert the list of intersections to an array and return
        return intersections.toArray(new int[intersections.size()][]);
    }

}
