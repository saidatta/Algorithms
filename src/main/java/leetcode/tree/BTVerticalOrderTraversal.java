package leetcode.tree;

import leetcode.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/#/description
 *
 * Created by venkatamunnangi on 3/25/17.
 */
public class BTVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, ArrayList<Integer>> columnMap = new HashMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> columnQueue = new LinkedList<>();
        int minColumn = 0, maxColumn = 0;

        nodeQueue.add(root);
        columnQueue.add(0);

        while (!nodeQueue.isEmpty()) {
            TreeNode currentNode = nodeQueue.poll();
            int currentColumn = columnQueue.poll();

            // Process left child
            if (currentNode.left != null) {
                nodeQueue.offer(currentNode.left);
                columnQueue.offer(currentColumn - 1);
                minColumn = Math.min(minColumn, currentColumn - 1);
            }

            // Process right child
            if (currentNode.right != null) {
                nodeQueue.offer(currentNode.right);
                columnQueue.offer(currentColumn + 1);
                maxColumn = Math.max(maxColumn, currentColumn + 1);
            }

            columnMap.computeIfAbsent(currentColumn, k -> new ArrayList<>())
                    .add(currentNode.val);
        }

        for (int i = minColumn; i <= maxColumn; i++) {
            result.add(columnMap.get(i));
        }

        return result;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        BTVerticalOrderTraversal traversal = new BTVerticalOrderTraversal();
        System.out.println(traversal.verticalOrder(root));
    }
}
