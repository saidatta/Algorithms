package Leetcode.Design;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/#/description
 * <p>
 * Created by venkatamunnangi on 3/19/17.
 */
public class LFUCache {
    // frequencyMap:
    //==================
    // key is frequency , starts with 0 on entry  , and holds a LinkedHashSet of nodes with that freq,
    // when a node is used again, we remove it from its location to insert with a key of 1 freq higher.
    // since the LinkedHashSet maintains the insertion order we automatically get LRU in case of frequency tie.

    Map<Integer, LinkedHashSet<Node>> frequencyMap;
    Map<Integer, Node> keyValue;
    int capacity;
    int count;              // count of cache
    int lowestFreq;         // current lowest frequency

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        frequencyMap = new HashMap<>();
        keyValue = new HashMap<>();
    }

    public int get(int key) {
        if (this.capacity == 0) {
            return -1;
        }
        if (!keyValue.containsKey(key)) {
            return -1;
        }
        updateFrequency(key);
        return keyValue.get(key).value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (keyValue.containsKey(key)) {
            keyValue.get(key).value = value;// update new value in same key
            updateFrequency(key);
            return;
        }
        Node created = new Node(key, value, 0);
        if (count == capacity) {
            LinkedHashSet<Node> lessFreqNodes = frequencyMap.get(lowestFreq);
            Iterator<Node> itr = lessFreqNodes.iterator();
            Node toRemove = itr.next();
            lessFreqNodes.remove(toRemove);// remove the first one as it is LRU
            keyValue.remove(toRemove.key);
            count--;
            if (lessFreqNodes.isEmpty()) {
                frequencyMap.remove(lowestFreq);
            }
        }

        keyValue.put(key, created);
        lowestFreq = 0;
        count++;
        if (frequencyMap.containsKey(0)) {
            frequencyMap.get(0).add(created);
        } else {
            LinkedHashSet<Node> newNodes = new LinkedHashSet<Node>();
            newNodes.add(created);
            frequencyMap.put(0, newNodes);
        }
    }


    public void updateFrequency(int key) {
        Node n = keyValue.get(key); // get the node
        int freq = n.frequency; // get the frequency from the node

        frequencyMap.get(freq).remove(n); // remove the node from frequency map to put in one level up
        if (frequencyMap.get(freq).size() == 0 && lowestFreq == freq) {
            frequencyMap.remove(freq);
            lowestFreq = freq + 1;
        }
        n.frequency++;// increment the frequency in node itself

        if (frequencyMap.containsKey(freq + 1)) {
            frequencyMap.get(freq + 1).add(n);
        } else {
            LinkedHashSet<Node> newHashSet = new LinkedHashSet<Node>();
            newHashSet.add(n);
            frequencyMap.put(freq + 1, newHashSet);
        }
    }

    class Node {
        int key;
        int value;
        int frequency;

        public Node(int k, int v, int f) {
            this.key = k;
            this.value = v;
            this.frequency = f;
        }

        public String toString() {
            return " [ k:" + key + " v:" + value + " f:" + frequency + "] ";
        }
    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
