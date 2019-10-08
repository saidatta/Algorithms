package Leetcode.Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by venkatamunnangi on 9/10/19.
 */
public class WordPatternII {

    Map<Character, String> map = new HashMap<>();
    Set<String> set = new HashSet<>();

    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.isEmpty()) {
            return str.isEmpty();
        }
        
        if (map.containsKey(pattern.charAt(0))) {
            String value = map.get(pattern.charAt(0));
            if (str.length() < value.length() || !str.substring(0, value.length()).equals(value)) {
                return false;
            }
            if (wordPatternMatch(pattern.substring(1), str.substring(value.length()))) {
                return true;
            }
        } else {
            for (int i = 1; i <= str.length(); i++) {
                if (set.contains(str.substring(0, i))) {
                    continue;
                }
                map.put(pattern.charAt(0), str.substring(0, i));
                set.add(str.substring(0, i));
                if (wordPatternMatch(pattern.substring(1), str.substring(i))) {
                    return true;
                }
                set.remove(str.substring(0, i));
                map.remove(pattern.charAt(0));
            }
        }
        return false;
    }

    private Map<Character, String> map1 = new HashMap<>();
    private Map<String, Character> map2 = new HashMap<>();

    public boolean wordPatternMatchCache(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        return dfs(pattern, str, 0, 0);
    }


    private boolean dfs(String pattern, String str, int start0, int start1) {
        if (start0 == pattern.length() && start1 == str.length()) {
            return true;
        } else if (start0 == pattern.length() || start1 == str.length()) {
            return false;
        } else {
            if (map1.containsKey(pattern.charAt(start0))) {
                String go = map1.get(pattern.charAt(start0));
                if (!str.startsWith(go, start1)) {
                    return false;
                } else {
                    return dfs(pattern, str, start0 + 1, start1 + go.length());
                }
            }
            for (int i = start1; i < str.length(); i++) {
                String temp = str.substring(start1, i + 1);
                if (str.length() - i < pattern.length() - start0) {
                    break;
                }
                if (map2.containsKey(temp)) {
                    continue;
                } else {
                    map1.put(pattern.charAt(start0), temp);
                    map2.put(temp, pattern.charAt(start0));
                    if (dfs(pattern, str, start0 + 1, i + 1)) {
                        return true;
                    } else {
                        map1.remove(pattern.charAt(start0));
                        map2.remove(temp);
                    }
                }
            }
            return false;
        }
    }


}
