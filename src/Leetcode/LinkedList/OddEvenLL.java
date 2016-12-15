package Leetcode.LinkedList;

import Leetcode.ListNode;

/**
 * LC - 328
 * Created by venkatamunnangi on 12/11/16.
 */
public class OddEvenLL {
    // null - null
    // 1 = 1
    // 1-2-3 = 1-3-2

    // 1-2-3-4 = 1-3-2-4
    // 1-1-2-3 = 1-2-1-3
    // 1-2-3-4-5 = 1-3-5-2-4
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode p1 = head, p2 = head.next; // odd, even
        ListNode odd = new ListNode(p1.val), even =  new ListNode(p2.val), oddHead = odd, evenHead = even; // 1,2

        while(p2.next != null) { // p2.next = 3
            p1 = p2.next; //next odd  = 3
            p2 = p2.next.next; // next even = 4

            if(p2 == null) {
                odd.next = new ListNode(p1.val);
                odd = odd.next;
                break;
            } else {
                odd.next = new ListNode(p1.val);
                even.next = new ListNode(p2.val);
                odd = odd.next;
                even = even.next;
            }
        }

        odd.next = evenHead;
        return oddHead;
    }

    public ListNode oddEvenList2(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode odd =head, even =  head.next, oddHead = odd, evenHead = even; // 1,2

        while(even != null && even.next != null) { // p2.next = 3
            odd.next = even.next; //next odd  = 3
            even.next = even.next.next; // next even = 4

            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }
}
