package Leetcode.LinkedList;

import Leetcode.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 * Created by venkatamunnangi on 12/11/16.
 */
public class AddTwoNumbers {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int carry = 0;

            ListNode head = new ListNode(Integer.MIN_VALUE);
            ListNode ln1 = l1, ln2 = l2, ln3 = head;

            while(ln1 != null || ln2 != null) {
                if (ln1 != null) {
                    carry += ln1.val;
                    ln1 = ln1.next;
                }

                if(ln2 != null) {
                    carry += ln2.val;
                    ln2 = ln2.next;
                }
                ln3.next = new ListNode(carry % 10);
                ln3 = ln3.next;
                carry /= 10;
            }

            if(carry == 1) {
                ln3.next = new ListNode(1);
            }
            return head.next;
        }
}
