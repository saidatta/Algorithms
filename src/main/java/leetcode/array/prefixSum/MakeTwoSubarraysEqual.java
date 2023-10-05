package leetcode.array.prefixSum;

// https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays/
public class MakeTwoSubarraysEqual {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] freq = new int[1001];  // As per problem constraints, values are from 1 to 1000

        // Increment the count for each number in target
        for (int num : target) {
            freq[num]++;
        }

        // Decrement the count for each number in arr
        for (int num : arr) {
            freq[num]--;
        }

        // Check if all frequencies are 0
        for (int count : freq) {
            if (count != 0) {
                return false;  // If any frequency is not 0, then arr can't be transformed to target
            }
        }

        return true;
    }
}
