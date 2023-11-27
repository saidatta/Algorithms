package leetcode.trie;

import java.util.*;

// https://leetcode.com/problems/lexicographical-numbers/description/
public class LexicographicalTrie {
    static class TrieNode {
        TrieNode[] children = new TrieNode[10];
        boolean isEndOfNumber;
    }

    TrieNode root = new TrieNode();

    // Build the trie with all numbers from 1 to n
    public void buildTrie(int n) {
        for (int i = 1; i <= n; i++) {
            insertNumber(i);
        }
    }

    private void insertNumber(int number) {
        TrieNode node = root;
        for (char digit : String.valueOf(number).toCharArray()) {
            int index = digit - '0';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfNumber = true;
    }

    // Perform DFS to collect numbers in lexicographical order
    public List<Integer> getLexicographicalNumbers() {
        List<Integer> result = new ArrayList<>();
        dfs(root, "", result);
        return result;
    }

    private void dfs(TrieNode node, String currentNumber, List<Integer> result) {
        if (node.isEndOfNumber) {
            result.add(Integer.parseInt(currentNumber));
        }
        for (int i = 0; i < node.children.length; i++) {
            if (node.children[i] != null) {
                dfs(node.children[i], currentNumber + i, result);
            }
        }
    }

    public static void main(String[] args) {
        LexicographicalTrie trie = new LexicographicalTrie();
        trie.buildTrie(13);
        System.out.println(trie.getLexicographicalNumbers());  // Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
    }
}
