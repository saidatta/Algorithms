package leetcode.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-search-ii/#/description
 *
 * Created by venkatamunnangi on 7/6/17.
 */
public class WordSearchII {
    Set<String> foundWords = new HashSet<>();
    int[][] dirs={{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) {
        WordSearchII wordSearchII = new WordSearchII();
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        List<String> foundWords = wordSearchII.findWords(board, words);
        System.out.println(foundWords);
    }


    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<>(foundWords);
    }

    public void dfs(char[][] board, boolean[][] visited, String currentWord, int x, int y, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        if (visited[x][y]) {
            return;
        }

        currentWord += board[x][y];
        if (!trie.startsWith(currentWord)) {
            return;
        }

        if (trie.search(currentWord)) {
            foundWords.add(currentWord);
        }

        visited[x][y] = true;

        for (int[] dir: dirs) {
            dfs(board, visited, currentWord, x+dir[0], y+dir[1], trie);
        }
//        dfs(board, visited, currentWord, x - 1, y, trie);
//        dfs(board, visited, currentWord, x + 1, y, trie);
//        dfs(board, visited, currentWord, x, y - 1, trie);
//        dfs(board, visited, currentWord, x, y + 1, trie);
        visited[x][y] = false;
    }

    class Trie {
        private class TrieNode {
            Map<Character, TrieNode> children;
            boolean endOfWord;
            public TrieNode() {
                children = new HashMap<>();
                endOfWord = false;
            }
        }
        private final TrieNode root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode node = current.children.get(ch);
                if (node == null) {
                    node = new TrieNode();
                    current.children.put(ch, node);
                }
                current = node;
            }
            //mark the current nodes endOfWord as true
            current.endOfWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode node = current.children.get(ch);
                //if node does not exist for given char then return false
                if (node == null) {
                    return false;
                }
                current = node;
            }
            //return true of current's endOfWord is true else return false.
            return current.endOfWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode current = root;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                TrieNode node = current.children.get(ch);
                //if node does not exist for given char then return false
                if (node == null) {
                    return false;
                }
                current = node;
            }

            return true;
        }
    }

}
