package leetcode.linkedlist;

import leetcode.linkedlist.util.ListNode;

/**
 * A solution to check if a given linked list is a palindrome.
 * The approach involves:
 * 1. Using the two-pointer (fast and slow) technique to find the middle of the linked list.
 * 2. Reversing the second half of the linked list.
 * 3. Comparing the first half and the reversed second half to check for palindrome property.
 *
 * Problem URL: https://leetcode.com/problems/palindrome-linked-list/
 */
public class PalindromeLinkedList {

    /**
     * Checks if the provided linked list is a palindrome.
     *
     * @param head Head of the input linked list
     * @return true if linked list is a palindrome, false otherwise
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // Find the middle of the linked list using the two-pointer technique.
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // If list has odd elements, skip the middle element.
        if (fast != null) {
            slow = slow.next;
        }

        // Reverse the second half of the list.
        slow = reverse(slow);

        // Compare the two halves.
        while (slow != null && head.val == slow.val) {
            head = head.next;
            slow = slow.next;
        }

        // If we've reached the end of the reversed half, then the list is a palindrome.
        return slow == null;
    }

    /**
     * Reverses the linked list starting from the provided node.
     *
     * @param node Start node of the list to be reversed
     * @return Head of the reversed linked list
     */
    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            // Save the next node
            ListNode nextNode = node.next;
            // Update current node's next to previous node (reverse)
            node.next = prev;
            // Move the prev pointer to the current node
            prev = node;
            // Move to the next node in the original list
            node = nextNode;
        }

        return prev;
    }

    //// REALISTIC - reverses the end of list.

    /**
     * Determines if the linked list starting from the given head is a palindrome.
     *
     * @param head the head of the linked list
     * @return true if the linked list is a palindrome, false otherwise
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }

        // Find the end of the first half and the start of the reversed second half.
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Compare the first and second half nodes for palindrome properties.
        ListNode pointer1 = head;
        ListNode pointer2 = secondHalfStart;
        boolean isPalindromic = true;

        while (isPalindromic && pointer2 != null) {
            if (pointer1.val != pointer2.val) {
                isPalindromic = false;
            }
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }

        // Restore the original structure of the linked list.
        firstHalfEnd.next = reverseList(secondHalfStart);

        return isPalindromic;
    }

    /**
     * Reverses a linked list starting from the given head.
     *
     * @param head the start of the linked list to reverse
     * @return the head of the reversed linked list
     */
    private ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
    }

    /**
     * Determines the end node of the first half of the linked list.
     *
     * @param head the start of the linked list
     * @return the end node of the first half of the linked list
     */
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fastPointer = head;
        ListNode slowPointer = head;

        while (fastPointer.next != null && fastPointer.next.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }

        return slowPointer;
    }

    public static void main(String[] args) {
        PalindromeLinkedList palindromeLL = new PalindromeLinkedList();

        // Sample linked list: 1 -> 2 -> 1
        ListNode ll = new ListNode(1);
        ll.next = new ListNode(2);
        ll.next.next = new ListNode(1);

        // Check if the sample linked list is a palindrome.
        System.out.println(palindromeLL.isPalindrome(ll)); // Expected output: true
    }
}