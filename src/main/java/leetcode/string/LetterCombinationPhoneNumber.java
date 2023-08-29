package leetcode.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.*;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/#/description
 *
 * Created by venkatamunnangi on 12/2/16.
 */
public class LetterCombinationPhoneNumber {

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        LinkedList<String> list = new LinkedList<>();
        list.add("");

        String [] mappings = new String[] {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

        for(int i = 0; i < digits.length(); i++) {
            int numberRepresentation = Character.getNumericValue(digits.charAt(i));

            while(list.peek().length() == i) {
                String temp = list.remove();
                for (int j = 0; j < mappings[numberRepresentation].length(); j++) {
                    list.add(temp + mappings[numberRepresentation].charAt(j));
                }
            }
        }

        return list;
    }

    public static void main(String [] args) {
        LetterCombinationPhoneNumber letterCombinationPhoneNumber = new LetterCombinationPhoneNumber();
        out.println(letterCombinationPhoneNumber.letterCombinations("23"));
        out.println(letterCombinationPhoneNumber.letterCombinations("1"));
    }
}
