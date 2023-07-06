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
//            observation that the binary representation of a number i is the same as the binary representation of
//            i - offset, with an additional '1'. For example, the binary representation of 5 (101) is the same as the
//            binary representation of 1 (1), with an additional '1'. This holds because i is always either a power of
//            2 or the sum of a smaller power of 2 (represented by offset) and a number that has been encountered before
//            (represented by i - offset). Therefore, the number of '1's in the binary representation of i is the number
//            of '1's in the binary representation of i - offset, plus one.
            ans[i] = ans[i - offset] + 1;
        }

        return ans;
    }

    public static void main(String [] args) {
        CountingBits countingBits = new CountingBits();
        System.out.println(Arrays.toString(countingBits.countBits(5)));
    }
}
