package leetcode.binary;

// https://leetcode.com/problems/reverse-bits/
public class ReverseBits {

//    Create a result variable initialized to 0. This variable will store the reversed bits.
//    Loop from 0 to 31 (both inclusive).
//    For each iteration:
//    Left-shift the result by 1. This makes space for the next bit.
//    If the least significant bit of the input number is 1 (i.e., n & 1 is 1), set the least significant bit of result to 1.
//    Right-shift the number by 1 to move to the next bit.
//    At the end of this process, the result variable will have the reversed bits of the number
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;  // Shift left by 1
            if ((n & 1) == 1) {
                result++;  // If the last bit of n is 1, add 1 to result
            }
            n >>= 1;  // Shift n right by 1
        }
        return result;
    }
}
