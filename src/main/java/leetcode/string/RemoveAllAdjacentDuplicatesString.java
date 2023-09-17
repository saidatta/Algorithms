package leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

// The problem aims to remove all consecutive and identical characters
// that appear k times in a row from a string.
public class RemoveAllAdjacentDuplicatesString {
    public String removeDuplicates(String s, int k) {
        // Base case: If string length is less than or equal to k, return original string.
        if (s.length() <= k) {
            return s;
        }

        Deque<CharData> charQueue = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // If the queue is empty or the last character in the queue is not the current character,
            // add a new CharData object to the queue.
            if (charQueue.isEmpty() || charQueue.peekLast().character != currentChar) {
                charQueue.addLast(new CharData(currentChar));
            } else {
                // Otherwise, increment the count of the last character.
                charQueue.peekLast().count++;
            }

            // If the last character's count is k, remove it from the queue.
            if (charQueue.peekLast().count == k) {
                charQueue.pollLast();
            }
        }

        // Convert the queue into the resulting string.
        StringBuilder result = new StringBuilder();
        while (!charQueue.isEmpty()) {
            CharData charData = charQueue.pollFirst();
            result.append(String.valueOf(charData.character).repeat(Math.max(0, charData.count)));
        }
        return result.toString();
    }


    // Each Data object represents a character 'c' and its consecutive occurrence 'count'.
    private static class CharData {
        char character;
        int count;

        CharData(char character) {
            this.character = character;
            this.count = 1;  // Initialized to 1 since a character will appear at least once.
        }
    }

    public static void main(String[] args) {
        RemoveAllAdjacentDuplicatesString solution = new RemoveAllAdjacentDuplicatesString();

        String input1 = "deeedbbcccbdaa";
        int k1 = 3;
        System.out.println("Input: " + input1 + ", k: " + k1 + " => " + solution.removeDuplicates(input1, k1));

        String input2 = "aabbcc";
        int k2 = 2;
        System.out.println("Input: " + input2 + ", k: " + k2 + " => " + solution.removeDuplicates(input2, k2));

        // Add more test cases if needed.
    }
}
