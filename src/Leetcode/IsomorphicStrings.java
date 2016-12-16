package Leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 205
 * Created by venkatamunnangi on 12/16/16.
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Object, Integer> doubleDictionary = new HashMap<>();
        for (Integer i=0; i<s.length(); i++) {
            // Here, put will return a previous value of that key.
            // If there is no previous occurrence, then it returns a null value.
            if (doubleDictionary.put(s.charAt(i), i) != doubleDictionary.put(t.charAt(i) + "", i))
                return false;
        }
        return true;
    }
}
