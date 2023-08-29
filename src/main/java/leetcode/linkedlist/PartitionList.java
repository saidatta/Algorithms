package leetcode.linkedlist;


// https://leetcode.com/problems/partition-list/description/
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode lessHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);
        ListNode less = lessHead, greater = greaterHead;

        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }

        // Connect the 'less' list to the 'greater' list.
        less.next = greaterHead.next;

        // End the 'greater' list.
        greater.next = null;

        return lessHead.next;
    }
}
