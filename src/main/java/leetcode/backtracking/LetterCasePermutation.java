package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/letter-case-permutation/
 *
 * Created by venkatamunnangi on 10/4/19.
 */
public class LetterCasePermutation {
        List<String> allWordPermutations = new ArrayList<>();
        public List<String> letterCasePermutation(String s) {
            helper(s.toCharArray(), 0);
            return allWordPermutations;
        }

//  add string to ans
//  go through the characters. If it is a letter check if it is lower case or upper case.
//  if lower case convert character to upper case and call recursive function
//  if upper case convert character to lower case and call recursive function
        public void helper(char[] str, int start){
            allWordPermutations.add(new String(str));
            for(int i=start; i<str.length; i++){
                if((str[i]>='a' && str[i]<='z') || (str[i]>='A' && str[i]<='Z')){
                    char ch = str[i];
                    if(ch>='A' && ch<='Z'){
                        str[i] = Character.toLowerCase(ch);
                        helper(str, i+1);
                        str[i] = ch;
                    }
                    else{
                        str[i] = Character.toUpperCase(ch);
                        helper(str, i+1);
                        str[i] = ch;
                    }
                }
            }
        }


        /// ------------------

    public interface PermutationGenerator {
        List<String> generatePermutations(String s);
    }

//    Single Responsibility Principle: Each method should have a single responsibility. In this case, the helper method
//    is responsible for both generating permutations and adding them to the list. We can separate these responsibilities
//    into different methods.

//    Open/Closed Principle: The code should be open for extension but closed for modification. This means that if we
//    want to add new functionality in the future, we should be able to do so without modifying the existing code. We
//    can achieve this by using interfaces and abstract classes.

//    Dependency Inversion Principle: High-level modules should not depend on low-level modules. Both should depend on
//    abstractions. In this case, the LetterCasePermutation class is directly dependent on the ArrayList class, which
//    is a low-level module. We can introduce an abstraction to remove this direct dependency.
    public class LetterCasePermutationII implements PermutationGenerator {

//     The LetterCasePermutation class implements PermutationGenerator interface. This way, if we want to add a
//    new way of generating permutations in the future, we can create a new class that implements the
//    PermutationGenerator interface without modifying the existing LetterCasePermutation class.
//
//    We've also separated the responsibility of adding permutations to the list into a separate method
//    generatePermutations. This method now takes the list as a parameter, so it's not directly dependent on the
//    ArrayList class anymore.
//
//    Finally, we've simplified the condition checks for whether a character is a letter and whether it's lower or upper
//    case by using the Character class's utility methods. This makes the code more readable and maintainable.
        @Override
        public List<String> generatePermutations(String s) {
            List<String> allWordPermutations = new ArrayList<>();
            generatePermutations(s.toCharArray(), 0, allWordPermutations);
            return allWordPermutations;
        }

        private void generatePermutations(char[] str, int start, List<String> allWordPermutations){
            allWordPermutations.add(new String(str));
            for(int i=start; i<str.length; i++){
                if(Character.isLetter(str[i])){
                    str[i] = Character.isLowerCase(str[i]) ? Character.toUpperCase(str[i]) : Character.toLowerCase(str[i]);
                    generatePermutations(str, i+1, allWordPermutations);
                    str[i] = Character.isLowerCase(str[i]) ? Character.toUpperCase(str[i]) : Character.toLowerCase(str[i]);
                }
            }
        }
    }
}
