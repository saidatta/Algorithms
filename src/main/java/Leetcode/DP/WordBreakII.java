package Leetcode.DP;

import java.util.*;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/word-break-ii/#/description
 * <p>
 * Created by venkatamunnangi on 3/24/17.
 */
public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<>());
    }

    // dfs function returns an array including all substrings derived from currentTrimmedWord.
    private List<String> DFS(String currentTrimmedWord, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(currentTrimmedWord)) {
            return map.get(currentTrimmedWord);
        }

        LinkedList<String> constructedSentences = new LinkedList<>();
        if (currentTrimmedWord.isEmpty()) {
            constructedSentences.add("");
            return constructedSentences;
        }
        for (String word : wordDict) {
            if (currentTrimmedWord.startsWith(word)) {
                List<String> sublist = DFS(currentTrimmedWord.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    constructedSentences.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(currentTrimmedWord, constructedSentences);
        return constructedSentences;
    }

    public static void main(String[] args) {
        WordBreakII wordBreakII = new WordBreakII();
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        out.println(wordBreakII.wordBreak("catsanddog", dict));
    }
}
