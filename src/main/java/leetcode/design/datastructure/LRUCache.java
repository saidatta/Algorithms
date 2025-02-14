package leetcode.design.datastructure;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;

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
class LRUCache<K, V> {
    LRUNode<K, V> head;
    LRUNode<K, V> tail;
    Map<K, LRUNode<K, V>> myMap;
    int capacity;

    public LRUCache(int capacity) {
        head = new LRUNode<K, V>();
        tail = new LRUNode<K, V>();
        head.next = tail;
        tail.prev = head;
        myMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(K key) {

        if (myMap.containsKey(key)) {
            LRUNode<?, ?> found = myMap.get(key);
            found.remove();
            head.add(found);
            return (int) found.value;
        }

        return -1;
    }

    public void put(K key, V value) {
        if (myMap.containsKey(key)) {
            LRUNode<K, V> found = myMap.get(key);
            found.value = value;
            found.remove();
            head.add(found);
            myMap.remove(key);
            myMap.put(key, found);
        } else {
            if (myMap.size() == capacity) {
                Object delkey = tail.prev.key;
                tail.prev.remove();
                myMap.remove(delkey);
            }
            LRUNode<?, ?> newNode = new LRUNode<K, V>(key, value);
            head.add(newNode);
            myMap.put(key, newNode);

        }

    }

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2); // Create a cache with capacity 2

        cache.put(1, 10); // Set key 1 to value 10
        cache.put(2, 20); // Set key 2 to value 20
        System.out.println(cache.get(1)); // Get the value for key 1, output: 10

        cache.put(3, 30); // Set key 3 to value 30, evicts key 2
        System.out.println(cache.get(2)); // Try to get evicted key 2, output: -1 (not found)

        cache.put(4, 40); // Set key 4 to value 40, evicts key 1
        System.out.println(cache.get(1)); // Try to get evicted key 1, output: -1 (not found)
        System.out.println(cache.get(3)); // Get the value for key 3, output: 30
        System.out.println(cache.get(4)); // Get the value for key 4, output: 40
    }
}

class LRUNode<K, V> {
    LRUNode<?, ?> prev;
    LRUNode<?, ?> next;
    K key;
    V value;
    LRUNode() {}

    LRUNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(V value) {
        this.value = value;
    }
    public void add(LRUNode<?, ?> node) {
        node.next = this.next;
        node.prev = this;
        this.next.prev = node;
        this.next = node;
    }

    public void remove() {
        this.next.prev = this.prev;
        this.prev.next = this.next;
    }

}
