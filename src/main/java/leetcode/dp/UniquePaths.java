package leetcode.dp;

/**
 * https://leetcode.com/problems/unique-paths/#/description
 *
 * Created by venkatamunnangi on 3/24/17.
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] tab = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
//                    If the robot is on the first row (i.e., i = 0) or the first column (i.e., j = 0), there's only
//                    one way to reach that cell: always move right (for the first row) or always move down (for the
//                    first column).
                    tab[i][j] = 1;
                } else {
//                    For any other cell (i, j), the robot can come either from the cell above it (i-1, j) or from the
//                    cell to its left (i, j-1).
//                   The number of ways to reach cell (i, j) is simply the sum of the ways to reach (i-1, j)
//                   and (i, j-1).
//                   In other words, tab[i][j] = tab[i-1][j] + tab[i][j-1].
                    tab[i][j] = tab[i - 1][j] + tab[i][j - 1];
                }
            }
        }

        return tab[m - 1][n - 1];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(2, 2));
    }
}
