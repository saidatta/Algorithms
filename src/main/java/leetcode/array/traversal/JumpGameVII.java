package leetcode.array.traversal;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/jump-game-vii/submissions/
public class JumpGameVII {
    public static boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1') {
            return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int maxReach = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // Calculate the start position for next jump
            int startJump = Math.max(curr + minJump, maxReach + 1);
            for (int nextJump = startJump; nextJump <= curr + maxJump && nextJump < n; nextJump++) {
                if (s.charAt(nextJump) == '0') {
                    if (nextJump == n - 1) {
                        return true;
                    }
                    queue.add(nextJump);
                }
            }
            maxReach = curr + maxJump;
        }

        return false;
    }

    public static void main(String[] args) {
        String s1 = "011010";
        int minJump1 = 2, maxJump1 = 3;
        System.out.println(canReach(s1, minJump1, maxJump1));  // true

        String s2 = "01101110";
        int minJump2 = 2, maxJump2 = 3;
        System.out.println(canReach(s2, minJump2, maxJump2));  // false
    }

}
