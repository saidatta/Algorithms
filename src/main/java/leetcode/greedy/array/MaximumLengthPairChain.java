package leetcode.greedy.array;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/maximum-length-of-pair-chain/
public class MaximumLengthPairChain {
    public static void main(String[] args) {
        int[][] pairs1 = {{1,2},{2,3},{3,4}};
        System.out.println(findLongestChain(pairs1));  // Output: 2

        int[][] pairs2 = {{1,2},{7,8},{4,5}};
        System.out.println(findLongestChain(pairs2));  // Output: 3
    }

//    This problem can be approached with a greedy algorithm. If we sort the pairs by their second (right) element, then
//    we can iterate through the pairs and pick those that can be added to the chain. This greedy approach ensures that
//    we have a better chance of fitting more pairs into the chain.
//
//    Here's how we can solve the problem:
//    Sort the pairs by their second (right) element.
//    Initialize a variable currEnd with the smallest possible value and a variable count to store the number of pairs
//    in the chain.
//
//    Iterate through each pair:
//    If the left element of the current pair is greater than currEnd, then this pair can be added to the chain.
//    Increment the count and update currEnd to be the right element of the current pair.
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int currEnd = Integer.MIN_VALUE;
        int count = 0;

        for (int[] pair : pairs) {
            if (pair[0] > currEnd) {
                currEnd = pair[1];
                count++;
            }
        }

        return count;
    }
}
