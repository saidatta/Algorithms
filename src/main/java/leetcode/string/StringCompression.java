package leetcode.string;

// Class to perform in-place string compression for a sequence of repeated characters.
// For example, "aaabbcc" would be compressed to "a3b2c2".
// https://leetcode.com/problems/string-compression/description/
class StringCompression {

    // Compresses the character array in-place and returns the new length of the compressed array.
    public int compress(char[] chars) {
        // Index to traverse the original array
        int readIndex = 0;

        // Index to write the compressed characters
        int writeIndex = 0;

        while (readIndex < chars.length) {
            // Start a new group of characters
            // Initialize the length of the current character group
            int groupLength = 1;

            // Count the number of repeated characters
            while (readIndex + groupLength < chars.length && chars[readIndex + groupLength] == chars[readIndex]) {
                groupLength++;
            }

            // Write the character
            chars[writeIndex++] = chars[readIndex];

            // If there's more than one character in the group, write the count as well
            if (groupLength > 1) {
                for (char c : Integer.toString(groupLength).toCharArray()) {
                    chars[writeIndex++] = c;
                }
            }

            // Move to the next group of characters
            readIndex += groupLength;
        }

        // Return the new length of the compressed array
        return writeIndex;
    }

    // Main method to test the string compression functionality
    public static void main(String[] args) {
        StringCompression compressor = new StringCompression();

        // Example input: an array of characters
        char[] input = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};

        // Perform compression
        int compressedLength = compressor.compress(input);

        // Print the compressed string and its length
        System.out.print("Compressed String: ");
        for (int i = 0; i < compressedLength; i++) {
            System.out.print(input[i]);
        }
        System.out.println("\nCompressed Length: " + compressedLength);
    }
}
