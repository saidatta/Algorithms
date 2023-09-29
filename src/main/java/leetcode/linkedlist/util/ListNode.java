package leetcode.linkedlist.util;

/**
 * Created by venkatamunnangi on 12/11/16.
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { this.val = x; }

    public ListNode(int insertVal, ListNode next) {
        this.val = insertVal;
        this.next = next;
    }
}
