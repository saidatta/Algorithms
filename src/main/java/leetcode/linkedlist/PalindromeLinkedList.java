package leetcode.linkedlist;

/**
 * https://leetcode.com/problems/palindrome-linked-list/#/description
 *
 * Created by venkatamunnangi on 9/28/16.
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if(fast != null) {
            slow = slow.next;
        }

        slow = reverse(slow);
        while(slow != null && head.val == slow.val) {
            head = head.next;
            slow = slow.next;
        }
        return slow == null;
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next; //2
            node.next = prev; //
            prev = node;
            node = next;
        }

        return prev;
    }

    public static void main(String [] args) {
        PalindromeLinkedList palindromeLL = new PalindromeLinkedList();

        ListNode ll = new ListNode(1);
        ll.next = new ListNode(2);
        ll.next.next = new ListNode(1);

        System.out.println(palindromeLL.isPalindrome(ll));
    }
}
