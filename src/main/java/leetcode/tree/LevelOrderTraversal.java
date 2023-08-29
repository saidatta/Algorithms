package leetcode.tree;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  LeetCode 102
 *  https://leetcode.com/problems/binary-tree-level-order-traversal/#/description
 *
 * Created by venkatamunnangi on 24/11/16.
 */
public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        ArrayList<Integer> list1 = new ArrayList<>();
        while (!q.isEmpty()) {
            int size = q.size();

            while(size -- > 0) {
                TreeNode n1 = q.poll();

                list1.add(n1.val);

                if(n1.left != null) {
                    q.add(n1.left);
                }

                if(n1.right != null) {
                    q.add(n1.right);
                }
            }

            result.add(list1);
            list1 = new ArrayList<>();
        }

        return result;
    }
}
