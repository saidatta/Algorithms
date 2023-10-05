package leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/strobogrammatic-number-ii/#/description
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 *
 * Created by venkatamunnangi on 5/14/17.
 */
public class StrobogrammaticNumberII {

    private static final List<String> ZERO_AND_ONE_DIGIT = List.of("0", "1", "8");
    private static final List<String> TWO_DIGIT_STARTERS = List.of("11","69","88","96");

    public List<String> findStrobogrammatic(int n) {
        if (n == 0) return List.of("");
        if (n == 1) return ZERO_AND_ONE_DIGIT;
        if (n == 2) return TWO_DIGIT_STARTERS;

        return constructNumbers(n, n);
    }

    private List<String> constructNumbers(int current, int max) {
        if (current == 0) {
            return List.of("");
        }
        if (current == 1) {
            return ZERO_AND_ONE_DIGIT;
        }

        List<String> smallerNumbers = constructNumbers(current - 2, max);
        List<String> results = new ArrayList<>();

        for (String number : smallerNumbers) {
            if (current != max) {
                results.add("0" + number + "0");
            }

            results.add("1" + number + "1");
            results.add("6" + number + "9");
            results.add("8" + number + "8");
            results.add("9" + number + "6");
        }

        return results;
    }

    public static void main(String[] args) {
        StrobogrammaticNumberII finder = new StrobogrammaticNumberII();
        System.out.println(finder.findStrobogrammatic(2));
    }
}
