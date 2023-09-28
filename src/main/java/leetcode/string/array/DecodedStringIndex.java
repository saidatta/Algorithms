package leetcode.string.array;

// https://leetcode.com/problems/decoded-string-at-index/
public class DecodedStringIndex {
    public String decodeAtIndex(String input, int k) {
        long decodedLength = computeDecodedLength(input);
        return findKthChar(input, k, decodedLength);
    }

    // Compute the length of the decoded string
    private long computeDecodedLength(String input) {
        long length = 0;
        for (int i = 0; i < input.length(); ++i) {
            char currentChar = input.charAt(i);
            if (Character.isDigit(currentChar)) {
                length *= currentChar - '0'; // Multiply length for numbers
            } else {
                length++;
            }
        }
        return length;
    }

    // Find the kth character in the decoded string
    private String findKthChar(String input, int k, long decodedLength) {
        for (int i = input.length() - 1; i >= 0; --i) {
            char currentChar = input.charAt(i);
            k %= decodedLength;
            if (k == 0 && Character.isLetter(currentChar)) {
                return Character.toString(currentChar);
            }
            if (Character.isDigit(currentChar)) {
                // Reduce the length based on the digit
                decodedLength /= currentChar - '0';
            } else {
                decodedLength--;
            }
        }
        // Meaningful exception message
        throw new IllegalArgumentException("Invalid input or k");
    }

    public static void main(String[] args) {
        DecodedStringIndex solution = new DecodedStringIndex();
        System.out.println(solution.decodeAtIndex("leet2code3", 10)); // Expected: o
        System.out.println(solution.decodeAtIndex("ha22", 5));        // Expected: h
        System.out.println(solution.decodeAtIndex("a2345678999999999999999", 1)); // Expected: a
    }
}
