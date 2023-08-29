package leetcode.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/#/description
 *
 * Word DICTIONARY
 * Created by venkatamunnangi on 4/19/17.
 */
public class AddAndSearchWord {

    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        TrieNode() {
            children = new HashMap<>();
        }
    }

    final TrieNode root;

    /** Initialize your data structure here. */
    public AddAndSearchWord() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode current = root;

        for(int i = 0; i<word.length();i++) {
            char c = word.charAt(i);
            TrieNode n = current.children.get(c);
            if(n == null) {
                n = new TrieNode();
                current.children.put(c, n);
            }

            current = n;
        }
        current.endOfWord = true;
    }

    /** Returns if the word is in the data structure. A word could
     * contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word.toCharArray(), 0, root);
    }

    private boolean search(char[] chars, int index, TrieNode parent) {
        if (index == chars.length) {
            return parent.endOfWord;
        }

        Map<Character, TrieNode> childNodes = parent.children;
        char c = chars[index];
        if (c == '.') {
            for (TrieNode n : childNodes.values()) {
                if (n != null) {
                    boolean b = search(chars, index + 1, n);
                    if (b) {
                        return true;
                    }
                }
            }
            return false;
        }
        TrieNode node = childNodes.get(c);
// simplified version in return statement
//        if (node == null){
//            return false;
//        }

        return node != null && search(chars, ++index, node);

    }
}
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */