package leetcode.array.grid.traversal;

public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int r = 0, c = 0;

        for (int i = 0; i < result.length; i++) {
            result[i] = matrix[r][c];
            // If sum of indices is even, we move in the up-right direction
            if ((r + c) % 2 == 0) {
                if (c == n - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
            }
            // If sum of indices is odd, we move in the down-left direction
            else {
                if (r == m - 1) {
                    c++;
                } else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        DiagonalTraverse obj = new DiagonalTraverse();

        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] result1 = obj.findDiagonalOrder(matrix1);
        for (int num : result1) {
            System.out.print(num + " ");
        }
        System.out.println();  // Expected output: 1 2 4 7 5 3 6 8 9

        int[][] matrix2 = {{1, 2}, {3, 4}};
        int[] result2 = obj.findDiagonalOrder(matrix2);
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();  // Expected output: 1 2 3 4
    }
}
