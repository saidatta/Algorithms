package Leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/unique-word-abbreviation/#/description
 *
 * Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
 * A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
 *
 * Created by venkatamunnangi on 5/10/17.
 */
public class UniqueWordAbbrev {
    private String [] dict;
    private HashMap<String, HashSet<String>> abbrMap = new HashMap<>();
    public UniqueWordAbbrev(String... dictionary) {
        this.dict = dictionary;
        makeAbbrMap(dictionary);
    }

    public void makeAbbrMap(String [] dictionary) {
        //abbrMap
        for(String word : dictionary) {
            String ab = getAbbr(word);
            if(abbrMap.containsKey(ab)) {
                abbrMap.get(ab).add(word);
            } else {
                HashSet<String> al = new HashSet<>();
                al.add(word);
                this.abbrMap.put(ab, al);
            }
        }
    }

    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if(abbrMap.containsKey(abbr) && !abbrMap.get(abbr).contains(word)) {
            // if the word does not exist in the values.
            return false;
        }
        if(abbrMap.containsKey(abbr) && abbrMap.get(abbr).size() > 1) {
            // if word's abbr exists but there are more than 1 word with same abbr.
            return false;
        }
        return true;
    }

    private String getAbbr(String word) {
        if(word.isEmpty()) {
            return "";
        }

        return ""+word.charAt(0)+(word.length() -2)+word.charAt(word.length()-1);
    }

    public static void main(String... args) {
        UniqueWordAbbrev uniqueWordAbbrev = new UniqueWordAbbrev("deer","door","cake","card");
        System.out.println(uniqueWordAbbrev.isUnique("dear"));
        System.out.println(uniqueWordAbbrev.isUnique("door"));
        System.out.println(uniqueWordAbbrev.isUnique("cart"));
        System.out.println(uniqueWordAbbrev.isUnique("cake"));
    }
}
