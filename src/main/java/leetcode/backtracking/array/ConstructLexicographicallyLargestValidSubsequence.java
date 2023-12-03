package leetcode.backtracking.array;

// https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
public class ConstructLexicographicallyLargestValidSubsequence {
    public int[] constructDistancedSequence(int n) {
        int[] result = new int[2 * n - 1];
        boolean[] visited = new boolean[n + 1];
        backtrack(result, visited, n, 0);
        return result;
    }

    private boolean backtrack(int[] result, boolean[] visited, int n, int position) {
        // Base case: if all positions are filled, return true
        if (position == result.length) {
            return true;
        }

        // If the current position is already filled, move to the next position
        if (result[position] != 0) {
            return backtrack(result, visited, n, position + 1);
        }

        // Try placing each number from n down to 1
        for (int i = n; i >= 1; i--) {
            // Special case for 1 as it only needs to be placed once
            if (i == 1) {
                if (!visited[i]) {
                    result[position] = i;
                    visited[i] = true;
                    if (backtrack(result, visited, n, position + 1)) {
                        return true;
                    }
                    // Backtrack
                    result[position] = 0;
                    visited[i] = false;
                }
            } else {
                // Check if i can be placed at position and position + i
                if (!visited[i] && position + i < result.length && result[position + i] == 0) {
                    result[position] = result[position + i] = i;
                    visited[i] = true;
                    if (backtrack(result, visited, n, position + 1)) {
                        return true;
                    }
                    // Backtrack
                    result[position] = result[position + i] = 0;
                    visited[i] = false;
                }
            }
        }
        return false;
    }
}
