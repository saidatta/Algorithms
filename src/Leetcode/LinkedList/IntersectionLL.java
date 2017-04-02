package Leetcode.LinkedList;

import Leetcode.ListNode;

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
        if(headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA, p2 = headB;

        int c1 = 0, c2 = 0;
        while(p1 != null) {
            c1++;
            p1 = p1.next;
        }

        while(p2 != null) {
            c2++;
            p2 = p2.next;
        }

        p1 = headA;
        p2 = headB;

        int diff = Math.abs(c2 - c1);
        while(diff-- > 0) {
            if(c1 > c2) {
                p1 = p1.next;
            } else {
                p2 = p2.next;
            }
        }

        while(p1 != null && p2 != null) {
            if(p1.val == p2.val) {
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return null;
    }
}
