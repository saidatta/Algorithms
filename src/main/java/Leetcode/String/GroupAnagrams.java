package Leetcode.String;

import java.util.*;

/**
 * https://leetcode.com/problems/anagrams/#/description
 *
 * Created by venkatamunnangi on 12/2/16.
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        Arrays.sort(strs);
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<>());
            }
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }


    public List<List<String>> gA2(String... strs) {
        List<List<String>> result = new ArrayList<>();

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for(String str: strs){
            char[] freqMatch = new char[26];
            for(int i=0; i < str.length(); i++){
                freqMatch[str.charAt(i)-'a']++;
            }
                String ns = new String(freqMatch);

            if(map.containsKey(ns)){
                map.get(ns).add(str);
            }else{
                ArrayList<String> al = new ArrayList<>();
                al.add(str);
                map.put(ns, al);
            }
        }

        result.addAll(map.values());

        return result;
    }

    public static void main(String [] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();

        System.out.println(groupAnagrams.gA2("eat", "tea", "tan", "ate", "nat", "bat"));
    }
}
