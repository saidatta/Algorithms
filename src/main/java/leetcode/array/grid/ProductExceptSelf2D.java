package leetcode.array.grid;

// https://leetcode.com/problems/construct-product-matrix/
public class ProductExceptSelf2D {
    private static final int MOD = 12345;


    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] result = new int[n][m];

        // Calculate products for rows
        int[][] rowLeftProducts = new int[n][m];
        int[][] rowRightProducts = new int[n][m];
        for (int i = 0; i < n; i++) {
            int leftProduct = 1;
            for (int j = 0; j < m; j++) {
                rowLeftProducts[i][j] = leftProduct;
                leftProduct = (leftProduct * grid[i][j]) % 12345;
            }
            int rightProduct = 1;
            for (int j = m - 1; j >= 0; j--) {
                rowRightProducts[i][j] = rightProduct;
                rightProduct = (rightProduct * grid[i][j]) % 12345;
            }
        }

        // Calculate products for columns
        int[][] colTopProducts = new int[n][m];
        int[][] colBottomProducts = new int[n][m];
        for (int j = 0; j < m; j++) {
            int topProduct = 1;
            for (int i = 0; i < n; i++) {
                colTopProducts[i][j] = topProduct;
                topProduct = (topProduct * grid[i][j]) % 12345;
            }
            int bottomProduct = 1;
            for (int i = n - 1; i >= 0; i--) {
                colBottomProducts[i][j] = bottomProduct;
                bottomProduct = (bottomProduct * grid[i][j]) % 12345;
            }
        }

        // Compute the final result for each cell
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long val = (long) rowLeftProducts[i][j] * rowRightProducts[i][j] % 12345;
                val = val * colTopProducts[i][j] % 12345;
                val = val * colBottomProducts[i][j] % 12345;
                result[i][j] = (int) val;
            }
        }

        return result;
    }



    public static void main(String[] args) {
        ProductExceptSelf2D sol = new ProductExceptSelf2D();

        // Test cases
        int[][] grid1 = {{1, 2}, {3, 4}};
        int[][] grid2 = {{12345}, {2}, {1}};

        printMatrix(sol.constructProductMatrix(grid1));
        System.out.println("------------------------");
        printMatrix(sol.constructProductMatrix(grid2));
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }
}
