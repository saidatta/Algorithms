package Leetcode;

/**
 * Created by venkatamunnangi on 9/13/19.
 */
public class PaintHouseII {

    /**
     * If there's no constraint, we choose min cost for each house.
     * Since house[i] and house[i - 1] cannot have the same color j, we should choose 2nd min color for house[i - 1].
     * If we choose the 3rd min color for house[i - 1], we might miss potential min cost.
     * min(i) = min(cost[i][j] + 1st min / 2nd min), 0 < j < n.
     * Since current row only relies on last row for getting mins and avoiding same color, O(1) space is enough.
     * 
     * @param costs
     * @return
     */
    public int minCostII(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int min1 = 0, min2 = 0, prevColorIndex = -1;

        for (int i = 0; i < costs.length; i++) {
            int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE, tempPrevColorIndex = -1;

            for (int currentColor = 0; currentColor < costs[0].length; currentColor++) {
                int costWithCurrentColor = costs[i][currentColor] + (currentColor != prevColorIndex ? min1 : min2);

                // min1 -> is the total min value of previous houses
                // min2 -> is the 2nd most min value of previous houses. it will be used in the specific case when prev.
                // house and current house have same color.

                if (costWithCurrentColor < m1) {           // costWithCurrentColor < m1 < m2
                    m2 = m1;
                    m1 = costWithCurrentColor;
                    tempPrevColorIndex = currentColor;
                } else if (costWithCurrentColor < m2) {    // m1 < costWithCurrentColor < m2
                    m2 = costWithCurrentColor;
                }
            }

            min1 = m1;
            min2 = m2;
            prevColorIndex = tempPrevColorIndex;
        }
        return min1;
    }

    public static void main(String [] args) {

    }
}
