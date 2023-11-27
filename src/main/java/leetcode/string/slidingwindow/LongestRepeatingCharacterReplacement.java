package leetcode.string.slidingwindow;

// https://leetcode.com/problems/longest-repeating-character-replacement/description/
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        // Count the occurrences of each character in the current window
        int[] charCount = new int[26];

        // maxCount tracks the character with the highest frequency in the current window
        int maxCount = 0;

        // maxLength tracks the maximum length of substring we can obtain
        int maxLength = 0;

        // left pointer for our sliding window
        int left = 0;

        // right is the right pointer for our sliding window
        for (int right = 0; right < s.length(); right++) {
            // Increment the count of the current character and update maxCount
            maxCount = Math.max(maxCount, ++charCount[s.charAt(right) - 'A']);

            // If the size of the window (right - left + 1) minus the character with the highest frequency
            // is greater than k, then we can't replace all other characters in the window
            if (right - left + 1 - maxCount > k) {
                // Reduce the count of the leftmost character of the window as we are going to exclude it
                charCount[s.charAt(left) - 'A']--;
                // Move the left pointer of the window
                left++;
            }

            // Update the maxLength after every iteration to keep track of the largest valid window we've seen
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement solution = new LongestRepeatingCharacterReplacement();

        System.out.println(solution.characterReplacement("ABAB", 2));  // Expected output: 4
        System.out.println(solution.characterReplacement("AABABBA", 1));  // Expected output: 4
    }
}
