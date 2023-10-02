package leetcode.linkedlist.actions;

import leetcode.linkedlist.util.ListNode;

/**
 * 160
 * https://leetcode.com/problems/intersection-of-two-linked-lists/?tab=Description
 *
 * A:          a1 → a2
                      ↘
                        c1 → c2 → c3
                      ↗
      B: b1 → b2 → b3
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * Created by venkatamunnangi on 12/11/16.
 */
public class IntersectionLL {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // Calculate the length of both lists
        int lenA = 0, lenB = 0;
        ListNode ptrA = headA, ptrB = headB;
        while (ptrA != null) {
            lenA++;
            ptrA = ptrA.next;
        }
        while (ptrB != null) {
            lenB++;
            ptrB = ptrB.next;
        }

        // Align the pointers to the same starting point
        ptrA = headA;
        ptrB = headB;
        while (lenA > lenB) {
            ptrA = ptrA.next;
            lenA--;
        }
        while (lenB > lenA) {
            ptrB = ptrB.next;
            lenB--;
        }

        // Move both pointers until they meet
        while (ptrA != ptrB) {
            ptrA = ptrA.next;
            ptrB = ptrB.next;
        }

        return ptrA;
    }
}
