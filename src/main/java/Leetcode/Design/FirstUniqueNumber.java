package Leetcode.Design;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/first-unique-number/
 */
public class FirstUniqueNumber {
    DoubleLinkedList unique;
    Map<Integer, NodeEntry> valNodeMap;

    public FirstUniqueNumber(int[] nums) {
        unique = new DoubleLinkedList();
        valNodeMap = new HashMap<>();
        for (int num : nums) {
            addValue(num);
        }
    }

    private void addValue(int num) {
        NodeEntry nodeEntry = valNodeMap.get(num);
        if (nodeEntry == null) {
            DoubleLinkedListNode node = new DoubleLinkedListNode(num);
            valNodeMap.put(num, new NodeEntry(node, true));
            unique.add(node);
        } else if (nodeEntry.unique) {
            nodeEntry.unique = false;
            unique.remove(nodeEntry.node);
        }
    }

    public int showFirstUnique() {
        return unique.isEmpty() ? -1 : unique.first().val;
    }

    public void add(int value) {
        addValue(value);
    }

    class DoubleLinkedListNode {
        int val;
        boolean unique;
        DoubleLinkedListNode prev;
        DoubleLinkedListNode next;

        DoubleLinkedListNode(int val) {
            this.val = val;
        }
    }

    class DoubleLinkedList {
        DoubleLinkedListNode head;
        DoubleLinkedListNode tail;
        int size;

        DoubleLinkedList() {
            size = 0;
            head = new DoubleLinkedListNode(0);
            tail = head;
            head.next = tail;
            head.prev = tail;
        }

        boolean isEmpty() {
            return size == 0;
        }

        DoubleLinkedListNode first() {
            return head.next;
        }

        void add(DoubleLinkedListNode node) {
            DoubleLinkedListNode prev = tail.prev;
            prev.next = node;
            tail.prev = node;

            node.next = tail;
            node.prev = prev;
            size++;
        }

        void remove(DoubleLinkedListNode node) {
            DoubleLinkedListNode prev = node.prev;
            prev.next = node.next;
            node.next.prev = prev;
            size--;
        }
    }

    class NodeEntry {
        DoubleLinkedListNode node;
        boolean unique;

        NodeEntry(DoubleLinkedListNode node, boolean unique) {
            this.node = node;
            this.unique = unique;
        }
    }
}
