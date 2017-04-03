package Leetcode;

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
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList), visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int lenOfTransformation = 1;
        while(!queue.isEmpty()) {
            for (int x = queue.size(); x > 0; x--) {
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
                            queue.offer(neighbour);
                        }
                    }
                }
            }
            lenOfTransformation++;
        }

        return 0;
    }

    public static void main(String [] args) {
        WordLadder wordLadder = new WordLadder();
        List<String> dict = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(wordLadder.ladderLength("hit", "cog",dict));
    }

    //hit
    // cog
    //["hot","dot","dog","lot","log","cog"]
}
