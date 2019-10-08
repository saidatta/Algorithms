package Leetcode;

/**
 * https://leetcode.com/problems/sort-colors/#/description
 *
 * DCP_35
 * Created by venkatamunnangi on 3/28/17.
 */
public class SortColors {
    // O (2n)
    public void sortColors(int[] A) {
        int n = A.length;
        int endIndex=n-1, startIndex=0;
        for (int i=0; i<=endIndex; i++) {
            while (A[i]==2 && i<endIndex) {
                int temp = A[i];
                A[i] = A[endIndex];
                A[endIndex--] = temp;
            }
            while (A[i]==0 && i>startIndex) {
                int temp = A[i];
                A[i] = A[startIndex];
                A[startIndex++] = temp;
            }
        }
    }
}
