package leetcode.design.datastructure;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/#/description
 * <p>
 * Created by venkatamunnangi on 3/19/17.
 */

/**
 * LFU (Least Frequently Used) Cache implementation.
 * It maintains a cache of a fixed capacity and evicts the least frequently used item when the capacity is reached.
 *
 * https://leetcode.com/problems/lfu-cache
 */
public class LFUCache {
    // Frequency map stores each frequency along with a set of nodes having that frequency.
    private final Map<Integer, LinkedHashSet<LFUNode>> frequencyMap;
    // Key-value map for quick access to nodes.
    private final Map<Integer, LFUNode> keyValueMap;
    private final int capacity;
    private int size; // Current size of the cache
    private int minFrequency; // The minimum frequency among the nodes in the cache

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFrequency = 0;
        frequencyMap = new HashMap<>();
        keyValueMap = new HashMap<>();
    }

    /**
     * Retrieves the value associated with the given key, and updates its frequency.
     *
     * @param key The key to retrieve.
     * @return The value associated with the key, or -1 if the key does not exist.
     */
    public int get(int key) {
        if (capacity == 0 || !keyValueMap.containsKey(key)) {
            return -1;
        }
        LFUNode LFUNode = keyValueMap.get(key);
        increaseFrequency(LFUNode);
        return LFUNode.value;
    }

    /**
     * Inserts a key-value pair into the cache.If the cache is at full capacity, it evicts the least frequently used
     * item.
     *
     * @param key   The key to insert.
     * @param value The value associated with the key.
     */
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (keyValueMap.containsKey(key)) {
            LFUNode LFUNode = keyValueMap.get(key);
            LFUNode.value = value; // Update value
            increaseFrequency(LFUNode);
            return;
        }

        if (size == capacity) {
            evictLFUNode();
        }

        LFUNode newLFUNode = new LFUNode(key, value, 0);
        keyValueMap.put(key, newLFUNode);
        frequencyMap.computeIfAbsent(0, k -> new LinkedHashSet<>()).add(newLFUNode);
        minFrequency = 0;
        size++;
    }

    /**
     * Increases the frequency of a node and updates its position in the frequency map.
     *
     * @param LFUNode The node whose frequency is to be increased.
     */
    private void increaseFrequency(LFUNode LFUNode) {
        int currentFreq = LFUNode.frequency;
        frequencyMap.get(currentFreq).remove(LFUNode);
        if (frequencyMap.get(currentFreq).isEmpty() && minFrequency == currentFreq) {
            minFrequency++;
        }
        LFUNode.frequency++;
        frequencyMap.computeIfAbsent(LFUNode.frequency, k -> new LinkedHashSet<>()).add(LFUNode);
    }

    /**
     * Evicts the least frequently used node from the cache.
     */
    private void evictLFUNode() {
        LinkedHashSet<LFUNode> leastFreqLFUNodes = frequencyMap.get(minFrequency);
        LFUNode toEvict = leastFreqLFUNodes.iterator().next();
        leastFreqLFUNodes.remove(toEvict);
        keyValueMap.remove(toEvict.key);
        if (leastFreqLFUNodes.isEmpty()) {
            frequencyMap.remove(minFrequency);
        }
        size--;
    }

    static class LFUNode {
        int key;
        int value;
        int frequency;

        LFUNode(int key, int value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "[key:" + key + ", value:" + value + ", freq:" + frequency + "]";
        }
    }
}
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
