package leetcode.linkedlist.actions;

import leetcode.linkedlist.util.ListNode;

/**
 * https://leetcode.com/problems/sort-list/
 *
 * Created by venkatamunnangi on 12/11/16.
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Divide the list into two halves using tortoise and hare approach
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // Step 2: Recursively sort both halves
        ListNode leftSide = sortList(head);
        ListNode rightSide = sortList(slow);

        // Step 3: Merge the two halves
        return merge(leftSide, rightSide);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode mergedList = new ListNode(-1);
        ListNode current = mergedList;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        return mergedList.next;
    }
}
