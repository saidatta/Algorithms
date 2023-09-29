package leetcode.trie.bst;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/range-sum-of-bst/description/
public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0; // Base case

        int sum = 0;
        // If current node's value is between low and high (inclusive), add it to the sum.
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        // If current node's value is greater than low, then traverse the left child.
        if (root.val > low) {
            sum += rangeSumBST(root.left, low, high);
        }
        // If current node's value is less than high, then traverse the right child.
        if (root.val < high) {
            sum += rangeSumBST(root.right, low, high);
        }
        return sum;
    }

    public int rangeSumBSTIterative(TreeNode root, int low, int high) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int sum = 0;
        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            int val = node.val;
            if(node.left != null && val > low) {
                q.add(node.left);
            }
            if(node.right != null && val < high) {
                q.add(node.right);
            }
            if(val >= low && val <= high) {
                sum += val;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // Create a sample binary search tree
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5, new TreeNode(3), new TreeNode(7));
        root.right = new TreeNode(15, null, new TreeNode(18));

        RangeSumBST solution = new RangeSumBST();
        int sum = solution.rangeSumBST(root, 7, 15);
        System.out.println(sum);  // Expected output: 32
    }
}
