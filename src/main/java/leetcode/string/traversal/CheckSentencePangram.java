package leetcode.string.traversal;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/check-if-the-sentence-is-pangram/
public class CheckSentencePangram {
    public boolean checkIfPangram(String sentence) {
        // Create a set to hold unique characters.
        Set<Character> uniqueChars = new HashSet<>();

        // Iterate through each character in the sentence.
        for (char c : sentence.toCharArray()) {
            uniqueChars.add(c);
        }

        // Return true if the set contains all 26 English letters, otherwise false.
        return uniqueChars.size() == 26;
    }

    public static void main(String[] args) {
        CheckSentencePangram sol = new CheckSentencePangram();
        System.out.println(sol.checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));  // Expected output: true
        System.out.println(sol.checkIfPangram("leetcode"));  // Expected output: false
    }
}
