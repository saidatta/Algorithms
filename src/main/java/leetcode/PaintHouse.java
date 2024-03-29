package leetcode;

/**
 * LC 256
 * https://leetcode.com/problems/paint-house/
 *
 * Created by venkatamunnangi on 2/6/17.
 *
 *  1 2 3
 *  5 7 9
 *
 */
public class PaintHouse {
    public int minCost(int[][] costs) {
        if(costs == null || costs.length == 0) {
            return 0;
        }

        //red(0), blue(1) or green(2)
        for(int i = 1; i< costs.length; i++) {
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }

        return Math.min(Math.min(costs[costs.length - 1][0], costs[costs.length -1][1]), costs[costs.length-1][2]);
    }
}
