package leetcode.tree;

import java.util.*;
import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/symmetric-tree/#/description
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * Created by venkatamunnangi on 24/11/16.
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) {
            return true;
        }

        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();

        if(!checkSymmetry(root.left, root.right, q, q2)) {
            return false;
        }

        while(!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                TreeNode tn1 = q.poll();
                TreeNode tn2 = q2.poll();

                if (!checkSymmetry(tn1.left, tn2.right, q, q2)) {
                    return false;
                }

                if (!checkSymmetry(tn1.right, tn2.left, q, q2)) {
                    return false;
                }
            }
        }

        return true;
    }

    // if both of them are equal then add it the queues.
    public boolean checkSymmetry(TreeNode tn1, TreeNode tn2, Queue<TreeNode> q , Queue<TreeNode> q2) {
        if(q.size() != q2.size()) {
            return false;
        }

        if(tn1 == null && tn2 == null) {
            return true;
        }

        if(tn1 == null || tn2 == null) {
            return false;
        }

        if(tn1.val == tn2.val) {

            q.add(tn1);
            q2.add(tn2);
            return true;
        }

        return false;
    }
}
