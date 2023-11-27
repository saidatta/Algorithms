package leetcode.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplaceWords {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
    }

    private void insertWord(TrieNode root, String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        node.word = word;
    }

    private String getRoot(TrieNode root, String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return word;
            if (node.word != null) return node.word;
        }
        return word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode trie = new TrieNode();
        for (String root : dictionary) {
            insertWord(trie, root);
        }

        String[] words = sentence.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (result.length() > 0) result.append(" ");
            result.append(getRoot(trie, word));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ReplaceWords solution = new ReplaceWords();
        List<String> dictionary = Arrays.asList("cat", "bat", "rat");
        String sentence = "the cattle was rattled by the battery";
        System.out.println(solution.replaceWords(dictionary, sentence));
        // Output: "the cat was rat by the bat"
    }
}
