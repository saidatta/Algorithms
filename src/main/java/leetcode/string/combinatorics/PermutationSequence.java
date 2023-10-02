package leetcode.string.combinatorics;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/permutation-sequence/
public class PermutationSequence {
    /**
     * Returns the kth permutation sequence of numbers 1 through n.
     *
     * @param n the number of digits in the sequence.
     * @param k the kth permutation sequence to return.
     * @return the kth permutation sequence as a string.
     */
    public String getPermutation(int n, int k) {
        // List to hold numbers 1 through n.
        List<Integer> availableNumbers = new ArrayList<>();
        // Array to store factorial values for efficient computation.
        int[] factorialValues = new int[n + 1];
        StringBuilder resultPermutation = new StringBuilder();

        // Initialize the list with numbers 1 to n.
        for (int i = 1; i <= n; i++) {
            availableNumbers.add(i);
        }

        // Pre-calculate factorial values.
        factorialValues[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorialValues[i] = factorialValues[i - 1] * i;
        }

        // Adjust k to be an index in the sequence.
        k--;

        // Construct the kth permutation using factorial-based indexing.
        for (int i = 1; i <= n; i++) {
            // Determine the index of the current digit.
            int index = k / factorialValues[n - i];
            resultPermutation.append(availableNumbers.get(index));
            // Remove the used number from the available numbers list.
            availableNumbers.remove(index);
            // Adjust k for the next iteration.
            k -= index * factorialValues[n - i];
        }

        return resultPermutation.toString();
    }

    public static void main(String[] args) {
        PermutationSequence solver = new PermutationSequence();

        System.out.println(solver.getPermutation(3, 3)); // "213"
        System.out.println(solver.getPermutation(4, 9)); // "2314"
        System.out.println(solver.getPermutation(3, 1)); // "123"
    }
}
