package Int;

import java.util.Arrays;

/**
 * Created by venkatamunnangi on 02/11/16.
 */
public class CheckPermutations {

    public boolean checkPermutation(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        return sort(s1).equals(sort(s2));
    }

    public String sort(String s) {
        char [] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    public boolean checkPermutation2(String s1, String s2) {
        if(s1.length() != s2.length()) {
            return false;
        }

        int [] characters = new int[128];

        for(char c : s1.toCharArray()) {
            characters[c - 'a']++;
        }
        for(char c : s2.toCharArray()) {
            characters[c - 'a']--;
        }


        for(int x : characters) {
            if(x > 0) {
                return false;
            }
        }

        return true;
    }
}
