package Leetcode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-pairs/#/description
 *
 * Given words = ["bat", "tab", "cat"] Return [[0, 1], [1, 0]] The palindromes are ["battab", "tabbat"]
 *
 * Case1: If s1 is a blank string, then for any string that is palindrome s2, s1+s2 and s2+s1 are palindrome.
 *
 * Case 2: If s2 is the reversing string of s1, then s1+s2 and s2+s1 are palindrome.
 *
 * Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:] , then s2+s1 is palindrome.
 *
 * Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.
 *
 * To make the search faster, build a HashMap to store the String-idx pairs.
 *
 * Created by venkatamunnangi on 4/5/17.
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if(words == null || words.length == 0){
            return res;
        }
        //build the map save the key-val pairs: String - idx
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            map.put(words[i], i);
        }

        //special cases: "" can be combine with any palindrome string
        if(map.containsKey("")){
            int blankIdx = map.get("");
            for(int i = 0; i < words.length; i++){
                if(isPalindrome(words[i])){
                    // not the empty space by itself.
                    if(i == blankIdx) {
                        continue;
                    }
                    res.add(Arrays.asList(blankIdx, i));
                    res.add(Arrays.asList(i, blankIdx));
                }
            }
        }

        //find all string and reverse string pairs
        for(int i = 0; i < words.length; i++){
            String reverseStr = reverseStr(words[i]);
            if(map.containsKey(reverseStr)){
                int found = map.get(reverseStr);
                if(found == i) {
                    continue;
                }
                res.add(Arrays.asList(i, found));
            }
        }

        //find the pair s1, s2 that
        //case1 : s1[0:cut] is palindrome and s1[cut+1:] = reverse(s2) => (s2, s1)
        //case2 : s1[cut+1:] is palindrome and s1[0:cut] = reverse(s2) => (s1, s2)
        for(int i = 0; i < words.length; i++){
            String cur = words[i];
            for(int cut = 1; cut < cur.length(); cut++){
                if(isPalindrome(cur.substring(0, cut))){
                    String cut_r = reverseStr(cur.substring(cut));
                    if(map.containsKey(cut_r)){
                        int found = map.get(cut_r);
                        if(found == i) continue;
                        res.add(Arrays.asList(found, i));
                    }
                }

                if(isPalindrome(cur.substring(cut))){
                    String cut_r = reverseStr(cur.substring(0, cut));
                    if(map.containsKey(cut_r)){
                        int found = map.get(cut_r);
                        if(found == i) continue;
                        res.add(Arrays.asList(i, found));
                    }
                }
            }
        }

        return res;
    }

    public String reverseStr(String str){
        StringBuilder sb= new StringBuilder(str);
        return sb.reverse().toString();
    }

    private boolean isPalindrome(String word) {
        int n = word.length();
        for(int i = 0; i<=n/2; i++) {
            if(word.charAt(i) != word.charAt(n-i-1)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String [] args) {
        PalindromePairs palindromePairs = new PalindromePairs();
        System.out.println(palindromePairs.isPalindrome("aa"));
        System.out.println(palindromePairs.isPalindrome("aba"));
        System.out.println(palindromePairs.isPalindrome("abax"));
    }
}
