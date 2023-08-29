package leetcode.dp;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/decode-ways/#/description
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class DecodeWays {
    static int NUM_LETTERS = 26;

    public int numDecodingsRecursion(String s) {
        return helper(s);
    }

    private int helper(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        } else  if(n <= 1) {
            return 1;
        }

        int total = 0;

        if(Integer.parseInt(s.substring(2)) <= 26) {
            total +=  helper(s.substring(2));
        }

        total += helper(s.substring(1));

        return total;
    }

    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }

        // We can explain memo[n] and memo[n-1] using the following cases:
        // Case 1: all zero string. The 1 here won't have any effect. Statement "continue" in the for loop will lead to mem[0] = 0.
        // Case 2: zero is in the last digit i.e. 10. Still two ways of decoding. 1 or 10. memo[n] =1 and memo[n-1] = 1. Adds up to 2.
        // Case 3: zero is not in the last digit i.e. 11. Still two ways of decoding. 1 or 11.
        // Case 4: i.e. 30 82. These numbers only has 1 way of decoding. This is taken care of by the for loop by moving memo[n-1] with 1 to memo[n-2].
        int[] memo = new int[n+1];
        memo[n]  = 1;
        memo[n-1] = s.charAt(n-1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--) {

            // For condition -> s.charAt(i) == '0', dont do processing and let it be incremented to next.
            // Case 1: individual 0 does not work
            // Case 2: 0 preceding any number does not work

            if (s.charAt(i) != '0') {
                // memo[i+1]+memo[i+2]
                // The character pair works, so we need to add up the total ways of decoding for the two pairs.
                // memo[i+1]
                // The pair does not work, just need to move the previous total so that memo[0]
                // will eventually have the right count.
                // Notice that we do not need to +1 here because we are counting the number of
                // ways to separate the string.
                memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= NUM_LETTERS) ? memo[i + 1] + memo[i + 2] : memo[i + 1];
            }
        }

        return memo[0];
    }

    public static void main(String [] args) {
        DecodeWays decodeWays = new DecodeWays();
        out.println(decodeWays.numDecodings("00"));
        out.println(decodeWays.numDecodingsRecursion("111"));

        out.println(decodeWays.numDecodings("111"));
        out.println(decodeWays.numDecodings("14122"));
        out.println(decodeWays.numDecodings("12"));
    }
}
