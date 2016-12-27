package Leetcode;

import Leetcode.LinkedList.IntersectionLL;
import Leetcode.LinkedList.OddEvenLL;
import Leetcode.Tree.BSTCheck;

/**
 * Created by venkatamunnangi on 12/11/16.
 */
public class Mainer {
    public static void main(String [] args) {
        testBSTCheck();
    }

    public static void testOddEvenLL() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        OddEvenLL odd = new OddEvenLL();
        ListNode ans = odd.oddEvenList(l1);
        while(ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }

    public static void testIntersectionLL() {
        ListNode l1 = new ListNode(1);
        IntersectionLL intLL = new IntersectionLL();
        ListNode l2 = new ListNode(1);
        ListNode ans = intLL.getIntersectionNode(l1,l2);
        while(ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
        }
    }

    public static void testBSTCheck() {
        TreeNode l1 = new TreeNode(1);
        l1.left = new TreeNode(1);
        BSTCheck bstCheck = new BSTCheck();
        System.out.println(bstCheck.validateBST(l1, null));
    }



}
