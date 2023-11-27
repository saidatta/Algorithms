package leetcode.design.datastructure;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/complete-binary-tree-inserter/description/
public class CompleteBinaryTreeInserter {
    private final TreeNode root;
    private final Queue<TreeNode> queue;

    public CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;
        this.queue = new LinkedList<>();
        // Initialize the queue with potential parent nodes
        Queue<TreeNode> tempQueue = new LinkedList<>();
        tempQueue.offer(root);

        while (!tempQueue.isEmpty()) {
            TreeNode node = tempQueue.poll();
            // If this node can be a parent for the next inserted node, add it to the queue
            if (node.left == null || node.right == null) {
                queue.offer(node);
            }
            if (node.left != null) {
                tempQueue.offer(node.left);
            }
            if (node.right != null) {
                tempQueue.offer(node.right);
            }
        }
    }

    public int insert(int val) {
        TreeNode parent = queue.peek(); // Peek at the potential parent
        TreeNode newNode = new TreeNode(val);

        // Insert the new node as either the left or right child
        assert parent != null;
        if (parent.left == null) {
            parent.left = newNode;
        } else if (parent.right == null) {
            parent.right = newNode;
            queue.poll(); // This parent is now complete, so remove it from the queue
        }

        // The new node can potentially be a parent for future insertions
        queue.offer(newNode);
        return parent.val;
    }

    public TreeNode get_root() {
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        CompleteBinaryTreeInserter cbtInserter = new CompleteBinaryTreeInserter(root);
        System.out.println(cbtInserter.insert(3)); // Output: 1
        System.out.println(cbtInserter.insert(4)); // Output: 2
        // Output the root of the tree
        System.out.println(cbtInserter.get_root().val); // Output: 1
    }
}
