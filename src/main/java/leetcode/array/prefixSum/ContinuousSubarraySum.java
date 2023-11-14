package leetcode.array.prefixSum;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/continuous-subarray-sum/
//https://www.youtube.com/watch?v=OKcrLfR-8mE
public class ContinuousSubarraySum {
//    The prefix sum for an index i in an array is the sum of all numbers up to and including that index.
//
//    The modulo property we're referring to here is:
//    If (a - b) % k = 0, then a % k = b % k.
//
//            Now, why does this matter?
//
//    Imagine we're computing the prefix sum for every index in our nums array. Let's call these sums sum[0], sum[1],
//    ..., sum[i], ..., sum[j], ... for indices 0, 1, ..., i, ..., j, ... respectively.
//
//            Now, if the modulo of sum[i] and sum[j] with respect to k is the same, then:
//
//    bash
//    Copy code
//            (sum[j] - sum[i]) % k = 0
//    which implies that the sum of numbers between index i+1 and j is a multiple of k.
//
//            Why?
//    Because the sum of numbers between index i+1 and j is essentially sum[j] - sum[i]. If this number modulo k is 0,
//    then it's a multiple of k.
//
//    Now, let's see a small example for clarity:
//
//    nums = [2, 3, 1, 2, 2, 3, 3], and k = 5
//
//    sum[0] = 2 % 5 = 2
//    sum[1] = (2+3) % 5 = 0
//    sum[2] = (2+3+1) % 5 = 1
//    sum[3] = (2+3+1+2) % 5 = 3
//    sum[4] = (2+3+1+2+2) % 5 = 0
//            ...
//    Now, notice that sum[1] and sum[4] both have a modulo of 0 with respect to 5. This means the sum of numbers
//    between indices 2 and 4 (1-indexed) or indices 1 and 4 (0-indexed) is a multiple of 5.
//
//    In this case, that subarray is [1, 2, 2], which indeed sums to 5, a multiple of k.
//
//    This is how the prefix sum and the modulo property together help determine if a subarray exists whose sum is a
//    multiple of k.
    public boolean checkSubarraySum(int[] nums, int k) {
        // initialize the hash map with index 0 for sum 0
        Map<Integer, Integer> hashMap = new HashMap<>(Map.of(0, 0));
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // if the remainder sum % k occurs for the first time
            if (!hashMap.containsKey(sum % k)) {
                hashMap.put(sum % k, i + 1);
            } else if (hashMap.get(sum % k) < i) {
                // if the subarray size is at least two
                return true;
            }
        }
        return false;
    }
}
