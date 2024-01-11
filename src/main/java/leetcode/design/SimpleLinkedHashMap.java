package leetcode.design;

import java.util.HashMap;
import java.util.Map;

//The `LinkedHashMap` in Java is a hash table and linked list implementation of the `Map` interface, with predictable
// iteration order. It maintains a doubly-linked list running through all of its entries in addition to the usual array
// of buckets storing the entries. Here's a breakdown of its functionality and some key methods:
//
//        ### Characteristics of LinkedHashMap:
//        1. **Ordering Modes**:
//        - **Insertion Order (Default)**: Iterates in the order in which keys were inserted into the map.
//        - **Access Order**: Iterates in the order in which keys were last accessed, from least-recently accessed to
//        most-recently.
//
//2. **Internal Structure**: Combines a hash table with a linked list, thereby linking entries in insertion or access
// order.
//
//3. **Performance**: Similar to `HashMap` in terms of complexity but with a predictable iteration order.
//
//        ### Key Operations:
//
//        #### 1. Put Operation:
//        - Same as `HashMap` for adding elements.
//        - Additionally, links the new node at the tail of the doubly linked list.
//- If a key already exists, its value is updated, and if `accessOrder` is true, the node is moved to the end of the list.
//
//        #### 2. Get Operation:
//        - Retrieves the value for the specified key.
//- If `accessOrder` is true, the accessed node is moved to the end of the list.
//
//#### 3. Remove the Least Recently Used (LRU) Element:
//        - Can be achieved by overriding `removeEldestEntry(Map.Entry<K,V> eldest)` method.
//- If this method returns `true`, the oldest entry is removed whenever a new entry is added.
//
//### Usage Examples:
//
//        #### Access Based on Insertion Order:
//        ```java
//Map<String, String> linkedHashMap = new LinkedHashMap<>();
//
//linkedHashMap.put("A", "1");
//linkedHashMap.put("B", "2");
//linkedHashMap.put("C", "3");
//// ... more puts
//
//for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
//        System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
//        }
//        ```
//
//        #### Traversal Based on Access Order:
//        ```java
//Map<String, String> linkedHashMap = new LinkedHashMap<>(initialCapacity, loadFactor, true);
//
//linkedHashMap.put("A", "1");
//// ... more puts
//linkedHashMap.get("C"); // Accessing "C"
//
//// Iterating after access
//for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
//        System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
//        }
//        ```
//
//        #### Remove Least Recently Used Elements:
//        ```java
//Map<String, String> linkedHashMap = new LinkedHashMap<>(initialCapacity, loadFactor, true) {
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
//        return size() > 3; // Size limit for LRU logic
//    }
//};
//
//linkedHashMap.put("A", "1");
//// ... more puts
//
//for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
//        System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
//        }
//        ```
//
//In the last example, the map automatically removes the least recently accessed entry once the size exceeds 3, making
// it a simple implementation of an LRU cache.
//
//`LinkedHashMap` is especially useful when the order of entries matters, like in caching scenarios (LRU caches) or
// when preserving insertion order is needed.
public class SimpleLinkedHashMap<K, V> {
    private final Map<K, Node<K, V>> hashMap;
    private Node<K, V> head, tail;

    public SimpleLinkedHashMap() {
        hashMap = new HashMap<>();
    }

    public void put(K key, V value) {
        if (hashMap.containsKey(key)) {
            Node<K, V> existingNode = hashMap.get(key);
            existingNode.value = value;
            return;
        }

        Node<K, V> newNode = new Node<>(key, value);
        hashMap.put(key, newNode);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public V get(K key) {
        return hashMap.containsKey(key) ? hashMap.get(key).value : null;
    }

    public void iterate() {
        Node<K, V> current = head;
        while (current != null) {
            System.out.println("Key: " + current.key + ", Value: " + current.value);
            current = current.next;
        }
    }

    // DLL
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

//Possible Q&A
//Q: How does this implementation maintain the insertion order?
//A: It uses a doubly-linked list to link nodes in the order they are added. The put method appends new nodes to the tail of the list.
//        Q: Is this implementation thread-safe?
//A: No, this simple implementation is not thread-safe. To make it thread-safe, you could add synchronization or use ConcurrentHashMap as the underlying map.
//        Q: How does the get method work in this implementation?
//A: It retrieves the value from the internal HashMap. However, in the standard LinkedHashMap, if accessOrder is true, the accessed node would also be moved to the end of the linked list.
//Q: Does your implementation support access-order?
//A: No, this simplified version only supports insertion-order. Implementing access-order would require additional logic to move the node to the end of the list on each access.
//Q: Can this implementation be used as an LRU cache?
//A: Not in its current form. For an LRU cache, you would need to override a method to remove the eldest entry when a new entry is added beyond a certain threshold.
//        Q: How would you improve this implementation?
//A: Enhancements could include implementing the remove method, supporting access-order, and possibly resizing the underlying HashMap for efficiency.
