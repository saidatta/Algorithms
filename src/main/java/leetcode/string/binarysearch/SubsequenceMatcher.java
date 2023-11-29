package leetcode.string.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/number-of-matching-subsequences
public class SubsequenceMatcher {
    public int numMatchingSubseq(String s, String[] words) {
        // Preprocess the string s to store the indices of each character
        Map<Character, List<Integer>> charIndices = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charIndices.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }

        int count = 0;
        for (String word : words) {
            if (isSubsequence(word, charIndices)) {
                count++;
            }
        }
        return count;
    }

    private boolean isSubsequence(String word, Map<Character, List<Integer>> charIndices) {
        int prevIndex = -1;  // The index of the previous character in 's'
        for (char c : word.toCharArray()) {
            if (!charIndices.containsKey(c)) {
                return false;  // Character not found in 's'
            }
            List<Integer> indices = charIndices.get(c);
            int foundIndex = findNextIndex(indices, prevIndex);
            if (foundIndex == -1) {
                return false;  // No valid position for the current character
            }
            prevIndex = foundIndex;
        }
        return true;
    }

    // Binary search to find the smallest index greater than prevIndex
    private int findNextIndex(List<Integer> indices, int prevIndex) {
        int low = 0, high = indices.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (indices.get(mid) <= prevIndex) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (low < indices.size()) ? indices.get(low) : -1;
    }

    public static void main(String[] args) {
        SubsequenceMatcher matcher = new SubsequenceMatcher();
        String s = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println(matcher.numMatchingSubseq(s, words));  // Output: 3
    }
}
