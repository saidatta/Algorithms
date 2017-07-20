package Leetcode.LinkedList;

import Leetcode.ListNode;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/#/description
 *
 * Created by venkatamunnangi on 12/11/16.
 */
public class DeleteNodeLL {

    // 1 - 1
    // 1 -> 2 -> 3 - 2
    // 1 -> 2 - 3 - 3

    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
