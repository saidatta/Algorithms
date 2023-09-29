package CC;

import leetcode.tree.util.TreeNode;

class UnivalTree {

    private int numOfUnivalSubtrees;

    /**
     * Run the isUnivalTree subroutine on the given root, which will update the numOfUnivalSubtrees global counter.
     *
     * @param root TreeNode root node
     * @return number of Unival Subtrees
     */
    int countUnivalSubtrees(TreeNode root) {
        numOfUnivalSubtrees = 0;
        isUnivalTree(root);
        return numOfUnivalSubtrees;
    }

    /**
     * Build the answer with a recursive dynamic programming expression. While computing each subproblem, update the
     * global counter if the subproblem returns true (root is a subtree).
     * @param root TreeNode tree
     * @return true if tree is a unival tree, else false.
     */
    boolean isUnivalTree(TreeNode root) {
        if (root == null)
            return false;

        // return the conjunction of two implications, where p is node exists, and q is that it's
        //  value equals current root value, and it is a unival subtree
        // 1. for p == root.left, if p -> q <==> not p or q
        // 2. for p == root.right, if p -> q <==> not p or q
        boolean leftIsUnivalTree =  (root.left == null || (isUnivalTree(root.left) && root.left.val == root.val));
        boolean rightIsUnivalTree = (root.right == null || (isUnivalTree(root.right) && root.right.val == root.val));
        if(leftIsUnivalTree && rightIsUnivalTree) {
            numOfUnivalSubtrees++;
            return true;
        }
        return false;
    }
}