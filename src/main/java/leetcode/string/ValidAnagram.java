package leetcode.string;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/valid-anagram/#/description
 *
 * Created by venkatamunnangi on 4/2/17.
 */
public class ValidAnagram {
    public boolean isAnagramUnicode1(String s, String t) {
        if(s.length()!=t.length())
            return false;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0; i<s.length(); i++){
            char c1 = s.charAt(i);
            if(map.containsKey(c1)){
                map.put(c1, map.get(c1)+1);
            }else{
                map.put(c1,1);
            }
        }
        for(int i=0; i<s.length(); i++){
            char c2 = t.charAt(i);
            if(map.containsKey(c2)){
                if(map.get(c2)==1){
                    map.remove(c2);
                }else{
                    map.put(c2, map.get(c2)-1);
                } }else{
                return false;
            }
        }
        if(map.size()>0)
            return false;
        return true;
    }

    public boolean isAnagramUnicode2(String s, String t) {
        if(s == null || t == null) {
            return false;
        }

        if(s.length() != t.length()) {
            return false;
        }

        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        String s1 = String.valueOf(c1);
        String s2 = String.valueOf(c2);

        return s1.equals(s2);
    }

    public boolean validAanagram2(String s, String t) {
        if(s == null || t == null) {
            return false;
        }

        if(s.length() != t.length()) {
            return false;
        }

        int[] freq = new int[26];

        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for(char c : t.toCharArray()) {
            freq[c - 'a']--;
            if(freq[c - 'a'] < 0) {
                return false;
            }
        }

        for(int i : freq) {
            if( i != 0) {
                return false;
            }
        }

        return true;
    }
}
