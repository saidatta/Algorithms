package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import leetcode.linkedlist.util.ListNode;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/#/description
 *
 * Created by venkatamunnangi on 3/28/17.
 */
public class MergeKSortedLists {
    // kn log(k)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.add(node);
            }
        }

        while (!priorityQueue.isEmpty()) {
            tail.next = priorityQueue.poll();
            tail = tail.next;

            if (tail.next != null) {
                priorityQueue.offer(tail.next);
            }
        }
        return dummy.next;
    }

    //
    public ListNode mergeKLists2(ListNode[] lists) {
        // Edge case: if there are no lists, return null
        if (lists.length == 0) {
            return null;
        }

        // Interval for merging lists. Start with 1 and double each time.
        int interval = 1;
        while (interval < lists.length) {
            // Merge lists in pairs. After each iteration, the number of lists is halved.
            for (int i = 0; i + interval < lists.length; i += interval * 2) {
                lists[i] = mergeTwoLists(lists[i], lists[i + interval]);
            }
            interval *= 2; // Double the interval
        }

        // Return the merged list
        return lists[0];
    }

    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0); // Dummy head for ease of handling edge cases
        ListNode current = dummyHead; // Pointer to construct the merged list

        // Compare nodes from both lists and merge them in sorted order
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list1;
                list1 = current.next.next;
            }
            current = current.next;
        }

        // Attach any remaining elements from either list
        current.next = (list1 == null) ? list2 : list1;

        return dummyHead.next; // Return the merged list, excluding the dummy head
    }
}
