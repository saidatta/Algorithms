package Leetcode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/group-shifted-strings/#/description
 *
 *
 * Created by venkatamunnangi on 5/21/17.
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        Arrays.sort(strings);
        for (String s : strings) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < s.length(); i++) {
                //Difference from the previous char.
                sb.append(String.format("%2d", (s.charAt(i) - s.charAt(i - 1) + 26) % 26));
            }
            if (!map.containsKey(sb.toString())) {
                map.put(sb.toString(), new ArrayList<>());
            }
            map.get(sb.toString()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
