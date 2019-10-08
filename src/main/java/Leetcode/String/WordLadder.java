package Leetcode.String;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/#/description
 * <p>
 * Example:
 * beginWord = "hit"
 * endWord = "cog"
 * <p>
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * <p>
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p>
 * Created by venkatamunnangi on 4/2/17.
 */
public class WordLadder {

    //Time Complexity: (n26)^L
    //  Space Complexity: (n26)^L
    //  n = number of words in list
    //  L = length of each word in list
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList), visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int lenOfTransformation = 1;


        // BFS produces the shortest word sequence for an undirected unweighted graph.
        while(!queue.isEmpty()) {
            for (int x = queue.size(); x > 0; x--) { // empty the current poll iteration.
                String word = queue.poll();

                if (word.equals(endWord)) {
                    return lenOfTransformation;
                }


                for (int i = 0; i < word.length(); i++) {
                    char[] wordArray = word.toCharArray();
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (j == wordArray[i]) {
                            continue;
                        }

                        wordArray[i] = j;
                        String neighbour = String.valueOf(wordArray);
                        if (dict.contains(neighbour) && !visited.contains(neighbour)) {
                            visited.add(neighbour);
                            // keep adding new words for the next transformation sequence.
                            queue.offer(neighbour);
                        }
                    }
                }
            }
            lenOfTransformation++;
        }

        return 0;
    }

    public int ladderLengthDoubleBFS(String beginWord, String endWord, Set<String> wordList) {
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();

        int len = 1;
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                swapSets(beginSet, endSet);
            }

            Set<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] wordCharArray = word.toCharArray();

                for (int i = 0; i < wordCharArray.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = wordCharArray[i];
                        wordCharArray[i] = c;
                        String target = String.valueOf(wordCharArray);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        wordCharArray[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;
        }

        return 0;
    }


    private void swapSets(Set<String> beginSet, Set<String> endSet) {
        Set<String> set = beginSet;
        beginSet = endSet;
        endSet = set;
    }
    public static void main(String [] args) {
        WordLadder wordLadder = new WordLadder();
//        List<String> dict = Arrays.asList("hot","dot","dog","lot","log","cog");
        List<String> dict = Arrays.asList("hot","dot","dog","lot");//"log","cog");
        System.out.println(wordLadder.ladderLength("hit", "cog",dict));
    }

    //hit
    // cog
    //["hot","dot","dog","lot","log","cog"]
}
