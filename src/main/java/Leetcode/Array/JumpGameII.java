package Leetcode.Array;

// https://leetcode.com/problems/jump-game-ii
public class JumpGameII {
    /**
     * The actual jump (incrementing totalCount) is made only when the iteration reaches a point (i) that surpasses the
     * current maxPos. This is the greedy aspect of the algorithm. The code doesn't prematurely jump to a seemingly
     * optimal position early on. Instead, it waits until it absolutely has to jump, ensuring that it has extracted
     * the maximum distance from the current jump. This is why totalCount represents the minimum number of jumps — it
     * only increments when there's no other choice.
     *
     * @param nums
     * @return
     */
    public static int jump(int... nums) {
        int maxPos = 0, totalCount = 0, nextMax = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > maxPos) {
                // if index reached maxPos, then we jumped.
                totalCount++;
                maxPos = nextMax;
            }

            nextMax = Math.max(nextMax, i + nums[i]);
        }
        return totalCount;
    }

    /**
     * BFS (Breadth First Search) approach. Since the problem is about finding the shortest path or minimum number of
     * jumps to reach the end, BFS can be applied as it explores all possible jumps at a current level before moving to
     * the next level. The BFS levels can be equated to the number of jumps.
     *
     * Steps:
     * Start at the first position.
     * For each position, jump to all the possible positions you can reach from that position.
     * Each time you jump to a new position, mark it as visited to ensure you don't revisit it.
     * Keep track of the number of levels (jumps) traversed.
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        if (nums.length <= 1) {
            return 0; // If the length is 1, you're already at the end
        }

        int currentMax = nums[0]; // Current max index you can reach
        int nextMax = nums[0]; // Max index you can reach in next iteration
        int jumps = 1; // Since we know we can reach the end, start from 1

        for (int i = 1; i < nums.length; i++) {
            // If we have reached the last index, return the number of jumps
            if (i == nums.length - 1) {
                return jumps;
            }

            // Update the nextMax
            nextMax = Math.max(nextMax, i + nums[i]);

            // If this is the last index of current jump range
            if (i == currentMax) {
                jumps++;
                currentMax = nextMax;
            }
        }
        return jumps;
    }


    public static void main(String [] args) {
        System.out.println(jump(2,3,1,1,4));
        System.out.println(jump(2,3,0,1,4));
    }
}
