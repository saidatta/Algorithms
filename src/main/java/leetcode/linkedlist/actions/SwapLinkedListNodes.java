package leetcode.linkedlist.actions;

import leetcode.linkedlist.util.ListNode;

// https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
public class SwapLinkedListNodes {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode first = head, second = head;
        ListNode current = head;
        int length = 0;

        // Calculate the total length of the linked list
        while (current != null) {
            length++;
            current = current.next;
        }

        // Find the kth node from the beginning
        for (int i = 1; i < k; i++) {
            first = first.next;
        }

        // Find the kth node from the end
        for (int i = 1; i <= length - k; i++) {
            second = second.next;
        }

        // Swap values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;

        return head;
    }

    public static void main(String[] args) {
        SwapLinkedListNodes solution = new SwapLinkedListNodes();

        // Creating a linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2; // For example, swap the 2nd node from start and end

        // Swapping nodes
        head = solution.swapNodes(head, k);

        // Printing the linked list after swapping
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
