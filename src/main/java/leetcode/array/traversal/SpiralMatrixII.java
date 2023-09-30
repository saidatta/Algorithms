package leetcode.array.traversal;

// https://leetcode.com/problems/spiral-matrix-ii/description/
public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int rowBegin = 0;
        int rowEnd = n - 1;
        int colBegin = 0;
        int colEnd = n - 1;

        // start from 1
        int num = 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j++) {
                matrix[rowBegin][j] = num++;
            }
            // next round adjustment
            rowBegin++;

            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j++) {
                matrix[j][colEnd] = num++;
            }
            // next round adjustment
            colEnd--;

            // Traverse Left
            for (int j = colEnd; j >= colBegin; j--) {
                matrix[rowEnd][j] = num++;
            }
            // next round adjustment
            rowEnd--;

            // Traverse Up
            for (int j = rowEnd; j >= rowBegin; j--) {
                matrix[j][colBegin] = num++;
            }
            // next round adjustment
            colBegin++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        SpiralMatrixII generator = new SpiralMatrixII();

        int[][] result = generator.generateMatrix(3);
        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }
}
