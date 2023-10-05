package leetcode.linkedlist.util;

public class ImmutableListNode {
    private final int value;
    private final ImmutableListNode next;

    // Constructor
    public ImmutableListNode(int value, ImmutableListNode next) {
        this.value = value;
        this.next = next;
    }

    public void printValue() {
        System.out.print(value + " ");
    }

    public ImmutableListNode getNext() {
        return next;
    }
}
