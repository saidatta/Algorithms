package leetcode.string.traversal;

// https://leetcode.com/problems/unique-length-3-palindromic-subsequences/description/
// https://www.youtube.com/watch?v=3THUt0vAFLU&list=PLot-Xpze53lfOdF3KwpMSFEyfE77zIwiP&index=91
import java.util.HashSet;
import java.util.Set;

public class UniqueLength3PalindromicSubsequences {

    public static int countPalindromicSubsequence(String s) {
        // 2D array to store the first and last occurrence of each letter
        // Initialized to -1 as the default value
        int[][] firstLast = new int[26][2];
        for (int i = 0; i < 26; i++) {
            firstLast[i][0] = -1;
            firstLast[i][1] = -1;
        }

        // Iterate through the string to populate the first and last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            // If first occurrence is not set, set it
            if (firstLast[c][0] == -1) {
                firstLast[c][0] = i;
            }
            firstLast[c][1] = i; // Update last occurrence every time
        }

        // Set to store unique palindromic subsequences
        Set<String> palindromes = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            // If a character has distinct first and last occurrences
            if (firstLast[i][0] != -1 && firstLast[i][0] != firstLast[i][1]) {
                // Check characters in between the first and last occurrence
                for (int j = firstLast[i][0] + 1; j < firstLast[i][1]; j++) {
                    // Create a palindromic subsequence and add it to the set
                    palindromes.add(String.valueOf((char) (i + 'a')) + s.charAt(j) + (char) (i + 'a'));
                }
            }
        }

        // Return the size of the set, which represents the number of unique palindromic subsequences
        return palindromes.size();
    }

    public static void main(String[] args) {
        String s1 = "aabca";
        System.out.println(countPalindromicSubsequence(s1));  // Output: 3

        String s2 = "adc";
        System.out.println(countPalindromicSubsequence(s2));  // Output: 0

        String s3 = "bbcbaba";
        System.out.println(countPalindromicSubsequence(s3));  // Output: 4
    }
}

//    We can follow a strategy where we first find the first and last occurrence of each character in the string.
//    Then, for each character, we check the characters in between its first and last occurrence because these
//    characters can be used to form a palindrome with the current character.

//    Initialize a 2D array firstLast of size 26x2 (for 26 letters) to store the first and last occurrence of each
//    letter. Set all values to -1 initially.
//        Iterate through the string from left to right. For each character c, if firstLast[c - 'a'][0] is -1, set it
//        to the current index. Always update firstLast[c - 'a'][1] to the current index.
//        Create a set to store unique palindromic subsequences.
//        Iterate through the string again. For each character c, look at the subarray in firstLast for c, let's call
//        the first and last indices as first and last. If first is not equal to last, iterate from first + 1 to
//        last - 1, and for each character d in that range, add the string cdc to the set.
//        The size of the set will be your answer.
//        Time Complexity: O(26 * n) = O(n), where n is the length of the string.
//        Space Complexity: O(26 * 2 + 26 * 3) = O(1) (constant space)