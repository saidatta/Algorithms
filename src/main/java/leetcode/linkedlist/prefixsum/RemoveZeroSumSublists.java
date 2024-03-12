package leetcode.linkedlist.prefixsum;

import java.util.HashMap;
import leetcode.linkedlist.util.ListNode;

// https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list
public class RemoveZeroSumSublists {
    public static ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        HashMap<Integer, ListNode> prefixSumToNode = new HashMap<>();
        int sum = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            prefixSumToNode.put(sum, node);
        }

        sum = 0;
        for (ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            node.next = prefixSumToNode.get(sum).next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Example usage
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(-3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(1);

        ListNode result = removeZeroSumSublists(head);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}

//To solve the problem "Remove Zero Sum Consecutive Nodes from Linked List" in Java, we can use a prefix sum approach
// combined with a HashMap to track the prefix sum of the nodes up to the current point. This allows us to find
// sequences that sum up to 0 efficiently. The basic idea is to iterate through the linked list, calculating the
// cumulative sum of the nodes' values. If we encounter a sum that we've seen before, it means the nodes in between
// sum up to 0 and should be removed. We then need to update the next pointers of the nodes accordingly and continue
// the process until no zero-sum sequences remain.
//
//Here is a step-by-step approach:
//
//Create a dummy node that points to the head of the linked list. This simplifies edge cases where the head of the
// list itself needs to be removed.
//Use a HashMap to store the cumulative sum of the nodes as keys and their corresponding nodes as values.
//Iterate through the linked list, updating the cumulative sum.
//If the cumulative sum is found in the HashMap, it means we've found a zero-sum sequence. We then need to remove all
// nodes in this sequence and update the links.
//Finally, return the next node of the dummy node, which is the head of the modified list.