package leetcode.tree;


import leetcode.linkedlist.ListNode;

import javax.swing.tree.TreeNode;

/**
 * Created by venkatamunnangi on 13/11/16.
 */
public class SortedListToBST {
        private ListNode end = null;
        public TreeNode sortedListToBST(ListNode head) {
            end = head;
            ListNode c = head;
            int count = 0;
            while(c!=null){
                count++;
                c = c.next;
            }
            return convert(head, count);
        }


        private TreeNode convert(ListNode n, int count){
            if(count==0){
                return null;
            }
            int m = count/2;
            TreeNode left = convert(n, m);
            TreeNode ret = null; //new Leetcode.TreeNode(end.val);
            end = end.next;
//            ret.left = left;
 //           ret.right = convert(end, count-m-1);
            return ret;
        }

}
