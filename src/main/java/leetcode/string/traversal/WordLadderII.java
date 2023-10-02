package leetcode.string.traversal;

import java.util.*;

// https://leetcode.com/problems/word-ladder-ii/description/
public class WordLadderII {
    private Map<String, List<String>> adjMap; // mapping of a word to its neighbors
    private List<List<String>> transformationSequences; // result to store all transformation sequences


//    The solution contains two steps
//    1 Use BFS to construct a graph.
//    2. Use DFS to construct the paths from end to start.Both solutions got AC within 1s.
//    The first step BFS is quite important. I summarized three tricks
//
//    Using a MAP to store the min ladder of each word, or use a SET to store the words visited in current ladder,
//    when the current ladder was completed, delete the visited words from unvisited. That's why I have two similar
//    solutions.
//
//    Use Character iteration to find all possible paths. Do not compare one word to all the other words and check if
//    they only differ by one character.
//
//    One word is allowed to be inserted into the queue only ONCE.
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        transformationSequences = new ArrayList<>();
        if (wordSet.isEmpty()) {
            return transformationSequences;
        }

        int shortestPath = Integer.MAX_VALUE;
        Queue<String> wordQueue = new ArrayDeque<>();
        wordQueue.add(start);

        adjMap = new HashMap<>();

        Map<String, Integer> wordLadderLengths = initializeLadderLengths(wordSet, start);
        wordSet.add(end);

        // BFS to build adjacency map and calculate shortest path
        while (!wordQueue.isEmpty()) {
            String currentWord = wordQueue.poll();
            int currentStep = wordLadderLengths.get(currentWord) + 1;

            if (currentStep > shortestPath) {
                break;
            }

            List<String> neighbors = getNeighbors(currentWord);
            for (String neighbor : neighbors) {
                if (!wordLadderLengths.containsKey(neighbor)) {
                    continue;
                }
                if (currentStep > wordLadderLengths.get(neighbor)) {
                    continue;
                } else if (currentStep < wordLadderLengths.get(neighbor)) {
                    wordQueue.add(neighbor);
                    wordLadderLengths.put(neighbor, currentStep);
                }

                adjMap.putIfAbsent(neighbor, new LinkedList<>());
                adjMap.get(neighbor).add(currentWord);

                if (neighbor.equals(end)) {
                    shortestPath = currentStep;
                }
            }
        }

        // Use DFS to backtrack from the end word to the start word
        List<String> path = new LinkedList<>();
        backtrack(end, start, path);
        return transformationSequences;
    }

    private Map<String, Integer> initializeLadderLengths(Set<String> wordSet, String start) {
        Map<String, Integer> ladder = new HashMap<>();
        for (String word : wordSet) {
            ladder.put(word, Integer.MAX_VALUE);
        }
        ladder.put(start, 0);
        return ladder;
    }

    private List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            StringBuilder wordBuilder = new StringBuilder(word);
            for (char ch = 'a'; ch <= 'z'; ch++) {
                wordBuilder.setCharAt(i, ch);
                neighbors.add(wordBuilder.toString());
            }
        }
        return neighbors;
    }

    private void backtrack(String currentWord, String start, List<String> path) {
        if (currentWord.equals(start)) {
            path.add(0, start);
            transformationSequences.add(new ArrayList<>(path));
            path.remove(0);
            return;
        }
        path.add(0, currentWord);
        if (adjMap.get(currentWord) != null) {
            for (String neighbor : adjMap.get(currentWord)) {
                backtrack(neighbor, start, path);
            }
        }
        path.remove(0);
    }

    public static void main(String[] args) {
        WordLadderII solution = new WordLadderII();
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(solution.findLadders("hit", "cog", wordList));
    }
}
