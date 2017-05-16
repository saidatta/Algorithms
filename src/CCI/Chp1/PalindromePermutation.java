package CCI.Chp1;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/palindrome-permutation/#/description
 *
 * Created by venkatamunnangi on 1/23/17.
 */
public class PalindromePermutation {
    public boolean isPalindromePermutation(String s) {
        Set<Character> set=new HashSet<>();
        for(int i=0; i<s.length(); ++i){
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else
                set.remove(s.charAt(i));
        }
        return set.isEmpty() || set.size()==1;
    }
}
