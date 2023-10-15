package leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

// Give a binary tree and print out the left and right edge nodes. The left side is from bottom to top and the right
// side is from top to bottom.
//         ex:
//              1
//         /     \\
//         2        3
//        /  \\    / \\
//        4   5   6  7
//               /
//              8
//         output:
//         8 4 2 1 3 7 8

public class LeftViewRightViewTraversal {
    public static void printEdgeNodes(TreeNode root) {
        if (root == null) {
            return;
        }

        List<TreeNode> leftNodes = new ArrayList<>();
        List<TreeNode> rightNodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            TreeNode firstNodeOfLevel = null;
            TreeNode lastNodeOfLevel = null;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.remove();

                if (i == 0) {
                    firstNodeOfLevel = currentNode;
                }

                if (i == levelSize - 1) {
                    lastNodeOfLevel = currentNode;
                }

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            if (!leftNodes.contains(firstNodeOfLevel)) {
                leftNodes.add(firstNodeOfLevel);
            }

            if (!rightNodes.contains(lastNodeOfLevel) && firstNodeOfLevel != lastNodeOfLevel) {
                rightNodes.add(lastNodeOfLevel);
            }
        }

        // Print left nodes in reverse order
        for (int i = leftNodes.size() - 1; i >= 0; i--) {
            System.out.print(leftNodes.get(i).val + " ");
        }

        // Print right nodes
        for (TreeNode node : rightNodes) {
            System.out.print(node.val + " ");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.left.left = new TreeNode(8);
        root.right.right = new TreeNode(7);

        printEdgeNodes(root);
    }
}
