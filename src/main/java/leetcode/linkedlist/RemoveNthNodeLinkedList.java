package leetcode.linkedlist;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 */
public class RemoveNthNodeLinkedList {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        // Create a temporary node and a counter to find the length of the linked list
        ListNode temp = head;
        int count = 0;

        // Traverse the linked list and count the number of nodes
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // Calculate the index of the node to be removed from the beginning of the list
        int len = count - n;

        // If the first node needs to be removed, update the head and return
        if (len == 0) {
            head = head.next;
        }
        else {
            // Traverse the list until the node before the one to be removed
            ListNode prev = head;
            while (len - 1 != 0) {
                prev = prev.next;
                len--;
            }
            // Remove the node by updating the previous node's next pointer
            prev.next = prev.next.next;
        }

        // Return the head node of the modified list
        return head;
    }
}
