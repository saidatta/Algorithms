package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/cousins-in-binary-tree/
public class BinaryTreeCousins {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isXExist = false;
            boolean isYExist = false;

            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.remove();

                if (currentNode.val == x) isXExist = true;
                if (currentNode.val == y) isYExist = true;

                if (currentNode.left != null && currentNode.right != null) {
                    if (currentNode.left.val == x && currentNode.right.val == y) {
                        return false;
                    }
                    if (currentNode.left.val == y && currentNode.right.val == x) {
                        return false;
                    }
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }

            if (isXExist && isYExist) return true;
            if (isXExist || isYExist) return false;
        }

        return false;
    }
}
