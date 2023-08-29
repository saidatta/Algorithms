package leetcode.tree;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * Created by venkatamunnangi on 3/13/17.
 */
public class BTRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        rightView(root, ans, 0);
        return ans;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }

    public static void main(String [] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        BTRightSideView btRightSideView = new BTRightSideView();
        System.out.println(btRightSideView.rightSideView(root));
    }
}
