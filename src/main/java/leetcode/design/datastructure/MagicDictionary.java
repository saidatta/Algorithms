package leetcode.design.datastructure;

// https://leetcode.com/problems/implement-magic-dictionary/

import java.util.*;

class MagicDictionary {

    // Define Node class for Trie
    static class Node {
        Node[] children;
        boolean isWordEnd;

        Node() {
            this.children = new Node[26];
            isWordEnd = false;
        }
    }

    private final Node root;

    public MagicDictionary() {
        root = new Node();
    }

    // Function to insert a word into the trie
    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            insert(word);
        }
    }

    // Helper method for inserting words into the trie
    private void insert(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Node();
            }
            current = current.children[index];
        }
        current.isWordEnd = true;
    }

    // Function to search for a word with one character difference
    public boolean search(String searchWord) {
        return dfs(root, searchWord, 0, 0);
    }

    // Helper method for DFS search with one character replacement allowed
    private boolean dfs(Node current, String word, int index, int modificationCount) {
        if (modificationCount > 1) {
            return false;  // More than one modification not allowed
        }
        if (index == word.length()) {
            return modificationCount == 1 && current.isWordEnd;
        }

        char currentChar = word.charAt(index);
        for (char c = 'a'; c <= 'z'; c++) {
            Node nextNode = current.children[c - 'a'];
            if (nextNode != null) {
                if (dfs(nextNode, word, index + 1, currentChar == c ? modificationCount : modificationCount + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});
        System.out.println(magicDictionary.search("hello")); // Output: false
        System.out.println(magicDictionary.search("hhllo")); // Output: true
        System.out.println(magicDictionary.search("hell"));  // Output: false
        System.out.println(magicDictionary.search("leetcoded")); // Output: false
    }
}

