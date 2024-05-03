package leetcode.dp.string;

// https://leetcode.com/problems/substring-with-largest-variance/
public class SubstringLargestVariance {
    public int largestVariance(String s) {
        // Count occurrences of each character.
        int[] counter = new int[26];
        for (char ch : s.toCharArray()) {
            counter[ch - 'a']++;
        }

        int globalMax = 0; // Tracks the maximum variance found.

        // Try every possible pair of distinct characters.
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                // Continue if both indices are the same or if any character doesn't appear in s.
                if (i == j || counter[i] == 0 || counter[j] == 0) {
                    continue;
                }

                int majorCount = 0; // Occurrences of the major character in the current substring.
                int minorCount = 0; // Occurrences of the minor character in the current substring.
                int restMinor = counter[j]; // Remaining occurrences of minor in s.

                for (char ch : s.toCharArray()) {
                    if (ch == 'a' + i) {
                        majorCount++; // Increment count for major character.
                    }
                    if (ch == 'a' + j) {
                        minorCount++; // Increment count for minor character.
                        restMinor--;  // Decrement the remaining count of minor character.
                    }

                    // Update the global maximum variance if valid.
                    if (minorCount > 0) {
                        globalMax = Math.max(globalMax, majorCount - minorCount);
                    }

                    // Reset counts if the current window is invalid and there are more minor characters to consider.
                    if (majorCount < minorCount && restMinor > 0) {
                        majorCount = 0;
                        minorCount = 0;
                    }
                }
            }
        }

        return globalMax;
    }

    public static void main(String[] args) {
        SubstringLargestVariance solver = new SubstringLargestVariance();
        System.out.println(solver.largestVariance("aababbb"));  // Expected Output: 3
        System.out.println(solver.largestVariance("abcde"));    // Expected Output: 0
    }

}

