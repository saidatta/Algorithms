package leetcode.linkedlist.math;

import leetcode.linkedlist.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers-ii/
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseLinkedList(l1);
        l2 = reverseLinkedList(l2);

        ListNode result = new ListNode(0);
        ListNode current = result;
        int carry = 0;

        while(l1 != null || l2 != null || carry != 0){
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            current.next = newNode;
            current = newNode;
        }
        return reverseLinkedList(result.next);
    }

    private ListNode reverseLinkedList(ListNode node){
        ListNode previousNode = null;
        ListNode currentNode = node;
        while (currentNode != null){
            ListNode nextTemp = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextTemp;
        }
        return previousNode;
    }
}
