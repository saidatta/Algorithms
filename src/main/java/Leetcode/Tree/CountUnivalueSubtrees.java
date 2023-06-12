package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * https://leetcode.com/problems/count-univalue-subtrees/#/description
 *
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value
 *
 * Created by venkatamunnangi on 3/14/17.
 */
// O(n)
public class CountUnivalueSubtrees {
    private int count = 0;

    public int countUnivalSubtrees(TreeNode root) {
        helper(root);
        return count;
    }

    private boolean helper(TreeNode node) {
        if (node == null) {
            return true;
        }

        // recursively check the left and right subtrees
        boolean left = helper(node.left);
        boolean right = helper(node.right);

        // if both subtrees are unival trees, check if the current node is also unival
        if (left && right) {
            if (node.left != null && node.val != node.left.val) {
                return false;
            }
            if (node.right != null && node.val != node.right.val) {
                return false;
            }

            // if the current node is unival, increment the count and return true
            count++;
            return true;
        }

        // if either of the subtrees is not unival, the current node cannot be unival
        return false;
    }

    int helper2(TreeNode root, int val)
    {
        if(root == null || root.val != val) return 0;
        return 1 + Math.max(helper2(root.left,val),helper2(root.right,val));
    }
    int longestUnivaluePath(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int sub = Math.max(longestUnivaluePath(root.left),longestUnivaluePath(root.right));
        return Math.max(sub,helper2(root.left,root.val) + helper2(root.right,root.val));
    }


    private final int[][] directions = {{1, 0}, {0, 1}};//{-1, 0}, {0, -1}

    int directionHelper(TreeNode root, int val)
    {
        if(root == null || root.val != val) return 0;
        return 1 + Math.max(helper2(root.left,val),helper2(root.right,val));
    }
//    int longestUnivaluePath(int[][] grid) {
//        if(grid == null) {
//            return 0;
//        }
//
//    }
//
//    int help(int[][] grid, int i,int j) {
//
//        if(!boundaryChecks(i,j, grid)) {
//            return 0;
//        }
//
////        for (int currentDirection = 0; currentDirection < directions.length; currentDirection++) {
//            int x1 = i + directions[0][0];
//            int y1 = j + directions[0][1];
//        int x2 = i + directions[1][0];
//        int y2 = j + directions[1][1];
//
//            int sub = Math.max(help(grid, x1,y1),help(grid, x2,y2));
//        return Math.max(sub,helper2(root.left,root.val) + helper2(root.right,root.val))
////        return sub;
////        }
//
//
//        return Math.max(sub,helper2(root.left,root.val) + helper2(root.right,root.val));
//
//    }

    private boolean boundaryChecks(int i, int j, int[][] matrix) {
        return i >= 0
                && i  < matrix.length
                && j  >= 0
                && j < matrix[0].length;
    }
}
