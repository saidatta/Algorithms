package Leetcode.DP;

import java.util.*;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/word-break-ii/#/description
 *
 * Created by venkatamunnangi on 3/24/17.
 */
public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<>());
    }

    // DFS function returns an array including all substrings derived from s.
    private List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String>res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }

    public static void main(String [] args) {
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
