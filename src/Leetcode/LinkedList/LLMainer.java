package Leetcode.LinkedList;

import Leetcode.ListNode;

/**
 * Created by venkatamunnangi on 12/11/16.
 */
public class LLMainer {
    public static void main(String [] args) {
        testAddTwoNumbers();
    }

    public static void testAddTwoNumbers() {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();

        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(5);
        l1.next.next = new ListNode(1);

        ListNode l2 = new ListNode(4);
        l1.next = new ListNode(3);

        ListNode ll = addTwoNumbers.addTwoNumbers2(l1,l2);

        while(ll.next != null) {
            System.out.println(ll.val);
            ll = ll.next;
        }
    }
}
