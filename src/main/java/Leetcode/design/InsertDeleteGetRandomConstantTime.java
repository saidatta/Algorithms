package Leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/#/description
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom(): Returns a random element from current set of elements.
 *              Each element must have the same probability of being returned.
 *
 * Created by venkatamunnangi on 4/2/17.
 */
public class InsertDeleteGetRandomConstantTime {
    ArrayList<Integer> nums;
    HashMap<Integer, Integer> dict;
    java.util.Random rand = new java.util.Random();
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomConstantTime() {
        nums = new ArrayList<>();
        dict = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if ( dict.containsKey(val) ) {
            return false;
        }
        int indexForVal = nums.size();
        dict.put(val, indexForVal);
        nums.add(val);

        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if ( ! dict.containsKey(val) ) {
            return false;
        }
        int loc = dict.get(val);
        if (loc < nums.size() - 1 ) {
            // not the last one than swap the last one with this val
            // For o(1) runtime.
            int lastone = nums.get(nums.size() - 1 );
            nums.set( loc , lastone );
            dict.put(lastone, loc);
        }
        dict.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return nums.get( rand.nextInt(nums.size()) );
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

