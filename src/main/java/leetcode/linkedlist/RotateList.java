package leetcode.linkedlist;

/**
 * https://leetcode.com/problems/rotate-list/
 * <p>
 * Created by venkatamunnangi on 9/11/19.
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        int size = 1; // since we are already at head node
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null) {
            size++;
            fast = fast.next;
        }

        for (int i = size - (k % size); i > 1; i--) {
            // i>1 because we need to put slow.next at the start.
            slow = slow.next;
        }

        // Remove and re-attach the list nodes.
        fast.next = head;
        head = slow.next;
        slow.next = null;

        return head;
    }
}
