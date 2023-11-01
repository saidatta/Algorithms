package leetcode.string;

import java.util.Stack;

/**
 * This class provides a method to decode a string that contains repeated substrings.
 * The repeated substrings are represented in the format "k[substring]", where the "substring" is repeated "k" times.
 * For example:
 * - "3[a]2[bc]" is decoded as "aaabcbc".
 * - "3[a2[c]]" is decoded as "accaccacc".
 * - "2[abc]3[cd]ef" is decoded as "abcabccdcdcdef".
 */
public class DecodeString {

    /**
     * Decodes a string with repeated substrings.
     *
     * @param input the string to be decoded
     * @return the decoded string
     */
    public String decodeString(String input) {
        // The result string that is being built
        StringBuilder currentString = new StringBuilder();
        // A stack to keep track of the count of repetitions for each nested substring
        Stack<Integer> repetitionCountStack = new Stack<>();
        // A stack to keep track of the result strings at each level of nesting
        Stack<String> stringStack = new Stack<>();
        int index = 0;

        // Iterate through the input string
        while (index < input.length()) {
            char currentChar = input.charAt(index);

            if (Character.isDigit(currentChar)) {
                // If the current character is a digit, calculate the whole number (in case of multiple digits)
                int count = 0;
                while (Character.isDigit(input.charAt(index))) {
                    count = 10 * count + input.charAt(index) - '0';
                    index++;
                }
                repetitionCountStack.push(count); // Push the calculated number to the stack
            } else if (currentChar == '[') {
                // When '[' is encountered, push the current result string to the stack and reset the result string
                stringStack.push(currentString.toString());
                currentString = new StringBuilder();
                index++;
            } else if (currentChar == ']') {
                // When ']' is encountered, pop the top element from the string stack and the repetition count stack
                String previousString = stringStack.pop();
                int repeatTimes = repetitionCountStack.pop();

                // Append the repeated current string to the previous string
                currentString = new StringBuilder(previousString).append(currentString.toString().repeat(repeatTimes));
                index++;
            } else {
                // If the current character is not a digit or bracket, append it to the current result string
                currentString.append(currentChar);
                index++;
            }
        }
        return currentString.toString(); // Convert the result StringBuilder to a String and return
    }

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();

        System.out.println(ds.decodeString("3[a]2[bc]")); // Output: "aaabcbc"
        System.out.println(ds.decodeString("3[a2[c]]")); // Output: "accaccacc"
        System.out.println(ds.decodeString("2[abc]3[cd]ef")); // Output: "abcabccdcdcdef"
    }
}
