package leetcode.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a Trie data structure to support
 * add and search operations with wildcard character '.'.
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
 */
public class AddAndSearchWord {

    // TrieNode inner class representing each node in the Trie
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    private final TrieNode root;

    /** Constructor to initialize the root of Trie. */
    public AddAndSearchWord() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie.
     * @param word the word to be added
     */
    public void addWord(String word) {
        TrieNode currentNode = root;

        for(char ch : word.toCharArray()) {
            TrieNode nextNode = currentNode.children.get(ch);
            if(nextNode == null) {
                nextNode = new TrieNode();
                currentNode.children.put(ch, nextNode);
            }

            currentNode = nextNode;
        }

        currentNode.isEndOfWord = true;
    }

    /**
     * Searches for a word in the Trie. Supports wildcard character '.'.
     * @param word the word to be searched
     * @return true if word is found, false otherwise
     */
    public boolean search(String word) {
        return searchRecursively(word.toCharArray(), 0, root);
    }

    private boolean searchRecursively(char[] chars, int index, TrieNode currentNode) {
        if (index == chars.length) {
            return currentNode.isEndOfWord;
        }

        char currentChar = chars[index];
        if (currentChar == '.') {
            for (TrieNode childNode : currentNode.children.values()) {
                if (childNode != null && searchRecursively(chars, index + 1, childNode)) {
                    return true;
                }
            }
            return false;
        }

        TrieNode child = currentNode.children.get(currentChar);
        return child != null && searchRecursively(chars, index + 1, child);
    }
}
