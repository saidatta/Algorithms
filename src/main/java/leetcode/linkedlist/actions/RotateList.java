package leetcode.linkedlist.actions;

import leetcode.linkedlist.util.ListNode;

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

    public static void main(String[] args) {
        RotateList solver = new RotateList();

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        ListNode result = solver.rotateRight(head1, 2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // Expected: 4 5 1 2 3
    }
}
