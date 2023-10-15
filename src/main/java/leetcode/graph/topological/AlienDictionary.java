package leetcode.graph.topological;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Problem: https://leetcode.com/problems/alien-dictionary/
 */
public class AlienDictionary {

    // Adjacency list to represent our graph
    private final Map<Character, List<Character>> adjacencyList = new HashMap<>();

    // This map keeps track of the number of incoming edges for each node/character
    private final Map<Character, Integer> inDegreeCount = new HashMap<>();

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        // Set up the graph's nodes
        initializeGraph(words);

        // Build the graph's edges based on word order
        if (!buildGraph(words)) {
            return "";
        }

        // Return the order of characters as per the alien dictionary
        return topologicalSort();
    }

    /**
     * Initialize graph with characters from words as nodes.
     */
    private void initializeGraph(String[] words) {
        for (String word : words) {
            for (char character : word.toCharArray()) {
                adjacencyList.putIfAbsent(character, new ArrayList<>());
                inDegreeCount.putIfAbsent(character, 0);
            }
        }
    }

    /**
     * Construct the graph. The order of characters in adjacent words
     * gives us the direction of the edges.
     */
    private boolean buildGraph(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i];
            String nextWord = words[i + 1];

            // Special case: If the current word is a prefix of the next word, then this is not a valid dictionary.
            if (currentWord.length() > nextWord.length() && currentWord.startsWith(nextWord)) {
                return false;
            }

            int minLength = Math.min(currentWord.length(), nextWord.length());
            for (int j = 0; j < minLength; j++) {
                char parentChar = currentWord.charAt(j);
                char childChar = nextWord.charAt(j);
                if (parentChar != childChar) {
                    // Build the edge and update the in-degree of child node
                    adjacencyList.get(parentChar).add(childChar);
                    inDegreeCount.put(childChar, inDegreeCount.get(childChar) + 1);
                    break;
                }
            }
        }
        return true;
    }

    /**
     * Perform topological sort. If we can order all characters without leftovers, we have a valid result.
     */
    private String topologicalSort() {
        Queue<Character> queue = new LinkedList<>();
        StringBuilder order = new StringBuilder();

        // Start with nodes that have no incoming edges
        inDegreeCount.forEach((character, inDegree) -> {
            if (inDegree == 0) {
                queue.add(character);
            }
        });

        while (!queue.isEmpty()) {
            char currentChar = queue.poll();
            order.append(currentChar);

            // Reduce the in-degree of all adjacent nodes and
            // if any of them has no incoming edges, add it to the queue
            for (char adjacentChar : adjacencyList.get(currentChar)) {
                inDegreeCount.put(adjacentChar, inDegreeCount.get(adjacentChar) - 1);
                if (inDegreeCount.get(adjacentChar) == 0) {
                    queue.add(adjacentChar);
                }
            }
        }

        // If we've covered all nodes, return the result
        return (inDegreeCount.size() == order.length()) ? order.toString() : "";
    }

    public static void main(String[] args) {
        AlienDictionary dictionarySolver = new AlienDictionary();
//        System.out.println(dictionarySolver.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
        System.out.println(dictionarySolver.alienOrder(new String[]{"za", "xyzbc"}));
        System.out.println(dictionarySolver.alienOrder(new String[]{"z", "x", "z"}));
    }
}
