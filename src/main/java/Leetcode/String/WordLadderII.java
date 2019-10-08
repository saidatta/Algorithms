package Leetcode.String;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder-ii/#/description
 * Find all shortest transformations.
 * <p>
 * Created by venkatamunnangi on 4/4/17.
 */
public class WordLadderII {
    Map<String, List<String>> map;
    List<List<String>> results;

//    The solution contains two steps 1 Use BFS to construct a graph. 2. Use DFS to construct the paths from end to start.Both solutions got AC within 1s.
//
//    The first step BFS is quite important. I summarized three tricks
//
//    Using a MAP to store the min ladder of each word, or use a SET to store the words visited in current ladder, when the current ladder was completed, delete the visited words from unvisited. That's why I have two similar solutions.
//
//    Use Character iteration to find all possible paths. Do not compare one word to all the other words and check if they only differ by one character.
//
//    One word is allowed to be inserted into the queue only ONCE. See my comments.

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        results = new ArrayList<>();
        if (dict.isEmpty()) {
            return results;
        }

        int min = Integer.MAX_VALUE;

        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);

        map = new HashMap<>();

        Map<String, Integer> ladder = new HashMap<String, Integer>();
        for (String string : dict) {
            // dictonaryWords with .
            ladder.put(string, Integer.MAX_VALUE);
        }
        ladder.put(start, 0);

        dict.add(end);

        //BFS: Dijisktra search
        while (!queue.isEmpty()) {

            String word = queue.poll();

            //'step' indicates how many steps are needed to travel to one word.
            int step = ladder.get(word) + 1;

            if (step > min) {
                // base case
                break;
            }

            for (int i = 0; i < word.length(); i++) {
                StringBuilder builder = new StringBuilder(word);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    builder.setCharAt(i, ch);
                    String newWord = builder.toString();
                    if (ladder.containsKey(newWord)) {
                        if (step > ladder.get(newWord))//Check if it is the shortest path to one word.
                        {
                            continue;
                        } else if (step < ladder.get(newWord)) {
                            queue.add(newWord);
                            ladder.put(newWord, step);
                        }
                        // It is a KEY line. If one word already appeared in one ladder,
                        // Do not insert the same word inside the queue twice. Otherwise it gets TLE.

                        if (map.containsKey(newWord)) //Build adjacent Graph
                            map.get(newWord).add(word);
                        else {
                            List<String> list = new LinkedList<String>();
                            list.add(word);
                            map.put(newWord, list);
                            //It is possible to write three lines in one:
                            //map.put(newWord,new LinkedList<String>(Arrays.asList(new String[]{word})));
                            //Which one is better?
                        }

                        if (newWord.equals(end))
                            min = step;

                    }//End if dict contains newWord
                }//End:Iteration from 'a' to 'z'
            }//End:Iteration from the first to the last
        }//End While

        //BackTracking
        LinkedList<String> result = new LinkedList<String>();
        backTrace(end, start, result);

        return results;
    }


    private void backTrace(String word, String start, List<String> list) {
        if (word.equals(start)) {
            list.add(0, start);
            results.add(new ArrayList<String>(list));
            list.remove(0);
            return;
        }
        list.add(0, word);
        if (map.get(word) != null) {
            for (String s : map.get(word)) {
                backTrace(s, start, list);
            }
        }
        list.remove(0);
    }

    public static void main(String[] args) {
        WordLadderII wordLadderII = new WordLadderII();
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));


        //out.println(wordLadderII.findLadders("hit", "cog", wordList));
    }
}
