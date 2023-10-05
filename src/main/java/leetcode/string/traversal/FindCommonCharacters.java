package leetcode.string.traversal;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/find-common-characters/description/
public class FindCommonCharacters {
    public List<String> commonChars(String[] words) {
        // List to store the result
        List<String> result = new ArrayList<>();

        // Array to store the minimum frequency of each character across all words
        int[] minFrequency = new int[26];
        for (int i = 0; i < 26; i++) {
            minFrequency[i] = Integer.MAX_VALUE;
        }

        for (String word : words) {
            int[] charCount = new int[26];

            // Count frequency of characters in current word
            for (char ch : word.toCharArray()) {
                charCount[ch - 'a']++;
            }

            // Update the minimum frequency
            for (int i = 0; i < 26; i++) {
                minFrequency[i] = Math.min(minFrequency[i], charCount[i]);
            }
        }

        // Build the result list
        for (int i = 0; i < 26; i++) {
            while (minFrequency[i] > 0) {
                result.add(String.valueOf((char) (i + 'a')));
                minFrequency[i]--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FindCommonCharacters sol = new FindCommonCharacters();
        System.out.println(sol.commonChars(new String[]{"bella", "label", "roller"}));  // Output: [e, l, l]
        System.out.println(sol.commonChars(new String[]{"cool", "lock", "cook"}));  // Output: [c, o]
    }
}
