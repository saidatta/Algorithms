package leetcode.dp;

/**
 * https://leetcode.com/problems/wildcard-matching/#/description
 *
 *
 * Created by venkatamunnangi on 4/19/17.
 */
public class WildCardPattern {
    boolean comparison(String str, String pattern) {
        int currentStringPtr = 0, currentPatternPtr = 0, matchedStringPtr = 0, starIndex = -1;
        while (currentStringPtr < str.length()){
            // advancing both pointers
            if (currentPatternPtr < pattern.length()  && (pattern.charAt(currentPatternPtr) == '?' || str.charAt(currentStringPtr) == pattern.charAt(currentPatternPtr))){
                currentStringPtr++;
                currentPatternPtr++;
            }
            // * found, only advancing pattern pointer
            else if (currentPatternPtr < pattern.length() && pattern.charAt(currentPatternPtr) == '*'){
                starIndex = currentPatternPtr;
                matchedStringPtr = currentStringPtr;
                currentPatternPtr++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIndex != -1){
                currentPatternPtr = starIndex + 1;
                matchedStringPtr++;
                currentStringPtr = matchedStringPtr;
            }
            //current pattern pointer is not star, last pattern pointer was not *
            //characters do not matchedStringPtr
            else return false;
        }

        //check for remaining characters in pattern
        while (currentPatternPtr < pattern.length() && pattern.charAt(currentPatternPtr) == '*') {
            currentPatternPtr++;
        }

        return currentPatternPtr == pattern.length();
    }
}
