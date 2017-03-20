package Leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

/**
 * https://leetcode.com/problems/lfu-cache/#/description
 *
 * Created by venkatamunnangi on 3/19/17.
 */
public class LFUCache {

    private Node head = null;
    private final int capacity;
    private Map<Integer, Integer> valueMap = new HashMap<>();
    private Map<Integer, Node> nodeMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (nodeMap.containsKey(key)) {
            increase(key);
        }

        return valueMap.getOrDefault(key, -1);
    }


    public void set(int key, int value) {
        if (0 == this.capacity) {
            return;
        }
        if (Objects.nonNull(valueMap.put(key, value))) {
            // non-firsttime.
            increase(key);
        } else {
            if (nodeMap.size() == this.capacity) {
                remove();
            }
            add(key);
        }
    }

    private void increase(int key) {
        Node node = nodeMap.get(key);
        node.keys.remove(key);
        if (Objects.isNull(node.next)) {
            node.next = new Node(node, null, 1 + node.count, key);
        } else if (node.next.count == node.count + 1) {
            node.next.keys.add(key);
        } else {
            node.next = node.next.prev = new Node(node, node.next, node.count + 1, key);
        }
        nodeMap.put(key, node.next);
        if (node.keys.isEmpty()) {
            remove(node);
        }
    }

    private void remove(Node node) {
        if (head == node) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (Objects.nonNull(node.next)) {
            node.next.prev = node.prev;
        }
    }

    private void add(int key) {
        if (Objects.isNull(head)) {
            head = new Node(null, null, 1, key);
        } else if (head.count == 1) {
            head.keys.add(key);
        } else {
            head = head.prev = new Node(null, head, 1, key);
        }
        nodeMap.put(key, head);
    }

    private void remove() {
        if (Objects.isNull(head)) {
            return;
        }
        int oldest = head.keys.iterator().next();
        head.keys.remove(oldest);
        if (head.keys.isEmpty()) {
            remove(head);
        }
        nodeMap.remove(oldest);
        valueMap.remove(oldest);
    }

    private class Node {
        private Node prev, next;
        private final int count;
        private LinkedHashSet<Integer> keys = new LinkedHashSet<>();

        private Node(Node prev, Node next, int count, int key) {
            this.prev = prev;
            this.next = next;
            this.count = count;
            keys.add(key);
        }
    }

    public static void main(String [] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.set(1,1);
        lfuCache.set(2,2);
        lfuCache.get(1);       // returns 1
        lfuCache.set(3, 3);    // evicts key 2
        lfuCache.get(2);       // returns -1 (not found)
        lfuCache.get(3);       // returns 3.
        lfuCache.set(4, 4);    // evicts key 1.
        lfuCache.get(1);       // returns -1 (not found)
        lfuCache.get(3);       // returns 3
        lfuCache.get(4);       // returns 4
    }
}
