package Leetcode;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/word-squares/#/description
 *
 * Created by venkatamunnangi on 4/30/17.
 */
public class WordSquares {
    class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            for (String w : words) {
                TrieNode cur = root;
                for (char ch : w.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null) {
                        cur.children[idx] = new TrieNode();
                    }
                    cur.children[idx].startWith.add(w);
                    cur = cur.children[idx];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null)
                    return ans;

                cur = cur.children[idx];
            }
            ans.addAll(cur.startWith);
            return ans;
        }
    }

    public List<List<String>> wordSquares(String... words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0)
            return ans;
        int len = words[0].length();
        Trie trie = new Trie(words);
        List<String> ansBuilder = new ArrayList<>();
        for (String w : words) {
            ansBuilder.add(w);
            search(len, trie, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return ans;
    }

    private void search(int len, Trie dictTrie, List<List<String>> ans,
                        List<String> ansBuilder) {
        if (ansBuilder.size() == len) {
            ans.add(new ArrayList<>(ansBuilder));
            return;
        }

        // the idx attribute helps in relating the character that the square needs to start with.
        int idx = ansBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();

        for (String s : ansBuilder) {
            prefixBuilder.append(s.charAt(idx));
        }

        // This command will assist in looking for the prefix word for the word square.
        List<String> startWith = dictTrie.findByPrefix(prefixBuilder.toString());
        for (String sw : startWith) {
            ansBuilder.add(sw);
            search(len, dictTrie, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }

    public static void main(String [] args) {
        WordSquares wordSquares = new WordSquares();

        out.println(wordSquares.wordSquares("ball","area","lead","lady"));
    }
}
