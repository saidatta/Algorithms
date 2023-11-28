package leetcode.backtracking.game;

import java.util.ArrayList;
import java.util.HashMap;
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
            if (p == 0) {
                continue;
            }
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

    public boolean canWin(String s) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < s.length() - 1; i++){
            if(s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                // generate all possible sequence after every attempt
                list.add(s.substring(0, i) + "--" + s.substring(i + 2, s.length()));
            }
        }

        for(String str : list) {
            if(!canWin(str)) {          // if there is any one way the next player can't win, take it and you'll win
                return true;
            }
        }
        return false;
    }

    public boolean canWinMemo(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        HashMap<String, Boolean> winMap = new HashMap<String, Boolean>();
        return helper(s, winMap);
    }

//    One search path:
//
//    Input s = "++++++++"
//
//    Player 0: "--++++++"
//
//    Player 1: "----++++"
//
//    Player 0: "----+--+"
//
//    Player0 can win for the input string as "----++++".
//
//    Another search path:
//
//    Player 0: "++--++++"
//
//    Player 1: "----++++"
//
//    Player 0: "----+--+"
//
//            (Duplicate computation happens. We have already known anyone can win for the
//
//    input string as "----++++".)

    public boolean helper(String inputString, HashMap<String, Boolean> winMap) {
        if (winMap.containsKey(inputString)) {
            return winMap.get(inputString);
        }



        for (int i = 0; i < inputString.length() - 1; i++) {
            if (inputString.startsWith("++", i)) {
                String nextTurn = inputString.substring(0, i) + "--" + inputString.substring(i+2);
                if (!helper(nextTurn, winMap)) {
                    winMap.put(inputString, true);
                    return true;
                }
            }
        }

        winMap.put(inputString, false);
        return false;
    }

    public static void main(String [] args) {
        FlipGameII flipGameII = new FlipGameII();
        System.out.println(flipGameII.canWin("++++"));
    }
}
