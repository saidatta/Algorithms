package Leetcode.dp;

/**
 * https://leetcode.com/problems/unique-binary-search-trees/?tab=Description
 *
 * Created by venkatamunnangi on 3/3/17.
 */
public class UniqueBST {
    public int numTrees(int n) {
        int [] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;

        for(int i=2; i<=n; ++i) {
            for(int j=1; j<=i; ++j) {
                G[i] += G[j-1] * G[i-j];
            }
        }

        return G[n];
    }

    public int numTrees2(int n) {
        int ans =1;

        for(int i=n+1;i<=2*n;i++) {
            ans = ans * i/(i-n);
        }

        return ans/(n+1);
    }
}
