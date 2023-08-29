package leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.System.*;

/**
 * LC 205
 * https://leetcode.com/problems/isomorphic-strings/#/description
 * <p>
 * Created by venkatamunnangi on 12/16/16.
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Object, Integer> doubleDictionary = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
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

    // egg add
    public boolean isIsomorphic2(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];

        for (int i = s.length()-1; i > -1; i--) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) {
                return false;
            }

            m1[s.charAt(i)] = m2[t.charAt(i)] = i;
        }

        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings isomorphicStrings = new IsomorphicStrings();
        out.println(isomorphicStrings.isIsomorphic("egg", "add"));
        out.println(isomorphicStrings.isIsomorphic("foo", "bar"));
        out.println(isomorphicStrings.isIsomorphic("paper", "title"));
    }
}
