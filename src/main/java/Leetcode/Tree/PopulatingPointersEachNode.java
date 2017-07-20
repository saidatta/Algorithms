package Leetcode.Tree;

import Leetcode.TreeLinkNode;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/?tab=Description
 *
 * Created by venkatamunnangi on 3/8/17.
 */
public class PopulatingPointersEachNode {

//  class TreeLinkNode {
//        public int val;
//        public TreeLinkNode left, right, next;
//        public TreeLinkNode(int x) { val = x; }
//    }

    public void connect(TreeLinkNode root) {
        if(root == null) {
            return;
        }
        connectHelper(root.left, root.right);
    }

    private void connectHelper(TreeLinkNode node1, TreeLinkNode node2){
        if(node1 == null) {
            return;
        }
        node1.next = node2;
        connectHelper(node1.left, node1.right);
        connectHelper(node2.left, node2.right);
        connectHelper(node1.right, node2.left);
    }
}
