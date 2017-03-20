package Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.System.*;

/**
 * LC 205
 * https://leetcode.com/problems/isomorphic-strings/#/description
 *
 * Created by venkatamunnangi on 12/16/16.
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Object, Integer> doubleDictionary = new HashMap<>();
        for (Integer i=0; i<s.length(); i++) {
            // Here, put will return a previous value of that key.
            // If there is no previous occurrence, then it returns a null value.
            Integer sValue = doubleDictionary.put(s.charAt(i), i);
            Integer tValue = doubleDictionary.put(Character.toString(t.charAt(i)), i);
            if (!Objects.equals(sValue, tValue)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String [] args) {
        IsomorphicStrings isomorphicStrings = new IsomorphicStrings();
        out.println(isomorphicStrings.isIsomorphic("egg", "add"));
        out.println(isomorphicStrings.isIsomorphic("foo", "bar"));
        out.println(isomorphicStrings.isIsomorphic("paper", "title"));
    }
}
