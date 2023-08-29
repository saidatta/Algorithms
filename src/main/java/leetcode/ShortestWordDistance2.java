package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 244
 * Created by venkatamunnangi on 12/13/16.
 */
public class ShortestWordDistance2 {
    Map<String, List<Integer>> wordsMap = new HashMap<>();
    int size = 0;

    public ShortestWordDistance2(String[] words) {
        for(int i = 0; i<words.length;i++) {
            String word = words[i];

            if(wordsMap.containsKey(word)) {
                wordsMap.get(word).add(i);
            } else {
                ArrayList<Integer> al = new ArrayList<>();
                al.add(i);
                wordsMap.put(word, al);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = wordsMap.get(word1);
        List<Integer> l2 = wordsMap.get(word2);
        int ans = Integer.MAX_VALUE;

        for(int i = 0, j = 0; i< l1.size() && j < l2.size();) {
            int i1 = l1.get(i);
            int i2 = l2.get(j);
            if(i1 > i2 ) {
                ans = Math.min(ans, i1 - i2);
                j++;
            } else {
                ans = Math.min(ans, i2 - i1);
                i++;
            }
        }
        return ans;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");