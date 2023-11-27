package leetcode.string.twopointers;

// https://leetcode.com/problems/reverse-string-iii/
public class ReverseWordsStringIII {
    /**
     * Reverses individual words in a given string.
     *
     * @param s The input string
     * @return The string with each word reversed but the order of words remains the same
     */
    public String reverseWords(String s) {
        // The index of the last space found, initialized to -1 to handle the first word
        int lastSpaceIndex = -1;

        // Convert the string to a character array for in-place manipulation
        char[] characters = s.toCharArray();

        // Iterate over each character in the string
        for (int currentIndex = 0; currentIndex <= s.length(); currentIndex++) {
            // Reverse a word when a space is found or when we're at the end of the string
            if (currentIndex == s.length() || characters[currentIndex] == ' ') {
                // Calculate start and end indices of the word to reverse
                int startWordIndex = lastSpaceIndex + 1;
                int endWordIndex = currentIndex - 1;

                // Reverse the word in-place using two pointers approach
                while (startWordIndex < endWordIndex) {
                    // Swap characters to reverse the word
                    char tempChar = characters[startWordIndex];
                    characters[startWordIndex] = characters[endWordIndex];
                    characters[endWordIndex] = tempChar;

                    // Move the pointers towards each other
                    startWordIndex++;
                    endWordIndex--;
                }

                // Update the position of the last found space
                lastSpaceIndex = currentIndex;
            }
        }

        // Convert the character array back to a string and return
        return new String(characters);
    }
}
