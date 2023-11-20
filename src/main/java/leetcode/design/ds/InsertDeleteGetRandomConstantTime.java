package leetcode.design.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/#/description
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom(): Returns a random element from current set of elements.
 * Each element must have the same probability of being returned.
 *
 * Created by venkatamunnangi on 4/2/17.
 */
import java.util.*;

// https://leetcode.com/problems/insert-delete-getrandom-o1/description/
class RandomizedSet {
    private Map<Integer, Integer> valueToIndex;
    private List<Integer> values;
    private Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        valueToIndex = new HashMap<>();
        values = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (valueToIndex.containsKey(val)) {
            return false;
        }
        values.add(val);
        valueToIndex.put(val, values.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!valueToIndex.containsKey(val)) {
            return false;
        }

        int index = valueToIndex.get(val);
        int lastElement = values.get(values.size() - 1);

        values.set(index, lastElement);
        valueToIndex.put(lastElement, index);

        values.remove(values.size() - 1);
        valueToIndex.remove(val);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return values.get(rand.nextInt(values.size()));
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1));  // true
        System.out.println(randomizedSet.remove(2));  // false
        System.out.println(randomizedSet.insert(2));  // true
        System.out.println(randomizedSet.getRandom()); // 1 or 2
        System.out.println(randomizedSet.remove(1));  // true
        System.out.println(randomizedSet.insert(2));  // false
        System.out.println(randomizedSet.getRandom()); // 2
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

