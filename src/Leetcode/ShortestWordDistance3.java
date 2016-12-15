package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkatamunnangi on 12/14/16.
 */
public class ShortestWordDistance3 {
    public int shortestWordDistance(String[] words, String word1, String word2) {

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        for(int i = 0; i < words.length;i++) {
            if (words[i].equals(word1)) {
                l1.add(i);
            }
            if (words[i].equals(word2)) {
                l2.add(i);
            }
        }
        int ans = Integer.MAX_VALUE;

        int a = 0, b = 0;

        while(a < l1.size() && b < l2.size()) {
            int i1 = l1.get(a);
            int i2 = l2.get(b);

            if(i1 == i2) {
                if(l1.size() == 1 && l2.size() == 1) {
                    return 1;
                } else {
                    if(b == l2.size()-1) {
                        break;
                    }

                    b++;
                    i2 = l2.get(b);
                }
            }

            if(i1 > i2) {
                ans = Math.min(ans, i1 - i2);
                b++;
            } else {
                ans = Math.min(ans, i2 - i1);
                a++;
            }
        }

        return ans;
    }
}
