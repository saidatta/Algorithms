package leetcode.linkedlist.traversal;

import leetcode.linkedlist.util.ImmutableListNode;

// https://leetcode.com/problems/print-immutable-linked-list-in-reverse/description/
public class PrintLinkedListInReverse {
    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head == null) return; // Base case

        // Recursively move to end
        printLinkedListInReverse(head.getNext());
        // On way back from recursion, print
        head.printValue();
    }
}
