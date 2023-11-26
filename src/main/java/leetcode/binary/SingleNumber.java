package leetcode.binary;

import java.util.stream.IntStream;

/**
 * Given an array of integers where every integer occurs three times except for one integer,
 * which only occurs once,
 * find and return the non-duplicated integer.
 *
 * For example, given [3, 3, 1,6, 3, 6, 6], return 1. Given [13, 19, 13, 13], return 19.
 * Do this in O(N) time and O(1) space.
 */
public class SingleNumber {
    public int singleNumber(int... nums) {
        int result = 0;
        for (int num : nums) {
            // xor of equal numbers is 0.
            // xor with 0 with another non-zero number is that non-zero number.
            result ^= num;
        }
        return result;
    }

    // this looks slower.
    public int singleNumberFunctional(int... nums) {
        return IntStream.of(nums).reduce((a, b) -> a ^ b).orElse(0);
    }

    public static void main(String [] args) {
        SingleNumber singleNumber = new SingleNumber();
        System.out.println(singleNumber.singleNumber(13,19,13,13));
    }
}
