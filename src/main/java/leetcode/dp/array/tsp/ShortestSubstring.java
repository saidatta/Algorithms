package leetcode.dp.array.tsp;

import java.util.OptionalInt;
import java.util.stream.IntStream;

// https://leetcode.com/problems/find-the-shortest-superstring/description/
class ShortestSubstring {
    /**
     * Finds the shortest superstring that contains all given words as subsequences.
     *
     * @param words Array of strings for which the shortest superstring is to be found.
     * @return The shortest superstring.
     */
    public String shortestSuperstring(String[] words) {
        int numWords = words.length;
        // overlapDiscount[i][j] stores the number of characters saved when word j follows word i
        int[][] overlapDiscount = new int[numWords][numWords];

        // Calculate the overlap discount for each pair of words
        for (int i = 0; i < numWords; i++) {
            for (int j = 0; j < numWords; j++) {
                if (i != j) {
                    for (int k = 0; k < words[i].length(); k++) {
                        if (words[j].startsWith(words[i].substring(k))) {
                            overlapDiscount[i][j] = words[i].length() - k;
                            break;
                        }
                    }
                }
            }
        }

        // dp[mask][j] represents the maximum overlap with the set of words used (mask) ending with word j
        int[][] dp = new int[1 << numWords][numWords];
        // path[mask][j] stores the previous word in the optimal path that leads to this configuration
        int[][] path = new int[1 << numWords][numWords];

        // Iterate over all combinations of words and calculate maximum overlaps
        for (int mask = 0; mask < (1 << numWords); mask++) {
            for (int j = 0; j < numWords; j++) {
                if ((mask & (1 << j)) > 0) {
                    for (int k = 0; k < numWords; k++) {
                        if ((mask & (1 << k)) == 0) {
                            int nextMask = mask | (1 << k);
                            int newOverlap = dp[mask][j] + overlapDiscount[j][k];
                            if (newOverlap >= dp[nextMask][k]) {
                                dp[nextMask][k] = newOverlap;
                                path[nextMask][k] = j;
                            }
                        }
                    }
                }
            }
        }

        // Reconstruct the path to form the shortest superstring
        int finalMask = (1 << numWords) - 1;
        final int finalMaskConst = finalMask;
        OptionalInt optLastWordInPath = IntStream.range(0, numWords)
                .reduce((a, b) -> dp[finalMaskConst][a] > dp[finalMaskConst][b] ? a : b);

        int lastWordInPath;
        if (optLastWordInPath.isPresent()) {
            lastWordInPath = optLastWordInPath.getAsInt();
        } else {
            // Handle the case where OptionalInt is empty
            // For example, you might set a default value or throw an exception
            lastWordInPath = -1; // or throw new IllegalStateException("No last word in path found");
        }

        String[] superstringParts = new String[numWords];
        int idx = numWords;

        // Build the superstring from the path by considering overlap
        while (finalMask > 0) {
            int prev = path[finalMask][lastWordInPath];
            superstringParts[--idx] = words[lastWordInPath].substring(
                    (finalMask & (finalMask - 1)) == 0 ? 0 : overlapDiscount[prev][lastWordInPath]
            );
            finalMask ^= (1 << lastWordInPath);
            lastWordInPath = prev;
        }

        // Return the constructed superstring
        return String.join("", superstringParts);
    }
}
