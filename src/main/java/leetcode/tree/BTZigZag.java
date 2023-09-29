package leetcode.tree;

import java.util.Deque;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import leetcode.tree.util.TreeNode;

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
        if (curr == null) {
            return;
        }

        if (solution.size() <= level) {
            solution.add(new LinkedList<>());
        }

        List<Integer> collection = solution.get(level);
        if (level % 2 == 0) {
            collection.add(curr.val);
        } else {
            collection.add(0, curr.val);
        }

        travel(curr.left, solution, level + 1);
        travel(curr.right, solution, level + 1);
    }

    public List<List<Integer>> zigzagLevelOrderBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean zigzag = false;

        while (!queue.isEmpty()) {
            List<Integer> currentLevel = new ArrayList<>();
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                if (zigzag) {
                    TreeNode node = queue.removeLast();
                    currentLevel.add(node.val);
                    if (node.right != null) {
                        queue.addFirst(node.right);
                    }
                    if (node.left != null) {
                        queue.addFirst(node.left);
                    }
                } else {
                    TreeNode node = queue.removeFirst();
                    currentLevel.add(node.val);
                    if (node.left != null) {
                        queue.addLast(node.left);
                    }
                    if (node.right != null) {
                        queue.addLast(node.right);
                    }
                }
            }

            result.add(currentLevel);
            zigzag = !zigzag;
        }
        return result;
    }

    public static void main(String[] args) {
        // Example:
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        BTZigZag traversal = new BTZigZag();
        List<List<Integer>> result = traversal.zigzagLevelOrder(root);
        for (List<Integer> level : result) {
            System.out.println(level);
        }
        // Expected output:
        // [3]
        // [20, 9]
        // [15, 7]
    }
}
