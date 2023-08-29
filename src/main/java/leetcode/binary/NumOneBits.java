package leetcode.binary;

// https://leetcode.com/problems/number-of-1-bits/description/
public class NumOneBits {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }
}
