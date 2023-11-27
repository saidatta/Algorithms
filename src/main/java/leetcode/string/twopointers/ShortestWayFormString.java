package leetcode.string.twopointers;

import java.util.Arrays;

// https://leetcode.com/problems/shortest-way-to-form-string/
public class ShortestWayFormString {
    /**
     * Calculates the minimum number of subsequences of the source string required
     * to form the target string.
     *
     * @param source The source string.
     * @param target The target string.
     * @return The minimum number of subsequences required, or -1 if impossible.
     */
    public int shortestWay(String source, String target) {
        final int sourceLength = source.length();
        final int targetLength = target.length();

        // nextOccurrence[i][char] holds the next occurrence index of 'char' in source starting from index i
        int[][] nextOccurrence = new int[sourceLength][26];

        // Initialize the last row with -1 (indicating no further occurrences)
        Arrays.fill(nextOccurrence[sourceLength - 1], -1);

        // Set the next occurrence for the last character in the source
        nextOccurrence[sourceLength - 1][source.charAt(sourceLength - 1) - 'a'] = sourceLength - 1;

        // Fill the nextOccurrence matrix in reverse order
        for (int i = sourceLength - 2; i >= 0; i--) {
            nextOccurrence[i] = nextOccurrence[i + 1].clone();
            nextOccurrence[i][source.charAt(i) - 'a'] = i;
        }

        int subsequencesCount = 0;
        int targetIndex = 0;

        while (targetIndex < targetLength) {
            int sourceIndex = 0;
            while (sourceIndex < sourceLength && targetIndex < targetLength) {
                int charIndexInSource = nextOccurrence[sourceIndex][target.charAt(targetIndex) - 'a'];
                if (charIndexInSource == -1) { // No next occurrence found, break to start a new subsequence
                    break;
                }
                sourceIndex = charIndexInSource + 1;
                targetIndex++;
            }
            if (sourceIndex == 0) { // Couldn't match any character in this iteration
                return -1; // Target cannot be formed
            }
            subsequencesCount++;
        }
        return subsequencesCount;
    }

    // Main method for testing
    public static void main(String[] args) {
        ShortestWayFormString solution = new ShortestWayFormString();
        System.out.println(solution.shortestWay("abc", "abcbc")); // Output: 2
        System.out.println(solution.shortestWay("abc", "acdbc")); // Output: -1
        System.out.println(solution.shortestWay("xyz", "xzyxz")); // Output: 3
    }
}
