package leetcode.array.string;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/description/
public class NumPairsConcatEqualTarget {

    /**
     * Counts the number of pairs in the array whose concatenation equals the target string.
     *
     * @param nums Array of string digits.
     * @param target The target string to match after concatenation.
     * @return The count of pairs whose concatenation equals the target string.
     */
    public int numOfPairs(String[] nums, String target) {
        int n = nums.length; // Length of the nums array
        int ans = 0; // Initialize the answer to 0

        // Iterate over each pair of elements in the array
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Ensure that we are not using the same element twice
                if (i != j) {
                    // Concatenate the strings at indices i and j
                    String concatenated = nums[i] + nums[j];

                    // Check if the concatenated string equals the target
                    if (concatenated.equals(target)) {
                        ans++; // Increment the count if it matches the target
                    }
                }
            }
        }
        return ans; // Return the final count
    }

    class Solution {

        // TrieNode class represents each node in the Trie.
        class TrieNode {
            TrieNode[] nexts; // Children nodes for each digit (0-9)
            List<Integer> endIdx; // List of indices where words end

            TrieNode() {
                nexts = new TrieNode[10]; // Initialize child nodes
                endIdx = new ArrayList<>(); // Initialize list for end indices
            }

            // Check if the current node is an end node (i.e., a complete word ends here)
            boolean isEnd() {
                return !endIdx.isEmpty();
            }
        }

        TrieNode root; // Root of the Trie

        // Insert a word into the Trie and record its index
        void insert(String word, int idx) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int num = c - '0';
                if (node.nexts[num] == null) {
                    node.nexts[num] = new TrieNode(); // Create new node if not exists
                }
                node = node.nexts[num];
            }
            node.endIdx.add(idx); // Add index to the end node
        }

        // Match a string in the Trie and return list of indices where the string ends
        List<Integer> matchString(String s) {
            TrieNode node = root;
            for (char c : s.toCharArray()) {
                int digit = c - '0';
                if (node.nexts[digit] == null) {
                    return new ArrayList<>(); // Return empty list if no match
                }
                node = node.nexts[digit];
            }
            return node.endIdx; // Return indices list where string ends
        }

        public int numOfPairs(String[] nums, String target) {
            root = new TrieNode();
            for (int i = 0; i < nums.length; ++i) {
                insert(nums[i], i); // Insert each string in nums into the Trie
            }

            // Prepare a list of suffix matches from target in the Trie
            List<List<Integer>> suffixMatches = new ArrayList<>();
            suffixMatches.add(new ArrayList<>());
            for (int i = 1; i < target.length(); ++i) {
                String sub = target.substring(i);
                List<Integer> matchList = matchString(sub);
                suffixMatches.add(matchList);
            }

            int ans = 0;
            TrieNode node = root;
            for (int i = 0; i < target.length() - 1; ++i) {
                int digit = target.charAt(i) - '0';
                if (node.nexts[digit] == null) {
                    break; // Break if no further match in Trie
                }
                node = node.nexts[digit];

                // If current node is an end node, calculate combinations with suffixes
                if (node.isEnd()) {
                    // Special handling if the target can be split into two equal parts
                    if ((i + 1) * 2 == target.length()) {
                        List<Integer> suffixList = suffixMatches.get(i + 1);
                        for (int prefixIndex : node.endIdx) {
                            ans += suffixList.contains(prefixIndex) ? suffixList.size() - 1 : suffixList.size();
                        }
                    } else {
                        ans += node.endIdx.size() * suffixMatches.get(i + 1).size();
                    }
                }
            }
            return ans; // Return total count of valid pairs
        }
    }


    public static void main(String[] args) {
        NumPairsConcatEqualTarget solution = new NumPairsConcatEqualTarget();

        // Test case 1
        String[] nums1 = {"777", "7", "77", "77"};
        String target1 = "7777";
        System.out.println("Test Case 1: " + solution.numOfPairs(nums1, target1)); // Expected output: 4

        // Test case 2
        String[] nums2 = {"123", "4", "12", "34"};
        String target2 = "1234";
        System.out.println("Test Case 2: " + solution.numOfPairs(nums2, target2)); // Expected output: 2

        // Test case 3
        String[] nums3 = {"1", "1", "1"};
        String target3 = "11";
        System.out.println("Test Case 3: " + solution.numOfPairs(nums3, target3)); // Expected output: 6
    }
}
