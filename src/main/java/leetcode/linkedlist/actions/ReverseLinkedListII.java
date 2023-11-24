package leetcode.linkedlist.actions;

import leetcode.linkedlist.util.ListNode;

// https://leetcode.com/problems/reverse-linked-list-ii/description/
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int curIdx = 1;
        ListNode beforeLeft = null;
        ListNode prev = null, cur = head, nxt;
        ListNode leftPtr = null;

        while (cur != null) {
            nxt = cur.next;

            if (curIdx == left - 1) {
                beforeLeft = cur;
            } else if (curIdx == left) {
                leftPtr = cur;
            } else if (curIdx > left && curIdx < right) {
                cur.next = prev;
            } else if (curIdx == right) {
                assert leftPtr != null;
                leftPtr.next = cur.next;
                cur.next = prev;
                if (beforeLeft != null) {
                    beforeLeft.next = cur;
                    return head;
                } else {
                    return cur;
                }
            }

            curIdx++;
            prev = cur;
            cur = nxt;
        }
        return head;
    }
}
