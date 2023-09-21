package leetcode.array;

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

    /**
     * Computes the length of the longest set constructed using the given rules.
     * This can be seen as finding the longest cycle in the represented graph.
     * The graph is represented by an array where the index and its corresponding
     * value forms an edge.
     *
     * @param nums The input array of integers.
     * @return The length of the longest set.
     */
    public int findLongestSetLength(int[] nums) {
        int maxLength = 0;
        boolean[] visited = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int currentLength = calculateSetLength(nums, i, visited);
                maxLength = Math.max(maxLength, currentLength);
            }
        }

        return maxLength;
    }

    /**
     * Computes the length of the set starting from a given index.
     * It traverses the graph until a cycle is detected.
     *
     * @param nums The input array of integers.
     * @param start The starting index for the set.
     * @param visited An array to keep track of visited indices.
     * @return The length of the set starting from the given index.
     */
    private int calculateSetLength(int[] nums, int start, boolean[] visited) {
        int currentIndex = start;
        int count = 0;

        // Traverse until we detect a cycle
        while (count == 0 || currentIndex != start) {
            visited[currentIndex] = true;
            currentIndex = nums[currentIndex];
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        ArrayNesting solution = new ArrayNesting();

        int[] nums1 = {5, 4, 0, 3, 1, 6, 2};
        System.out.println(solution.findLongestSetLength(nums1));  // Expected output: 4

        int[] nums2 = {0, 1, 2};
        System.out.println(solution.findLongestSetLength(nums2));  // Expected output: 1
    }
}

