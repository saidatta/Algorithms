package Leetcode.DP;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/counting-bits/
 *
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the
 * number of 1's in their binary representation and return them as an array.
 *
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 *
 * Created by venkatamunnangi on 3/2/17.
 */
public class CountingBits {
    public int[] countBits(int num) {
        if(num < 0 ) {
            return null;
        }

        int[] ans = new int[num+1];

        int offset = 1;
        for(int i = 1; i<ans.length;i++) {
            if(offset * 2 == i) {
                // if it is a power of 2. {2,4,8,16,32...}
                offset *= 2;
            }

            // recurrence relation.
            ans[i] = ans[i - offset] + 1;
        }

        return ans;
    }

    public static void main(String [] args) {
        CountingBits countingBits = new CountingBits();
        System.out.println(Arrays.toString(countingBits.countBits(5)));
    }
}
