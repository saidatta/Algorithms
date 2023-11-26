package leetcode.binary;

import java.util.Arrays;

// https://leetcode.com/problems/single-number-iii/description/
public class SingleNumberIII {
    public int[] singleNumber(int... nums) {
        // Step 1: Initial XOR of all numbers
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        // Step 2: Find rightmost set bit. Since we are doing xor earlier for those numbers, it will be 1. only when its
        // 0 & 1. So, the rightmostbit for below can never be present in both ends.
        int rightmostSetBit = xor & -xor;

        // Array to hold the two unique numbers
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & rightmostSetBit) != 0) {
                // If rightmost set bit is set in num
                result[0] ^= num;
            } else {
                // If rightmost set bit is not set in num
                result[1] ^= num;
            }
        }

        return result;
    }

    public static void main(String [] args) {
        SingleNumberIII singleNumberIII = new SingleNumberIII();
        System.out.println(Arrays.toString(singleNumberIII.singleNumber(1, 2, 1, 3, 2, 6)));
    }
}

//  The XOR of two identical numbers is 0, and the XOR of any number with 0 is the number itself. Since we know that
//  all numbers except two appear twice in the array, we can XOR all the numbers in the array to find the XOR result
//  of the two unique numbers. However, we need an additional step to separate these two unique numbers from each other.
//
//  Here's the detailed approach:

//        Initial XOR: XOR all numbers in the array. The result will be the XOR of the two unique numbers since the XOR of any duplicate numbers will cancel out.
//        Find Rightmost Set Bit: Find the rightmost set bit in the XOR result. This set bit must be set in one of the unique numbers and not set in the other (otherwise, they wouldn't be unique). We can isolate this set bit by performing xor & ~(xor - 1).
//        Separate Two Unique Numbers: Use the set bit to partition the array into two groups - one with this bit set and the other with this bit not set. XORing the numbers in each group separately will give us the two unique numbers.