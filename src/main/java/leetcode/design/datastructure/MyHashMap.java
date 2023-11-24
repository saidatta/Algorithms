package leetcode.design.datastructure;

/**
 * https://github.com/donnemartin/system-design-primer/blob/master/solutions/object_oriented_design/hash_table
 * /hash_map.ipynb
 *
 * https://leetcode.com/problems/design-hashmap/
 *
 * My solution is based on Princeton Algorithms version.
 * Due to this problem is acutually a toy-sample version(i.e. key is only int format), so we can simply neglect generic
 * type and only need to think about 'the first node' in every single-chain.
 *
 * From <Algorithms 4th edition>, as you may read, if we want to assure that the time complexity must be in constant
 * time with as small as possible space complexity, we should make the bucket size M around the
 * (total elements number)/5. For this problem, let's say, 10000001/5. (we assume 10000001 is a prime number ,
 * if it is not, please change it to another one )
 */
public class MyHashMap {
    private final Node<Integer, Integer>[] nodes;
    private static final int M = 1000001 / 5;

    /**
     * Initialize your data structure here.
     */
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        nodes = (Node<Integer, Integer>[]) new Node<?, ?>[M];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int index = key % M;
        if (nodes[index] == null) {
            nodes[index] = new Node<>(key, value);
            return;
        }

        Node<Integer, Integer> cur = nodes[index];
        // collision
        while (cur != null) {
            if (cur.key == key) {
                cur.val = value;
                return;
            }
            if (cur.next == null) {
                break;
            }
            cur = cur.next;
        }

        // add it to head of the chaining.
        Node<Integer, Integer> node = new Node<>(key, value);
        node.next = nodes[index];
        nodes[index] = node;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int index = key % M;
        if (nodes[index] == null) {
            return -1;
        }
        Node<Integer, Integer> currentNode = nodes[index];
        //collision.
        while (currentNode != null) {
            if (currentNode.key == key) {
                return currentNode.val;
            }
            currentNode = currentNode.next;
        }

        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int index = key % M;
        if (nodes[index] == null) {
            return;
        }
        Node<Integer, Integer> currentNode = nodes[index];
        if (currentNode.key == key) {
            nodes[index] = currentNode.next;
            return;
        }
        while (currentNode.next != null) {
            if (currentNode.next.key == key) {
                currentNode.next = currentNode.next.next;
                return;
            }
            currentNode = currentNode.next;
        }
    }
}

// single LL
class Node<K, V> {
    K key;
    V val;
    Node<K,V> next;

    Node(K key, V val) {
        this.key = key;
        this.val = val;
    }
}
