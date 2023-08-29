package leetcode.linkedlist;

// https://leetcode.com/problems/swap-nodes-in-pairs/description/
public class SwapNodePairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        // create a dummy node to act as the prevNode for the head node
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prevNode = dummy;

        while (head != null && head.next != null) {
            // nodes to be swapped
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            // swapping
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // reinitialize the head and prevNode for next swap
            prevNode = firstNode;
            head = firstNode.next;
        }

        return dummy.next;
    }
}
