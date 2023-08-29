package leetcode.array.counting;

import java.util.List;

// https://leetcode.com/problems/minimum-operations-to-form-subsequence-with-target-sum/
public class MinOpsFormSubsequenceTargetSum {
    private static final int MAX_BIT_SIZE = 32;

    // o(n)
    public int minOperations(List<Integer> nums, int target) {
        int[] bitCounts = new int[MAX_BIT_SIZE];
        calculateBitCounts(nums, bitCounts);

        int minOperations = 0;

        for (int i = 0; i < MAX_BIT_SIZE - 1; i++) {
            if (isBitSet(target, i)) {
                if (bitCounts[i] == 0) {
                    int nextSetBitIndex = findNextSetBitIndex(bitCounts, i + 1);
                    if (nextSetBitIndex == -1) {
                        return -1; // Not possible
                    }
                    distributeBits(bitCounts, nextSetBitIndex, i);
                    minOperations += (nextSetBitIndex - i);
                }
                bitCounts[i]--;
            }
            bitCounts[i + 1] += bitCounts[i] / 2;
        }

        return minOperations;
    }

    private void calculateBitCounts(List<Integer> nums, int[] bitCounts) {
        for (int num : nums) {
            bitCounts[getMostSignificantBitIndex(num)]++;
        }
    }

    private boolean isBitSet(int number, int index) {
        return ((number >> index) & 1) == 1;
    }

    private int getMostSignificantBitIndex(int number) {
        int index = -1;
        while (number != 0) {
            index++;
            number >>= 1;
        }
        return index;
    }

    private int findNextSetBitIndex(int[] bitCounts, int start) {
        for (int i = start; i < MAX_BIT_SIZE; i++) {
            if (bitCounts[i] > 0) {
                return i;
            }
        }
        return -1; // No bit set found
    }

    private void distributeBits(int[] bitCounts, int from, int to) {
        for (int i = from; i > to; i--) {
            bitCounts[i]--;
            bitCounts[i - 1] += 2;
        }
    }

    public static void main(String [] args) {
//        System.out.println(minOperations())
    }
}
