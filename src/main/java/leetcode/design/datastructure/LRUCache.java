package leetcode.design.datastructure;

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
    int capacity; // Maximum size of the cache
    Map<Integer, Node> map = new LinkedHashMap<>(); // Stores key-node pairs for O(1) access
    Node head = null; // Head of the doubly linked list
    Node end = null; // End of the doubly linked list

    // Node class to represent each entry in the cache
    static class Node {
        int key;
        int value;
        Node pre; // Previous node in the doubly linked list
        Node next; // Next node in the doubly linked list

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            // Move the accessed node to the head of the list to mark it as recently used
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }
        return -1; // Return -1 if the key is not found
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            // Update existing node's value and move it to the head of the list
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            // Create a new node for a new key
            Node created = new Node(key, value);
            if (map.size() >= capacity) {
                // If capacity is exceeded, remove the least recently used item (end of the list)
                map.remove(end.key);
                remove(end);
            }
            setHead(created); // Set the new node as the most recently used item
            map.put(key, created); // Add the new node to the map
        }
    }

    private void remove(Node n) {
        // Remove a node from the doubly linked list
        if (n.pre != null) {
            n.pre.next = n.next;
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
        // Set a node as the head of the doubly linked list (most recently used)
        n.next = head;
        n.pre = null;

        if (head != null) {
            head.pre = n;
        }

        head = n;

        if (end == null) {
            end = head; // If the list was empty, set both head and end to the new node
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2); // Create a cache with capacity 2

        cache.set(1, 10); // Set key 1 to value 10
        cache.set(2, 20); // Set key 2 to value 20
        System.out.println(cache.get(1)); // Get the value for key 1, output: 10

        cache.set(3, 30); // Set key 3 to value 30, evicts key 2
        System.out.println(cache.get(2)); // Try to get evicted key 2, output: -1 (not found)

        cache.set(4, 40); // Set key 4 to value 40, evicts key 1
        System.out.println(cache.get(1)); // Try to get evicted key 1, output: -1 (not found)
        System.out.println(cache.get(3)); // Get the value for key 3, output: 30
        System.out.println(cache.get(4)); // Get the value for key 4, output: 40
    }
}
