package leetcode.linkedlist;

import leetcode.linkedlist.util.ListNode;

// https://leetcode.com/problems/reverse-nodes-in-k-group/
public class ReverseNodesKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }

        // Count the nodes
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        if (k > count) {
            return head;
        }

        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;
        int i = 0;
        while (current != null && i < k) { // reverse k nodes
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            i++;
        }

        if (next != null) {
            head.next = reverseKGroup(next, k); // recursively reverse the next group of k nodes
        }

        return prev;
    }
}
