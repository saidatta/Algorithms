package leetcode.linkedlist.math;

import leetcode.linkedlist.util.ListNode;

/**
 * LC - 328
 * https://leetcode.com/problems/odd-even-linked-list/#/description
 * <p>
 * Created by venkatamunnangi on 12/11/16.
 */
public class OddEvenLL {
    // null - null
    // 1 = 1
    // 1-2-3 = 1-3-2

    // 1-2-3-4 = 1-3-2-4
    // 1-1-2-3 = 1-2-1-3
    // 1-2-3-4-5 = 1-3-5-2-4
    public ListNode     oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p1 = head, p2 = head.next; // odd, even
        ListNode odd = new ListNode(p1.val), even = new ListNode(p2.val), oddHead = odd, evenHead = even; // 1,2

        while (p2.next != null) { // p2.next = 3
            p1 = p2.next; //next odd  = 3
            p2 = p2.next.next; // next even = 4

            if (p2 == null) {
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
        if (head == null) return null;

        ListNode oddPtr = head;
        ListNode evenPtr = head.next;
        ListNode evenHead = evenPtr;

        while (evenPtr != null && evenPtr.next != null) {
            oddPtr.next = oddPtr.next.next;
            evenPtr.next = evenPtr.next.next;
            oddPtr = oddPtr.next;
            evenPtr = evenPtr.next;
        }

        oddPtr.next = evenHead;
        return head;
    }

    // Main method for testing
    public static void main(String[] args) {
        OddEvenLL solution = new OddEvenLL();

        // Example 1: Input: head = [1,2,3,4,5]
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        head = solution.oddEvenList(head);
        printList(head); // Expected Output: [1,3,5,2,4]

        // Example 2: Input: head = [2,1,3,5,6,4,7]
        head = new ListNode(2, new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(7)))))));
        head = solution.oddEvenList(head);
        printList(head); // Expected Output: [2,3,6,7,1,5,4]
    }

    // Helper method to print the linked list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
