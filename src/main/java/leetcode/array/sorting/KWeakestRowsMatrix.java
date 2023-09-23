package leetcode.array.sorting;

import java.util.Arrays;

public class KWeakestRowsMatrix {
    public static int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;

        // Create an array to store the row index and its corresponding number of soldiers.
        int[][] soldierCounts = new int[m][2];

        for (int i = 0; i < m; i++) {
            soldierCounts[i][0] = i;
            soldierCounts[i][1] = countSoldiers(mat[i]);
        }

        // Sort the rows first by the number of soldiers and then by row index.
        Arrays.sort(soldierCounts, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        // Extract the first k indices.
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = soldierCounts[i][0];
        }

        return result;
    }

    private static int countSoldiers(int[] row) {
        int count = 0;
        for (int num : row) {
            if (num == 1) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] mat1 = {
                {1,1,0,0,0},
                {1,1,1,1,0},
                {1,0,0,0,0},
                {1,1,0,0,0},
                {1,1,1,1,1}
        };
        System.out.println(Arrays.toString(kWeakestRows(mat1, 3))); // Expected output: [2,0,3]

        int[][] mat2 = {
                {1,0,0,0},
                {1,1,1,1},
                {1,0,0,0},
                {1,0,0,0}
        };
        System.out.println(Arrays.toString(kWeakestRows(mat2, 2))); // Expected output: [0,2]
    }
}
