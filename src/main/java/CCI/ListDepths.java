package CCI;

import leetcode.tree.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by venkatamunnangi on 12/16/16.
 */
public class ListDepths {
    ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {

        ArrayList<LinkedList<TreeNode>> answer = new ArrayList<>();
        LinkedList<TreeNode> currentNodes = new LinkedList<>();
        if(root == null) {
            return new ArrayList<>();
        } else {
            currentNodes.add(root);
        }


        while(currentNodes.size() > 0) {
            answer.add(currentNodes);
            LinkedList<TreeNode> parents = currentNodes;
            currentNodes = new LinkedList<>();

            for(TreeNode node : parents) {
                if(node.left != null) {
                    currentNodes.add(node.left);
                }

                if(node.right != null) {
                    currentNodes.add(node.right);
                }
            }
        }
        return answer;
    }
}
