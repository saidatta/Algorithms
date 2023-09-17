package leetcode.linkedlist;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/linked-list-cycle/#/description
 *
 * Created by venkatamunnangi on 12/11/16.
 */
public class LLCycle {

    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }

        if(head.next.val == head.val) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String [] args) {
        LLCycle llCycle = new LLCycle();
        out.println(llCycle.hasCycle(null));
    }
}
