package Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by venkatamunnangi on 23/11/16.
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {

        if(root == null) {
            return null;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            TreeNode r = q.poll();

            TreeNode left = r.left;
            r.left = r.right;
            r.right = left;

            if (r.left != null) {
                q.offer(r.left);
            }
            if (r.right != null) {
                q.offer(r.right);
            }
        }

        return root;
    }

    public List<TreeNode> adjacentNodes(TreeNode n) {
        ArrayList<TreeNode> tnList = new ArrayList<>();
        tnList.add(n.left);
        tnList.add(n.right);
        return tnList;
    }


}
