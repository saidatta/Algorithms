package Leetcode.DP;

/**
 * https://leetcode.com/problems/unique-paths/#/description
 *
 * Created by venkatamunnangi on 3/24/17.
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] tab = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i==0 || j==0){
                    //boundary of the grid.
                    tab[i][j] = 1;
                }
                else{
                    //the number of ways is the sum of
                    // left and up ways.
                    tab[i][j] = tab[i-1][j]+tab[i][j-1];
                }
            }
        }

        return tab[m-1][n-1];
    }

    public static void main(String [] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(3, 7));
    }
}
