package Leetcode;

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
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    List<String> helper(int current, int m) {
        if (current == 0) {
            return new ArrayList<>(Arrays.asList(""));
        }
        if (current == 1) {
            return new ArrayList<>(Arrays.asList("0", "1", "8"));
        }

        List<String> list = helper(current - 2, m);

        List<String> res = new ArrayList<>();

        for (String s : list) {
            if (current != m) {
                res.add("0" + s + "0");
            }

            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }

        return res;
    }

    public static void main(String[] args) {
        StrobogrammaticNumberII strobogrammaticNumberII = new StrobogrammaticNumberII();

        System.out.println(strobogrammaticNumberII.findStrobogrammatic(2));
    }
}
