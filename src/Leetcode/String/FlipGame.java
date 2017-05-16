package Leetcode.String;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/flip-game/#/description
 *
 * Created by venkatamunnangi on 5/15/17.
 */
public class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                list.add(s.substring(0, i - 1) + "--" + s.substring(i + 1, s.length()));
            }
        }
        return list;
    }
}
