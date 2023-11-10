package leetcode.array.slidingWindow;

// https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
public class NumberOfSubstringsWithAllChars {
    public static int numberOfSubstrings(String s) {
        int[] count = {0, 0, 0}; // count[0] for 'a', count[1] for 'b', count[2] for 'c'
        int result = 0;
        int left = 0; // Left end of the sliding window

        // Iterate through the string to expand the window
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'a']++; // Increase the count for the current character

            // Check if the current window contains all three characters
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                result += s.length() - right; // Add the number of substrings starting from the left
                count[s.charAt(left) - 'a']--; // Decrease the count for the character on the left
                left++; // Shrink the window from the left
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Number of substrings (Example 1): " + numberOfSubstrings("abcabc")); // Output: 10
        System.out.println("Number of substrings (Example 2): " + numberOfSubstrings("aaacb"));  // Output: 3
        System.out.println("Number of substrings (Example 3): " + numberOfSubstrings("abc"));    // Output: 1
    }
}

