package leetcode.linkedlist.actions;

import leetcode.linkedlist.util.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list/#/description
 *
 * Created by venkatamunnangi on 9/25/16.
 */
public class ReverseLinkedList {

    // 1 - 3 - 4 = 4 - 3 - 1
    // 1 - 2 = 2 - 1
    // 1 = 1
    // null = null

    public ListNode reverseList(ListNode head) {
        // If the list is empty or has only one node, no need to reverse.
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;
        ListNode reversedPart = null; // This will store the reversed part of the list.

        while (current != null) {
            // Keep track of the next node before reversing the link.
            ListNode nextNode = current.next;

            // Reverse the current node's link.
            current.next = reversedPart;

            // Move the reversedPart pointer to this node.
            reversedPart = current;

            // Move to the next node in the original list.
            current = nextNode;
        }

        // At the end of the loop, reversedPart will point to the new head of the reversed list.
        return reversedPart;
    }

    // Iterative solution
    public ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    // Recursive solution
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reversedListHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;

        return reversedListHead;
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