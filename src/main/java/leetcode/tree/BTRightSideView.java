package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * Created by venkatamunnangi on 3/13/17.
 */
public class BTRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightViewDFS(root, ans, 0);
        return ans;
    }

    public void rightViewDFS(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }

        rightViewDFS(curr.right, result, currDepth + 1);
        rightViewDFS(curr.left, result, currDepth + 1);
    }

    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.remove();
                if (i == size - 1) {
                    result.add(current.val);
                }
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }

        return result;
    }

    public static void main(String [] args) {

        TreeNode root = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(4)));


        BTRightSideView btRightSideView = new BTRightSideView();
        System.out.println(btRightSideView.rightSideView(root));
    }
}
