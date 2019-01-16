package Leetcode;

import java.util.*;

public class SentenceSimilarityII {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }

        Map<String, Set<String>> graph = new HashMap<>();
        for (String[] p : pairs) {
            graph.putIfAbsent(p[0], new HashSet<>());
            graph.putIfAbsent(p[1], new HashSet<>());
            graph.get(p[0]).add(p[1]);
            graph.get(p[1]).add(p[0]);
        }

        for (int i = 0; i < words1.length; i++) {
            if (Objects.equals(words1[i], words2[i])) {
                continue;
            }

            if (!graph.containsKey(words1[i])){
                return false;
            }

            if (!dfs(graph, words1[i], words2[i], new HashSet<>())){
                return false;
            }
        }

        return true;
    }

    private boolean dfs(Map<String, Set<String>> graph, String source, String target, Set<String> visited) {
        if (graph.get(source).contains(target)) {
            return true;
        }

        if (visited.add(source)) {
            return graph.get(source).stream().anyMatch((String nextPairWord) -> !visited.contains(nextPairWord) && dfs(graph, nextPairWord, target, visited));
        }
        return false;
    }
}
