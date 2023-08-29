package leetcode.design;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/#/description
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 *
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new item.
 *
 * Created by venkatamunnangi on 3/10/17.
 */
public class LRUCache {
    int capacity;
    // value to node.
    Map<Integer, Node> map = new LinkedHashMap<>();
    Node head = null;
    Node end = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            Node created = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(end.key);
                remove(end);
            }
            setHead(created);
            map.put(key, created);
        }
    }

    private void remove(Node n) {
        if (n.pre != null) {
            (n.pre).next = n.next;
        } else {
            head = n.next;
        }

        if (n.next != null) {
            n.next.pre = n.pre;
        } else {
            end = n.pre;
        }

    }

    private void setHead(Node n) {
        n.next = head;
        n.pre = null;

        if (head != null) {
            head.pre = n;
        }

        head = n;

        if (end == null) {
            end = head;
        }
    }

    class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
