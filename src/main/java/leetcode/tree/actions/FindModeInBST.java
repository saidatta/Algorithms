package leetcode.tree.actions;

import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/find-mode-in-binary-search-tree/
public class FindModeInBST {

    private TreeNode prev = null;
    private int maxCount = 0;
    private int currentCount = 0;
    private int modeCount = 0;
    private int[] modes;

    public int[] findMode(TreeNode root) {
        // First pass to find the max frequency
        inOrderPass(root);
        modes = new int[modeCount];
        modeCount = 0;
        currentCount = 0;
        // Second pass to fill the modes array
        inOrderPass(root);
        return modes;
    }

    private void inOrderPass(TreeNode node) {
        if (node == null) return;
        inOrderPass(node.left);

        if (prev != null && prev.val == node.val) {
            currentCount++;
        } else {
            currentCount = 1;
        }

        if (currentCount > maxCount) {
            maxCount = currentCount;
            modeCount = 1;
        } else if (currentCount == maxCount) {
            if (modes != null) {
                modes[modeCount] = node.val;
            }
            // if multiple max frequencies, then multiple mode count.
            modeCount++;
        }

        prev = node;
        inOrderPass(node.right);
    }

    public static void main(String[] args) {
        FindModeInBST solution = new FindModeInBST();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        int[] result = solution.findMode(root);
        for (int val : result) {
            System.out.print(val + " ");
        }
        // Output: 2
    }
}



