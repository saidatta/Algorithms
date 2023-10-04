package leetcode.array.grid;

// https://leetcode.com/problems/flood-fill/description/
public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // If the starting color is already the same as newColor, return the image.
        int startingColor = image[sr][sc];
        if (startingColor != newColor) {
            dfs(image, sr, sc, startingColor, newColor);
        }
        return image;
    }

    public void dfs(int[][] image, int i, int j, int startingColor, int newColor) {
        // If out of bounds or the current pixel doesn't have the starting color, return.
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != startingColor) {
            return;
        }

        // Change the color of the pixel.
        image[i][j] = newColor;

        // Recurse in all 4 directions.
        dfs(image, i + 1, j, startingColor, newColor);
        dfs(image, i - 1, j, startingColor, newColor);
        dfs(image, i, j + 1, startingColor, newColor);
        dfs(image, i, j - 1, startingColor, newColor);
    }

    public static void main(String[] args) {
        FloodFill solver = new FloodFill();
        int[][] image1 = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int[][] result1 = solver.floodFill(image1, 1, 1, 2);
        for (int[] row : result1) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }

        System.out.println();

        int[][] image2 = {
                {0, 0, 0},
                {0, 0, 0}
        };
        int[][] result2 = solver.floodFill(image2, 0, 0, 0);
        for (int[] row : result2) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }
}
