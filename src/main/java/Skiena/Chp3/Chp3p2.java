package Skiena.Chp3;

import leetcode.linkedlist.ListNode;

/**
 * 3-2. Write a program to reverse the direction of a given singly-linked list.
 * In other words, after the reversal all pointers should now point backwards. Your algorithm should take linear time
 * Created by venkatamunnangi on 12/28/16.
 */
public class Chp3p2 {
        ListNode head;

        public Chp3p2() {
            head = null;
        }

        public void prepend(final int in) {
            final ListNode data = new ListNode(in);
            data.next = head;
            head = data;
        }

        public void appendToList(final int in) {
            final ListNode data = new ListNode(in);
            ListNode current = head;
            if (head == null) {
                head = data;
                return;
            }
            while (current.next != null) {
                current = current.next;
            }
            current.next = data;
        }

        public void printList(final ListNode head) {
            ListNode current = head;
            while (current != null) {
                System.out.print(current.val + " -> ");
                current = current.next;
            }
            System.out.println();
        }

        public ListNode reverseList(final ListNode node) {
            //what if 0 node in list
            if (node == null) {
                return null;
            }
            //what if 1 node in list
            if (node.next == null) {
                return node;
            }
            //what if multiple nodes.divide into two parts . reverse second part recursively and in end point second.next to first
            final ListNode secondElem = node.next;
            node.next = null;
            final ListNode reverseRest = reverseList(secondElem);
            secondElem.next = node;
            return reverseRest;
        }

    public static void main(final String[] args) {
        final Chp3p2 linkedList = new Chp3p2();
        linkedList.appendToList(12);
        linkedList.appendToList(13);
        linkedList.appendToList(14);
        linkedList.appendToList(15);
        linkedList.printList(linkedList.head);
        final ListNode head = linkedList.reverseList(linkedList.head);
        linkedList.printList(head);
    }
}
