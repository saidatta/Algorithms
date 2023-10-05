package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
// https://leetcode.com/problems/convert-bst-to-greater-tree/
public class BSTConvertGreaterSumTree {
    private int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            // Traverse the right subtree first.
            bstToGst(root.right);

            // Update the sum and the current node's value.
            sum += root.val;
            root.val = sum;

            // Traverse the left subtree.
            bstToGst(root.left);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4,
                new TreeNode(1,
                        new TreeNode(0),
                        new TreeNode(2, null, new TreeNode(3))),
                new TreeNode(6,
                        new TreeNode(5),
                        new TreeNode(7, null, new TreeNode(8)))
        );

        BSTConvertGreaterSumTree solution = new BSTConvertGreaterSumTree();
        TreeNode gstRoot = solution.bstToGst(root);
        System.out.println(gstRoot);
    }
}
