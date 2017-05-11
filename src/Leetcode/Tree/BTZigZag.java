package Leetcode.Tree;

import Leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/#/description
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 *
 * Created by venkatamunnangi on 3/14/17.
 */
public class BTZigZag {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> solution = new ArrayList<>();
        travel(root, solution, 0);
        return solution;
    }

    private void travel(TreeNode curr, List<List<Integer>> solution, int level) {
        if(curr == null) {
            return;
        }

        if(solution.size() <= level) {
            solution.add(new LinkedList<>());
        }

        List<Integer> collection  = solution.get(level);
        if(level % 2 == 0) {
            collection.add(curr.val);
        } else {
            collection.add(0, curr.val);
        }

        travel(curr.left, solution, level + 1);
        travel(curr.right, solution, level + 1);
    }

}
