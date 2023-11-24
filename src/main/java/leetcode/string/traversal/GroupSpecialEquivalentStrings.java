package leetcode.string.traversal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/groups-of-special-equivalent-strings/
class GroupSpecialEquivalentStrings {
    public int numSpecialEquivGroups(String[] words) {
        Set<String> uniqueGroups = new HashSet<>();

        for (String word : words) {
            String encoded = encode(word);
            uniqueGroups.add(encoded);
        }

        return uniqueGroups.size();
    }

    private String encode(String word) {
        StringBuilder even = new StringBuilder();
        StringBuilder odd = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (i % 2 == 0) {
                even.append(word.charAt(i));
            } else {
                odd.append(word.charAt(i));
            }
        }

        char[] evenChars = even.toString().toCharArray();
        char[] oddChars = odd.toString().toCharArray();

        // Sort characters to normalize the representation
        Arrays.sort(evenChars);
        Arrays.sort(oddChars);

        return new String(evenChars) + new String(oddChars);
    }

    public static void main(String[] args) {
        GroupSpecialEquivalentStrings solution = new GroupSpecialEquivalentStrings();

        // Example input
        String[] words = {"abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx"};

        // Calculate the number of special-equivalent groups
        int result = solution.numSpecialEquivGroups(words);

        // Print the result
        System.out.println("Number of Special-Equivalent Groups: " + result);
    }
}
