package Leetcode;

import java.util.*;

/**
 * LC 170
 * The trade off is that constant time addition but O(n) in find.
 * Created by venkatamunnangi on 12/15/16.
 */
public class TwoSum3 {
    List<Integer> distinctNumbers = new ArrayList<>();
    Map<Integer, Integer> dict = new HashMap<>();

    // Add the number to an internal data structure.
    public void add(int number) {
        if(dict.containsKey(number)) {
            dict.put(number, dict.get(number) +1);
        } else {
            dict.put(number, 1);
            distinctNumbers.add(number);
        }
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for(int a : distinctNumbers) {
            int b = value -a;
            int count = (b == a) ? 1 : 0;
            if(dict.get(b) != null && dict.get(b) > count) {
                return true;
            }
        }
        return false;
    }
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);