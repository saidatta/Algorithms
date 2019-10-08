package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by venkatamunnangi on 10/1/19.
 */
public class LongestCommonPrefix {
    private static TrieNode root = new TrieNode();

    private static class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }


    public static String solution(String[] strings) {
        buildTrie(strings);
        StringBuilder commonPrefix = new StringBuilder();
        TrieNode node = root;
        while(node.children.size() == 1 && !node.endOfWord) {
            Map.Entry<Character, TrieNode> nodeChildren = node.children.entrySet().iterator().next();
            char childrenIndex = nodeChildren.getKey();
            commonPrefix = commonPrefix.append(childrenIndex);
            node = nodeChildren.getValue();
        }

        return commonPrefix.toString();
    }

    private static void buildTrie(String[] strings) {
        for(String currentString : strings) {
            TrieNode node = root;
            for(char c : currentString.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.endOfWord = true;
        }
    }
}
