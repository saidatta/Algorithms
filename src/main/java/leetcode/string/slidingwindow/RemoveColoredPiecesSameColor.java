package leetcode.string.slidingwindow;

// https://leetcode.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/description/
public class RemoveColoredPiecesSameColor {
    public boolean winnerOfGame(String colors) {
        // Count of consecutive segments of 'A's
        int aliceConsecutiveSegmentCount = 0;
        // Count of consecutive segments of 'B's
        int bobConsecutiveSegmentCount = 0;

        // Iterate through the colors string, excluding the first and last characters
        for (int i = 1; i < colors.length() - 1; i++) {
            // Check for consecutive characters that are the same
            if (colors.charAt(i - 1) == colors.charAt(i) && colors.charAt(i) == colors.charAt(i + 1)) {
                // If the current character is 'A', it's a consecutive segment for Alice
                if (colors.charAt(i) == 'A') {
                    aliceConsecutiveSegmentCount++;
                }
                // Otherwise, it's a consecutive segment for Bob
                else {
                    bobConsecutiveSegmentCount++;
                }
            }
        }

        // Return true if Alice has more consecutive segments than Bob by at least one
        return aliceConsecutiveSegmentCount - bobConsecutiveSegmentCount >= 1;
    }
}
