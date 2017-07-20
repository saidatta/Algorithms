package Leetcode.Tree;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/graph-valid-tree/#/description
 *
 * Created by venkatamunnangi on 3/12/17.
 */
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        // initialize n isolated islands
        int[] nums = new int[n];
        Arrays.fill(nums, -1);

        // perform union find
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];

            int x = find(nums, src);
            int y = find(nums, dest);

            // if two vertices happen to be in the same set
            // then there's a cycle
            if (x == y) {
                return false;
            }

            // union
            nums[x] = y;
        }

        // always true because we are mostly focused on the cycle condition
        return edges.length == n - 1;
    }

    private int find(int[] nums, int i) {
        if (nums[i] == -1) {
            return i;
        }
        return find(nums, nums[i]);
    }

    public static void main(String [] args) {
        int[][] edges = {{0,1},{1,2},{2,3},{1,3},{1,4}};
        //int[][] edges = {{0,1},{0,2},{0,3},{1,4}};

        GraphValidTree graphValidTree = new GraphValidTree();
        System.out.println(graphValidTree.validTree(5, edges));
    }
}
