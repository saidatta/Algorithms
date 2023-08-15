package Leetcode.array;

/**
 * https://leetcode.com/problems/array-nesting/
 *
 * Created by venkatamunnangi on 9/12/19.
 */

//Input: A = [5,4,0,3,1,6,2]
//        Output: 4
//        Explanation:
//        A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
//
//        One of the longest S[K]:
//        S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
public class ArrayNesting {
//    	This is actually a DFS.  Use a visited map to keep track of visited node. If
//    number is visited before, then the set that starts at this number must be smaller then
//    previous max. So we can safely skip this number. In total it's O(n) complexity.
// O(n)
    public int arrayNesting(int[] nums) {
        int max = Integer.MIN_VALUE;
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            max = Math.max(max, calcLength(nums, i, visited));
        }
        return max;
    }

    private int calcLength(int[] nums, int start, boolean[] visited) {
        int i = start, count = 0;
        // we dont intersect in between the chain because
        // The elements of A are all distinct.
        while (count == 0 || i != start) {
            visited[i] = true;
            i = nums[i];
            count++;
        }
        return count;
    }
    //1,2,1
}
