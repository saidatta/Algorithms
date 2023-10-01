package leetcode.string.combinatorics;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/permutation-sequence/
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] factorial = new int[n+1];
        StringBuilder sb = new StringBuilder();

        // Create a list of numbers to get permutations.
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        // Fill factorial array. factorial[i] contains i!
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i-1] * i;
        }

        // To get the index
        k--;

        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n-i];
            sb.append(numbers.get(index));
            numbers.remove(index);
            k -= index * factorial[n-i];
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        PermutationSequence solver = new PermutationSequence();

        System.out.println(solver.getPermutation(3, 3)); // "213"
        System.out.println(solver.getPermutation(4, 9)); // "2314"
        System.out.println(solver.getPermutation(3, 1)); // "123"
    }
}
