package leetcode.array.grid;

// https://leetcode.com/problems/flipping-an-image/description/
public class FlipInvertImage {
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;

        // Iterate through each row in the image.
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;

            // Use two pointers (left and right) to reverse the row.
            while (left <= right) {
                int temp = image[i][left];
                image[i][left] = image[i][right] ^ 1;  // Reverse and invert.
                image[i][right] = temp ^ 1;            // Reverse and invert.

                left++;
                right--;
            }
        }

        return image;
    }

    public static void main(String[] args) {
        FlipInvertImage sol = new FlipInvertImage();

        int[][] image1 = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] result1 = sol.flipAndInvertImage(image1);
        // Expected output: [[1,0,0],[0,1,0],[1,1,1]]

        int[][] image2 = {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}};
        int[][] result2 = sol.flipAndInvertImage(image2);
        // Expected output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

        for (int[] row : result1) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        System.out.println();

        for (int[] row : result2) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
