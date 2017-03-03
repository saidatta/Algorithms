package Leetcode.DP;

/**
 * https://leetcode.com/problems/counting-bits/
 *
 * Created by venkatamunnangi on 3/2/17.
 */
public class CountingBits {
    public int[] countBits(int num) {
        if(num < 0 ) {
            return null;
        }

        int[] ans = new int[num+1];

        int offset = 0;
        for(int i = 0; i<ans.length;i++) {
            if(offset * 2 == i) {
                offset *= 2;
            }

            ans[i] = ans[i - offset] + 1;
        }

        return ans;
    }

}
