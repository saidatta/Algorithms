package Leetcode;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/#/description
 *
 * Created by venkatamunnangi on 3/28/17.
 */
public class MergeKSortedLists {

    // kn log(k)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> priorityQueue= new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);

        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists) {
            if (node != null) {
                priorityQueue.add(node);
            }
        }

        while (!priorityQueue.isEmpty()){
            tail.next = priorityQueue.poll();
            tail = tail.next;

            if (tail.next != null) {
                priorityQueue.offer(tail.next);
            }
        }
        return dummy.next;
    }
}
