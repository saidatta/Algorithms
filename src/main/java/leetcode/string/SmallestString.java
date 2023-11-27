package leetcode.string;

import java.util.Arrays;

public class SmallestString {
    public String getSmallestString(int n, int k) {
        char[] result = new char[n];
        Arrays.fill(result, 'a'); // Step 1: Initialize with 'a's
        k -= n; // Adjust k to represent the additional sum needed

        int i = n - 1;
        while (k > 0) {
            int add = Math.min(k, 25); // Step 3: Calculate the value to add (max 25 to go from 'a' to 'z')
            result[i] = (char) ('a' + add); // Replace the character at position i
            k -= add;
            i--; // Move to the next character from the end
        }

        return new String(result);
    }
}
