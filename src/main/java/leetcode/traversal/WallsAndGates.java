package leetcode.traversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/walls-and-gates/#/description
 *
 * Created by venkatamunnangi on 5/21/17.
 */
public class WallsAndGates {
    public static final int[] d = {0, 1, 0, -1, 0};

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        /// adding all gates.
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] point = queue.remove();
            int x = point[0], y = point[1];

            if( x > 0 && rooms[x-1][y] == Integer.MAX_VALUE) {
                rooms[x-1][y] = rooms[x][y] +1;
                queue.offer(new int[]{x-1, y});
            }

            // since we are looking x+1, look at penultimate item.
            if( x <m-1 && rooms[x+1][y] == Integer.MAX_VALUE) {
                rooms[x+1][y] = rooms[x][y] +1;
                queue.offer(new int[]{x+1, y});
            }

            if( y > 0 && rooms[x][y-1] == Integer.MAX_VALUE) {
                rooms[x][y-1] = rooms[x][y] +1;
                queue.offer(new int[]{x, y-1});
            }

            if( y < n-1 && rooms[x][y+1] == Integer.MAX_VALUE) {
                rooms[x][y+1] = rooms[x][y] +1;
                queue.offer(new int[]{x, y+1});
            }
        }
    }
}
