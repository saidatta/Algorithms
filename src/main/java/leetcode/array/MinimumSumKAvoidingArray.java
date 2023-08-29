package leetcode.array;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/determine-the-minimum-sum-of-a-k-avoiding-array/description/
public class MinimumSumKAvoidingArray {
    public int minimumSum(int n, int k) {
        // Create a set to hold used numbers
        Set<Integer> used = new HashSet<>();
        int sum = 0;
        int num = 1;

        // Loop until we've found n numbers
        while (used.size() < n) {
            // If (k-num) is in the set, that means num+(k-num) = k
            // So, skip to the next number.
            if (used.contains(k - num)) {
                num++;
                continue;
            }

            // Add the current number to the set and to the sum.
            used.add(num);
            sum += num;
            num++;
        }

        return sum;
    }
}
