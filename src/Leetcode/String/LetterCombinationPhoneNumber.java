package Leetcode.String;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by venkatamunnangi on 12/2/16.
 */
public class LetterCombinationPhoneNumber {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> q = new LinkedList<>();
        q.add("");

        String [] mappings = new String[] {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

        for(int i = 0; i < digits.length();i++) {
            int numberRepresentation = Character.getNumericValue(digits.charAt(i));

            while(q.peek().length() == i) {
                String temp = q.remove();
                //while(){
                for (int j = 0; j < mappings[numberRepresentation].length(); j++) {
                    q.add(temp + mappings[numberRepresentation].charAt(j));
                }
                //}
            }
        }

        if(q.size() == 1 && q.peek().length() == 1) {
            q.remove();
        }

        return q;
    }
}
