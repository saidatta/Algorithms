package Leetcode.LinkedList;

import Leetcode.ListNode;

/**
 * Created by venkatamunnangi on 9/25/16.
 */
public class ReverseLinkedList {

    // 1 - 3 - 4 = 4 - 3 - 1
    // 1 - 2 = 2 - 1
    // 1 = 1
    // null = null
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head, ln2 = null;

        while(curr != null) {
            ListNode temp = curr.next; // 3 |
            curr.next = ln2; // 1
            ln2 = curr; // 1
            curr = temp; // 3
        }

        // returns head of constructed head.
        return ln2;
    }

    public static void main(String [] args) {
        ReverseLinkedList rl = new ReverseLinkedList();

        ListNode ln1 = new ListNode(1);
        ln1.next = new ListNode(3);
        ln1.next.next = new ListNode(4);
        ln1.next.next.next = new ListNode(10);
        ln1.next.next.next.next = new ListNode(19);

        rl.reverseList(ln1);
    }
}