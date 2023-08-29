package leetcode.trie;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/word-squares/#/description
 *
 * Created by venkatamunnangi on 4/30/17.
 */
public class WordSquares {
    public static void main(String [] args) {
        WordSquares wordSquares = new WordSquares();

        out.println(wordSquares.wordSquares("ball","area","lead","lady"));
    }

    public List<List<String>> wordSquares(String... words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }

        int lengthOfWord = words[0].length();
        Trie trie = new Trie(words);
        List<String> ansBuilder = new ArrayList<>();
        for (String w : words) {
            ansBuilder.add(w);
            search(lengthOfWord, trie, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return ans;
    }

    private void search(int lenOfWord, Trie dictTrie, List<List<String>> ans,
                        List<String> ansBuilder) {
        if (ansBuilder.size() == lenOfWord) {
            ans.add(new ArrayList<>(ansBuilder));
            return;
        }

        // the index attribute helps in relating the character that the square needs to start with.
        int index = ansBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();

        for (String s : ansBuilder) {
            prefixBuilder.append(s.charAt(index));
        }

        // This command will assist in looking for the prefix word for the word square.
        List<String> startWith = dictTrie.findByPrefix(prefixBuilder.toString());
        for (String sw : startWith) {
            ansBuilder.add(sw);
            search(lenOfWord, dictTrie, ans, ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }


    class TrieNode {
        List<String> startWithWords;
        TrieNode[] children;

        TrieNode() {
            startWithWords = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            for (String currentWord : words) {
                TrieNode currentNode = root;
                for (char ch : currentWord.toCharArray()) {
                    int index = ch - 'a';
                    if (currentNode.children[index] == null) {
                        currentNode.children[index] = new TrieNode();
                    }
                    currentNode.children[index].startWithWords.add(currentWord);
                    currentNode = currentNode.children[index];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                int index = ch - 'a';
                if (cur.children[index] == null) {
                    return ans;
                }

                cur = cur.children[index];
            }
            ans.addAll(cur.startWithWords);
            return ans;
        }
    }
}
