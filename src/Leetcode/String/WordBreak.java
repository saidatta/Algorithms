package Leetcode.String;

import java.util.Set;

/**
 * Created by venkatamunnangi on 10/1/16.
 */
public class WordBreak {
    public boolean wordBreak(String s, Set<String> wordDict) {

        if(wordDict == null || s == null || s.isEmpty()|| wordDict.isEmpty()) {
            return false;
        }
        boolean [] positions = new boolean[s.length() + 1];
        positions[0] = true;

        for(int i = 0 ;i<s.length();i++) {

            if(!positions[i]) {
                continue;
            }

            for(String word : wordDict) {
                int wordLength = word.length();
                int wordEnd = i + wordLength;

                if(wordEnd > s.length()) {
                    continue;
                }

                if(s.substring(i,wordEnd).equals(word)) {
                    positions[wordEnd] = true;
                }
            }

        }
        return positions[s.length()];
    }
}