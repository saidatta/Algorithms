package Leetcode.Tree;

import Leetcode.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/?tab=Description
 *
 * Created by venkatamunnangi on 3/7/17.
 */
public class LowestCommonAncestorBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            // -ve * -ve means right subtree
            // +ve * +ve means left subtree
            root = p.val < root.val ? root.left : root.right;
        }
        return root;
    }

    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            // left subtree.
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            // right subtree
            return lowestCommonAncestor(root.right, p, q);
        }else{
            return root;
        }
    }

    class Solution {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            // Value of p
            int pVal = p.val;

            // Value of q;
            int qVal = q.val;

            // Start from the root node of the tree
            TreeNode node = root;

            // Traverse the tree
            while (node != null) {

                // Value of ancestor/parent node.
                int parentVal = node.val;

                if (pVal > parentVal && qVal > parentVal) {
                    // If both p and q are greater than parent
                    node = node.right;
                } else if (pVal < parentVal && qVal < parentVal) {
                    // If both p and q are lesser than parent
                    node = node.left;
                } else {
                    // We have found the split point, i.e. the LCA node.
                    return node;
                }
            }
            return null;
        }
    }
}
