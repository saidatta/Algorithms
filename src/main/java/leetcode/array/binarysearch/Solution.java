package leetcode.array.binarysearch;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * Computes the minimum error for substituting '!' in a string with '0' or '1'.
     * Each position in the string is evaluated for its impact on the error,
     * using dynamic programming to find the optimal substitution.
     *
     * @param errorString The binary string with '!', which can be replaced by '0' or '1'.
     * @param x The cost associated with the '01' subsequence.
     * @param y The cost associated with the '10' subsequence.
     * @return The minimum error after optimal substitutions.
     */
    private static final int MOD = 1_000_000_007;


    public static int getMinErrors(String errorString, int x, int y) {
        List<Integer> zeroCase = replaceAndConvert(errorString, 0);
        List<Integer> oneCase = replaceAndConvert(errorString, 1);

        return Math.min(errorCounter(zeroCase, x, y), errorCounter(oneCase, x, y));
    }

    private static List<Integer> replaceAndConvert(String errorString, int replacement) {
        List<Integer> result = new ArrayList<>();
        for (char c : errorString.toCharArray()) {
            if (c == '!') {
                result.add(replacement);
            } else {
                result.add(Character.getNumericValue(c));
            }
        }
        return result;
    }

    private static int errorCounter(List<Integer> bits, int x, int y) {
        List<List<Integer>> freqs = freqCounter(bits);
        long xCount = 0;
        long yCount = 0;
        for (int i = 0; i < bits.size(); i++) {
            int bit = bits.get(i);
            if (bit == 0) {
                xCount += freqs.get(i).get(1);
            } else {
                yCount += freqs.get(i).get(0);
            }
        }
        return (int) (((xCount * x) % MOD + (yCount * y) % MOD) % MOD);
    }

    private static List<List<Integer>> freqCounter(List<Integer> bits) {
        int N = bits.size();
        List<List<Integer>> freqs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            freqs.add(new ArrayList<>(List.of(0, 0)));
        }
        for (int i = N - 2; i >= 0; i--) {
            int currentBit = bits.get(i + 1);
            List<Integer> currentFreqs = new ArrayList<>(freqs.get(i + 1));
            if (currentBit == 0) {
                currentFreqs.set(0, currentFreqs.get(0) + 1);
            } else {
                currentFreqs.set(1, currentFreqs.get(1) + 1);
            }
            freqs.set(i, currentFreqs);
        }
        return freqs;
    }


    public static void main(String[] args) {
        String errorString = "01!0";
        int x = 2;
        int y = 3;
        System.out.println(getMinErrors(errorString, x, y));

        String errorString2 = "!!!!!!!";
        int x2 = 23;
        int y2 = 47;
        System.out.println(getMinErrors(errorString2, x2, y2));
    }
}

