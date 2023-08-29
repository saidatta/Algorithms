package leetcode.linkedlist;

// https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/
class DoubleNumberLinkedList {
    public ListNode doubleIt(ListNode head) {
        // Create dummy node to act as the new head
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;

        // Start from the least significant digit (end of the list)
        head = reverse(head);

        while (head != null) {
            int sum = head.val * 2 + carry;
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
            head = head.next;
        }

        // If there's any remaining carry, add it to the result
        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return reverse(dummy.next);
    }

    // Helper function to reverse a linked list
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        return prev;
    }

    public static void main(String[] args) {
//        DoubleNumberLinkedList sol = new DoubleNumberLinkedList();
//
//        ListNode head1 = new ListNode(1, new ListNode(8, new ListNode(9)));
//        ListNode result1 = sol.doubleLinkedList(head1);
//        printList(result1); // 3 -> 7 -> 8
//
//        ListNode head2 = new ListNode(9, new ListNode(9, new ListNode(9)));
//        ListNode result2 = sol.doubleLinkedList(head2);
//        printList(result2); // 1 -> 9 -> 9 -> 8
    }

    // Helper function to print the linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }
}