package Leetcode.LinkedList;

import Leetcode.ListNode;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/#/description
 *
 * Created by venkatamunnangi on 12/11/16.
 */
public class MergeTwoSortedLL {
    // null,null = null
    // null, 1 = 1
    // 1, null = 1
    // 1-2, 3-4 = 1-2-3-4
    // 1-3, 2-4 = 1-2-3-4
    // 1-3-9, 4 = 1-3-4-9
    // 4, 1-3-9 = 1-3-4-9
    // 1-3-8-9, 2,5,7,10 = 1-2-3-5-7-8-9-10
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else {
            ListNode ans = new ListNode(Integer.MIN_VALUE), curr = ans;
            ans.next = l1;
            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    l1 = l1.next;
                } else { // l1.val >= l2.val 2 1
                    ListNode tmp = curr.next; // null
                    curr.next = l2; // -1 - 1
                    ListNode tmp2 = l2.next; // 4
                    l2.next = tmp; // null
                    l2 = tmp2; // 4
                }
                curr = curr.next;
            }

            // if one of them is longer than the other.
            if (l1 != null) {
                curr.next = l1;
            }
            if (l2 != null) {
                curr.next = l2;
            }
            // return the one excluding the fake head.
            return ans.next;
        }
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1 == null &&  l2 == null) {
            return null;
        }

        ListNode fHead = new ListNode(Integer.MIN_VALUE);
        ListNode ans = fHead;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                ans.next = l1;
                l1 = l1.next;
            } else {
                ans.next = l2;
                l2 = l2.next;
            }
            ans = ans.next;
        }

        if(l1 != null) {
            ans.next = l1;
        }

        if(l2 != null) {
            ans.next = l2;
        }

        return ans.next;

    }
}
