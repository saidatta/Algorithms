package leetcode.design;

import java.util.*;

// Define an interface for number validation
public class ValidNumber {

    // Enumeration to identify character groups.
    private enum CharGroup {
        DIGIT, SIGN, DOT, EXPONENT, INVALID
    }

    // Deterministic finite automaton (DFA) design for numeric string validation.
    private static final List<Map<CharGroup, Integer>> DFA = List.of(
            Map.of(CharGroup.DIGIT, 1, CharGroup.SIGN, 2, CharGroup.DOT, 3),
            Map.of(CharGroup.DIGIT, 1, CharGroup.DOT, 4, CharGroup.EXPONENT, 5),
            Map.of(CharGroup.DIGIT, 1, CharGroup.DOT, 3),
            Map.of(CharGroup.DIGIT, 4),
            Map.of(CharGroup.DIGIT, 4, CharGroup.EXPONENT, 5),
            Map.of(CharGroup.SIGN, 6, CharGroup.DIGIT, 7),
            Map.of(CharGroup.DIGIT, 7),
            Map.of(CharGroup.DIGIT, 7)
    );

    // Valid final states for the DFA.
    private static final Set<Integer> VALID_FINAL_STATES = Set.of(1, 4, 7);

    public boolean isNumber(String s) {
        int currentState = 0;

        for (int i = 0; i < s.length(); i++) {
            CharGroup group = identifyCharGroup(s.charAt(i));

            if (group == CharGroup.INVALID || !DFA.get(currentState).containsKey(group)) {
                return false;
            }

            currentState = DFA.get(currentState).get(group);
        }

        return VALID_FINAL_STATES.contains(currentState);
    }

    private CharGroup identifyCharGroup(char c) {
        return switch (c) {
            case '+', '-' -> CharGroup.SIGN;
            case 'e', 'E' -> CharGroup.EXPONENT;
            case '.' -> CharGroup.DOT;
            default -> Character.isDigit(c) ? CharGroup.DIGIT : CharGroup.INVALID;
        };
    }

    public static void main(String[] args) {
        ValidNumber solution = new ValidNumber();
        System.out.println(solution.isNumber("123"));       // true
        System.out.println(solution.isNumber(".1"));        // true
        System.out.println(solution.isNumber("1e5"));       // true
        System.out.println(solution.isNumber("1e"));        // false
        System.out.println(solution.isNumber("e5"));        // false
        System.out.println(solution.isNumber("1.5e5"));     // true
        System.out.println(solution.isNumber("1.5e5.2"));   // false
        System.out.println(solution.isNumber("abc"));       // false
    }
}
