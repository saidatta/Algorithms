package leetcode.string.twopointers;

// https://leetcode.com/problems/reverse-string-ii/
public class ReverseWordsStringII {
    /**
     * Reverse every k characters for every 2k characters in a string.
     *
     * @param s The input string
     * @param k The number of characters to reverse at a time in every 2k characters.
     * @return The string after reversing k characters for every 2k characters
     */
    public String reverseStr(String s, int k) {
        // Convert the string to a character array for in-place manipulation
        char[] characters = s.toCharArray();

        // Iterate over the string in increments of 2k to reverse every k characters
        for (int startIndex = 0; startIndex < characters.length; startIndex += 2 * k) {
            // Calculate the start and end indices for the section to be reversed
            int left = startIndex;
            // Ensure we don't go beyond the string length
            int right = Math.min(startIndex + k - 1, characters.length - 1);

            // Reverse the k characters in-place using two pointers approach
            while (left < right) {
                // Swap characters to reverse the segment
                char tempChar = characters[left];
                characters[left++] = characters[right];
                characters[right--] = tempChar;
            }
        }

        // Convert the character array back to a string and return
        return new String(characters);
    }
}
