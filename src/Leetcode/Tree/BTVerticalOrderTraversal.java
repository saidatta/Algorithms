package Leetcode.Tree;

import Leetcode.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/#/description
 *
 * Created by venkatamunnangi on 3/25/17.
 */
public class BTVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        int min  = 0, max = 0;

        q.add(root);
        cols.add(0);

        while(!q.isEmpty()) {
            TreeNode currentNode = q.poll();
            int col = cols.poll();

            if(currentNode.left != null) {
                q.offer(currentNode.left);
                cols.offer(col - 1);
                min = Math.min(min, col-1);
            }

            if(currentNode.right != null) {
                q.offer(currentNode.right);
                cols.offer(col + 1);
                max = Math.max(max, col+1);
            }

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(currentNode.val);
        }

        for(int i = min; i<= max; i++) {
            res.add(map.get(i));
        }

        return res;
    }





    public static void main(String [] args) {

    }
}
