package leetcode.linkedlist;

import java.util.Random;

/**
 * https://leetcode.com/problems/linked-list-random-node/#/description
 *
 * Created by venkatamunnangi on 7/22/17.
 */
class LinkedListRandomNode {
    Random random;
    int size = 0;
    ListNode temp;

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public LinkedListRandomNode(ListNode head) {
        random = new Random();
        temp = head;
        while (head.next != null) {
            size++;
            head = head.next;
        }
        size++;
        //ciruclar LL
        head.next = temp;

    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        int n = random.nextInt(size);
        while (n-- >= 0) {
            temp = temp.next;
        }
        return temp.val;
    }
}