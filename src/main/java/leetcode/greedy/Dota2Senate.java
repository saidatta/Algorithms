package leetcode.greedy;

import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate {
    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        int n = senate.length();

        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.add(i);
            } else {
                dire.add(i);
            }
        }

        while (!radiant.isEmpty() && !dire.isEmpty()) {
            if (radiant.peek() < dire.peek()) {
                radiant.add(radiant.poll() + n);
                dire.poll();
            } else {
                dire.add(dire.poll() + n);
                radiant.poll();
            }
        }

        return radiant.isEmpty() ? "Dire" : "Radiant";
    }
}
