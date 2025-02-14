package leetcode.string;

import java.util.Map;

/**
 * Created by venkatamunnangi on 11/29/16.
 */
public class Roman2Integer {

    // Define a map for the basic Roman numeral values
    private static final Map<Character, Integer> ROMAN_VALUES = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1_000
    );

    public int romanToInt(String s) {
        int result = 0;
        int previousValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);
            int currentValue = ROMAN_VALUES.getOrDefault(currentChar, 0);

            // Subtract if smaller value precedes a larger value (e.g., IV = 4)
            if (currentValue < previousValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            previousValue = currentValue;
        }

        return result;
    }
}
