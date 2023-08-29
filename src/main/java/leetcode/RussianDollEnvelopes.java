package leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/russian-doll-envelopes/#/description
 *
 * Created by venkatamunnangi on 4/30/17.
 */
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
        int max = 0;
        int dp [] = new int [envelopes.length];
        for(int i = 0; i < envelopes.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;    }
}
