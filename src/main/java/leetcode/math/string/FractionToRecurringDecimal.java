package leetcode.math.string;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/fraction-to-recurring-decimal/description/
public class FractionToRecurringDecimal {
    /**
     * Convert a fraction (numerator/denominator) to its decimal representation.
     * If the fractional part is repeating, enclose the repeating part in parentheses.
     *
     * @param numerator   the numerator of the fraction
     * @param denominator the denominator of the fraction
     * @return the decimal representation of the fraction
     */
    public String fractionToDecimal(int numerator, int denominator) {
        // Base case: 0 divided by any number is 0.
        if (numerator == 0) {
            return "0";
        }

        StringBuilder decimalRepresentation = new StringBuilder();

        // Handle the sign for negative numbers.
        if (numerator < 0 ^ denominator < 0) {
            decimalRepresentation.append("-");
        }

        // Convert to Long to handle overflows and calculate absolute values.
        long absoluteNumerator = Math.abs((long) numerator);
        long absoluteDenominator = Math.abs((long) denominator);

        // Append the integer part.
        decimalRepresentation.append(absoluteNumerator / absoluteDenominator);
        long remainder = absoluteNumerator % absoluteDenominator;

        // If there's no fractional part, return.
        if (remainder == 0) {
            return decimalRepresentation.toString();
        }

        decimalRepresentation.append(".");
        Map<Long, Integer> seenRemainders = new HashMap<>();

        // Handle the fractional part.
        while (remainder != 0) {
            // If we've seen this remainder before, it's a repeating fraction.
            if (seenRemainders.containsKey(remainder)) {
                decimalRepresentation.insert(seenRemainders.get(remainder), "(");
                decimalRepresentation.append(")");
                break;
            }

            // Mark the current position for this remainder.
            seenRemainders.put(remainder, decimalRepresentation.length());
            remainder *= 10;

            // Append the next digit.
            decimalRepresentation.append(remainder / absoluteDenominator);
            remainder %= absoluteDenominator;
        }

        return decimalRepresentation.toString();
    }

    public static void main(String[] args) {
        FractionToRecurringDecimal solution = new FractionToRecurringDecimal();

        // Test some sample fractions
        int[][] testCases = {
                {10000000, 3},
                {2, 1},
                {2, 3},
                {4, 333},
                {1, 5},
                {-2, 3},
                {-2, -3},
                {0, 5},
                {7, -12}
        };

        for (int[] testCase : testCases) {
            String result = solution.fractionToDecimal(testCase[0], testCase[1]);
            System.out.println(testCase[0] + "/" + testCase[1] + " = " + result);
        }
    }

}
