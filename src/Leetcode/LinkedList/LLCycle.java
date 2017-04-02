package Leetcode.LinkedList;

import Leetcode.ListNode;

/**
 * https://leetcode.com/problems/linked-list-cycle/#/description
 *
 * Created by venkatamunnangi on 12/11/16.
 */
public class LLCycle {
    // 1-1 = true
    // 1-2 = false
    // 1-2-3-4-1 = true
    // 1-2-3-4-5-6-3 = true
    // 1-2-3-4 = false
    //  Questions to ask: How are two nodes equal? their values?

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }

        if(head.next.val == head.val) {
            return true;
        }

        ListNode p1 = head, p2 = head.next.next;
        while(p1 != null || p2 != null) {
            assert p1 != null;
            if(p1.val == p2.val) {
                return true;
            }
            p1 = p1.next;

            if(p2.next == null) {
                return false;
            }
            p2 = p2.next.next;
        }
        return false;
    }

    public static void main(String [] args) {
        LLCycle llCycle = new LLCycle();
        System.out.println(llCycle.hasCycle(null));
    }
}
