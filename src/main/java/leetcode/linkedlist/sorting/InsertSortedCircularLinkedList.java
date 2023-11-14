package leetcode.linkedlist.sorting;

import leetcode.linkedlist.util.ListNode;

// https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/description/
public class InsertSortedCircularLinkedList {
    /**
     * Inserts a new node with the given value into the sorted circular linked list.
     * If the list is empty, it creates a new node and returns it.
     *
     * @param head Current head of the circular linked list.
     * @param insertVal Value of the node to be inserted.
     * @return Head of the circular linked list.
     */
    public ListNode insert(ListNode head, int insertVal) {
        // If the list is empty, create a new node, point it to itself and return
        if (head == null) {
            ListNode newNode = new ListNode(insertVal);
            newNode.next = newNode;
            return newNode;
        }

        // Find the correct position to insert the node
        ListNode insertPosition = findInsertPosition(head, insertVal);

        // Insert the node
        insertAfter(insertPosition, insertVal);

        return head;
    }

    /**
     * Finds the node after which the new node with insertVal should be placed.
     */
    private ListNode findInsertPosition(ListNode head, int insertVal) {
        ListNode node = head;

        while (node.next != head) {
            // If the current node and next node are in order
            if (node.val <= node.next.val) {
                // Check if insertVal lies between them
                if (insertVal >= node.val && insertVal <= node.next.val) {
                    return node;
                }
            } else {
                // We're at the point where the list wraps around (max node to min node transition)
                if (insertVal >= node.val || insertVal <= node.next.val) {
                    return node;
                }
            }
            node = node.next;
        }

        return node;
    }

    /**
     * Inserts a new node with insertVal after the specified node.
     */
    private void insertAfter(ListNode node, int insertVal) {
        ListNode next = node.next;
        node.next = new ListNode(insertVal, next);
    }
}
