package Leetcode.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/flip-game-ii/#/description
 *
 * Created by venkatamunnangi on 5/15/17.
 */
public class FlipGameII {

    // https://discuss.leetcode.com/topic/27282/theory-matters-from-backtracking-128ms-to-dp-0ms
    public boolean canWinDP(String s) {
        s = s.replace('-', ' ');
        int G = 0;
        List<Integer> g = new ArrayList<>();
        for (String t : s.split("[ ]+")) {
            int p = t.length();
            if (p == 0) continue;
            while (g.size() <= p) {
                char[] x = t.toCharArray();
                int i = 0, j = g.size() - 2;
                while (i <= j)
                    x[g.get(i++) ^ g.get(j--)] = '-';
                g.add(new String(x).indexOf('+'));
            }
            G ^= g.get(p);
        }
        return G != 0;
    }
}
