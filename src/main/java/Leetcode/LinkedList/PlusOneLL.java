package Leetcode.LinkedList;

import Leetcode.ListNode;

/**
 * https://leetcode.com/problems/plus-one-linked-list/#/description
 *
 * Created by venkatamunnangi on 5/17/17.
 */
public class PlusOneLL {
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        ListNode j = dummy;

        // j.next is to avoid null pointer at the (j.val != 9).
        while (j.next != null) {
            j = j.next;
            if (j.val != 9) {
                i = j;
            }
        }
        // i = index of last non-9 digit
        // 1 - 2 - 9 - 9
        // 1 - 3 - 0 - 0
        i.val++;
        i = i.next;
        while (i != null) {
            // if you have multiple 9-9's then mark all of them to zero.
            // as we are incrementing the number before the 9.
            i.val = 0;
            i = i.next;
        }

        if (dummy.val == 0) {
            // if there was no carry on the head of the list.
            return dummy.next;
        }
        return dummy;
    }
}
