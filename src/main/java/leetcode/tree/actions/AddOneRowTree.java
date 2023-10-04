package leetcode.tree.actions;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/add-one-row-to-tree/
public class AddOneRowTree {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newNode = new TreeNode(val);
            newNode.left = root;
            return newNode;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentDepth = 1;

        while (!queue.isEmpty() && currentDepth < depth - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            currentDepth++;
        }

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode leftChild = node.left;
            TreeNode rightChild = node.right;
            // adding the new nodes
            node.left = new TreeNode(val);
            node.right = new TreeNode(val);

            node.left.left = leftChild;
            node.right.right = rightChild;
        }

        return root;
    }

    public static void main(String[] args) {
        AddOneRowTree solution = new AddOneRowTree();

        // Test case 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(1);
        root1.right.left = new TreeNode(5);
        TreeNode newRoot1 = solution.addOneRow(root1, 1, 2);
        printTree(newRoot1);  // This should print [4,1,1,2,null,null,6,3,1,5]

        System.out.println();

        // Test case 2
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(1);
        TreeNode newRoot2 = solution.addOneRow(root2, 1, 3);
        printTree(newRoot2);  // This should print [4,2,null,1,1,3,null,null,1]
    }

    // Helper function to print tree in BFS manner (for visualization purposes)
    public static void printTree(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node != null) {
                System.out.print(node.val + ",");
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                System.out.print("null,");
            }
        }
    }
}
