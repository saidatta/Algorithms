package Leetcode;

import java.util.*;

/**
 * https://leetcode.com/problems/anagrams/#/description
 *
 * Created by venkatamunnangi on 4/2/17.
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strings) {
        if(strings == null || strings.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(strings);
        Map<String, List<String>> dict = new HashMap<>();


        for(String currentString : strings) {
            char[] ch = currentString.toCharArray();
            Arrays.sort(ch);
            String s = String.valueOf(ch);

            if(!dict.containsKey(s)) {
                dict.put(s, new ArrayList<>());
            }
            dict.get(s).add(currentString);
        }
        return new ArrayList<>(dict.values());
    }
}
