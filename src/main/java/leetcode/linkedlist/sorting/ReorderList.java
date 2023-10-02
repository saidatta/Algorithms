package leetcode.linkedlist.sorting;

import leetcode.linkedlist.util.ListNode;

// https://leetcode.com/problems/reorder-list/
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // 1. Find the middle of the linked list
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Reverse the second half
        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        // 3. Merge the two halves
        ListNode first = head, second = prev;
        while (second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }
}
