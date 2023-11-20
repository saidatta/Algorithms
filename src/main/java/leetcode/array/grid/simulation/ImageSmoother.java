package leetcode.array.grid.simulation;

import java.util.Arrays;

// https://leetcode.com/problems/image-smoother/description/
// TODO - https://leetcode.com/problems/image-smoother/editorial/
public class ImageSmoother {

    public int[][] imageSmoother(int[][] img) {
        int rows = img.length, cols = img[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = calculateAverage(img, i, j);
            }
        }

        return result;
    }

    private int calculateAverage(int[][] img, int row, int col) {
        int rows = img.length, cols = img[0].length;
        int sum = 0, count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols) {
                    sum += img[i][j];
                    count++;
                }
            }
        }

        return sum / count;
    }

    public static void main(String[] args) {
        ImageSmoother smoother = new ImageSmoother();
        int[][] img1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] img2 = {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        System.out.println(Arrays.deepToString(smoother.imageSmoother(img1))); // Output: [[0,0,0],[0,0,0],[0,0,0]]
        System.out.println(Arrays.deepToString(smoother.imageSmoother(img2))); // Output: [[137,141,137],[141,138,141],[137,141,137]]
    }
}

