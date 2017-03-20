package Leetcode.String;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/#/description
 *
 * Created by venkatamunnangi on 12/2/16.
 */
public class LetterCombinationPhoneNumber {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> q = new LinkedList<>();
        q.add("");

        String [] mappings = new String[] {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

        for(int i = 0; i < digits.length(); i++) {
            int numberRepresentation = Character.getNumericValue(digits.charAt(i));

            while(q.peek().length() == i) {
                String temp = q.remove();
                for (int j = 0; j < mappings[numberRepresentation].length(); j++) {
                    q.add(temp + mappings[numberRepresentation].charAt(j));
                }
            }
        }

        if(q.size() == 1 && q.peek().length() == 1) {
            q.remove();
        }

        return q;
    }

    public static void main(String [] args) {
        LetterCombinationPhoneNumber letterCombinationPhoneNumber = new LetterCombinationPhoneNumber();
        System.out.println(letterCombinationPhoneNumber.letterCombinations("23"));
    }
}
