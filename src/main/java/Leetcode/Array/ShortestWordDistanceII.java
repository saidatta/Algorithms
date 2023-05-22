package Leetcode.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/shortest-word-distance-ii/
 *
 * Created by venkatamunnangi on 9/7/19.
 */
public class ShortestWordDistanceII {
    final HashMap<String, List<Integer>> cache;

    public ShortestWordDistanceII(String[] words) {
        cache = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (cache.containsKey(words[i])) {
                cache.get(words[i]).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                cache.put(words[i], list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = cache.get(word1);
        List<Integer> l2 = cache.get(word2);
        int result = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        while (i < l1.size() && j < l2.size()) {
            result = Math.min(result, Math.abs(l1.get(i) - l2.get(j)));
            if (l1.get(i) < l2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }

}
