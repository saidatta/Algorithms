package leetcode.tree.list;

import leetcode.linkedlist.util.ListNode;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/
public class ConvertSortedListToBST {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);

        ListNode prev = null, slow = head, fast = head;

        // Finding the middle of the linked list
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);

        prev.next = null;  // Splitting the list into two halves
        root.left = sortedListToBST(head);

        root.right = sortedListToBST(slow.next);

        return root;
    }

    public static void main(String[] args) {
        ConvertSortedListToBST converter = new ConvertSortedListToBST();

        ListNode head = new ListNode(-10);
        head.next = new ListNode(-3);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(5);
        head.next.next.next.next = new ListNode(9);

        System.out.println(converter.sortedListToBST(head));
        // Output: The root of the height-balanced BST
    }
}
