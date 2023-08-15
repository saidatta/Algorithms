package Leetcode.array;

/**
 * https://leetcode.com/problems/trapping-rain-water/#/description
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * Created by venkatamunnangi on 08/11/16.
 */
public class TrappingRainWater {
    public int trap(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        int max = height[0];
        int maxIndex = 0;
        //find the highest bar.
        for (int i = 1; i <= height.length - 1; i++) {
            if (max < height[i]) {
                max = height[i];
                maxIndex = i;
            }
        }

        int prevHigh = 0, ans = 0;
        for (int i = 0; i < maxIndex; i++) {
            if (prevHigh < height[i]) {
                prevHigh = height[i];
            }
            ans += (prevHigh - height[i]);
        }

        prevHigh = 0;
        for (int i = height.length - 1; i > maxIndex; i--) {
            if (prevHigh < height[i]) {
                prevHigh = height[i];
            }
            ans += (prevHigh - height[i]);
        }

        return ans;
    }
}
