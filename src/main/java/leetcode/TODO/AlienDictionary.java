package leetcode.TODO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/alien-dictionary/#/description
 *
 * Created by venkatamunnangi on 5/16/17.
 */
import java.util.*;

public class AlienDictionary {
    private final Map<Character, List<Character>> adjList = new HashMap<>();
    private final Map<Character, Integer> inDegree = new HashMap<>();

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";

        initializeGraph(words);
        if (!buildGraph(words)) return "";
        return topologicalSort();
    }

    private void initializeGraph(String[] words) {
        for (String w : words) {
            for (char c : w.toCharArray()) {
                adjList.putIfAbsent(c, new ArrayList<>());
                inDegree.putIfAbsent(c, 0);
            }
        }
    }

    private boolean buildGraph(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            if (word1.length() > word2.length() && word1.startsWith(word2)) return false;

            int minOfWords = Math.min(word1.length(), word2.length());
            for (int j = 0; j < minOfWords; j++) {
                char parent = word1.charAt(j);
                char child = word2.charAt(j);
                if (parent != child) {
                    adjList.get(parent).add(child);
                    inDegree.put(child, inDegree.get(child) + 1);
                    break;
                }
            }
        }
        return true;
    }

    private String topologicalSort() {
        Queue<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        inDegree.forEach((key, value) -> {
            if (value == 0) q.add(key);
        });

        while (!q.isEmpty()) {
            char vertex = q.poll();
            sb.append(vertex);
            List<Character> children = adjList.get(vertex);
            for (char child : children) {
                inDegree.put(child, inDegree.get(child) - 1);
                if (inDegree.get(child) == 0) q.add(child);
            }
        }

        return (inDegree.size() == sb.length()) ? sb.toString() : "";
    }

    public static void main(String[] args) {
        AlienDictionary dictionary = new AlienDictionary();
        System.out.println(dictionary.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(dictionary.alienOrder(new String[]{"z", "x"}));
        System.out.println(dictionary.alienOrder(new String[]{"z", "x", "z"}));
    }
}
