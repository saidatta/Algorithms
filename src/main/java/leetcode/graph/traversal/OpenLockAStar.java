package leetcode.graph.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class OpenLockAStar {
    public int openLock(String[] deadends, String target) {
        HashSet<String> set = new HashSet<>(Arrays.asList(deadends));
        if (set.contains("0000")) {
            return -1;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.estToTal));
        pq.offer(new Node("0000", 0, target));
        while (!pq.isEmpty()) {
            Node cur = pq.remove();
            if (set.contains(cur.string)) {
                continue;
            }
            if (target.equals(cur.string)) {
                return cur.distSoFar;
            }
            set.add(cur.string);
            List<String> nbs = neighbors(cur.string);
            for (String nb : nbs) {
                if (set.contains(nb)) {
                    continue;
                }
                pq.offer(new Node(nb, cur.distSoFar + 1, target));
            }
        }
        return -1;
    }

    private List<String> neighbors(String s) {
        List<String> strings = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = addOne(chars[i]);
            strings.add(new String(chars));
            chars[i] = minusOne(chars[i]);
            chars[i] = minusOne(chars[i]);
            strings.add(new String(chars));
            chars[i] = addOne(chars[i]);
        }
        return strings;
    }

    private char addOne(char c) {
        return c == '9' ? '0' : (char) (c + 1);
    }

    private char minusOne(char c) {
        return c == '0' ? '9' : (char) (c - 1);
    }
}

class Node {
    String string;
    int distSoFar;
    int estToTal;

    Node(String s, int distSoFar, String target) {
        this.string = s;
        this.distSoFar = distSoFar;
        this.estToTal = distSoFar + editDist(s, target);
    }

    private int editDist(String s1, String s2) {
        int dist = 0;
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            int d = Math.abs(s1.charAt(i) - s2.charAt(i));
            dist += Math.min(d, 10 - d);
        }
        return dist;
    }
}
