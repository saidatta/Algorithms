package Leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/word-pattern-ii/
 *
 * Created by venkatamunnangi on 9/10/19.
 */
public class WordPatternII {

    Map<Character, String> patternCacheMap = new HashMap<>();
    Set<String> duplicateCheck = new HashSet<>();


    public static void main(String [] args) {
        WordPatternII wordPatternII = new WordPatternII();
        System.out.println(wordPatternII.wordPatternMatch("abab", "redblueredblue"));
    }
    //Input: pattern = "abab", s = "redblueredblue"
    public boolean wordPatternMatch(String pattern, String givenString) {
        if (pattern.isEmpty()) {
            return givenString.isEmpty();
        }
        
        if (patternCacheMap.containsKey(pattern.charAt(0))) {
            String value = patternCacheMap.get(pattern.charAt(0));
            if (givenString.length() < value.length() || !givenString.startsWith(value)) {
                return false;
            }
            return wordPatternMatch(pattern.substring(1), givenString.substring(value.length()));
        } else {
            for (int i = 1; i <= givenString.length(); i++) {
                if (duplicateCheck.contains(givenString.substring(0, i))) {
                    continue;
                }
                patternCacheMap.put(pattern.charAt(0), givenString.substring(0, i));
                duplicateCheck.add(givenString.substring(0, i));
                if (wordPatternMatch(pattern.substring(1), givenString.substring(i))) {
                    return true;
                }
                duplicateCheck.remove(givenString.substring(0, i));
                patternCacheMap.remove(pattern.charAt(0));
            }
        }
        return false;
    }

    private Map<Character, String> patternCache = new HashMap<>();
    private Map<String, Character> map2 = new HashMap<>();

    public boolean wordPatternMatchCache(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        return dfs(pattern, str, 0, 0);
    }


    private boolean dfs(String pattern, String givenString, int patternStart, int stringsStart) {
        if (patternStart == pattern.length() && stringsStart == givenString.length()) {
            return true;
        } else if (patternStart == pattern.length() || stringsStart == givenString.length()) {
            return false;
        } else {
            if (patternCache.containsKey(pattern.charAt(patternStart))) {
                String go = patternCache.get(pattern.charAt(patternStart));
                if (!givenString.startsWith(go, stringsStart)) {
                    return false;
                } else {
                    return dfs(pattern, givenString, patternStart + 1, stringsStart + go.length());
                }
            }
            for (int i = stringsStart; i < givenString.length(); i++) {
                String temp = givenString.substring(stringsStart, i + 1);
                if (givenString.length() - i < pattern.length() - patternStart) {
                    break;
                }
                if (!map2.containsKey(temp)) {
                    patternCache.put(pattern.charAt(patternStart), temp);
                    map2.put(temp, pattern.charAt(patternStart));
                    if (dfs(pattern, givenString, patternStart + 1, i + 1)) {
                        return true;
                    } else {
                        patternCache.remove(pattern.charAt(patternStart));
                        map2.remove(temp);
                    }
                }
            }
            return false;
        }
    }


}
