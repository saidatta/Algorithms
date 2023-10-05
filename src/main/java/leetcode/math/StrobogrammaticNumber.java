package leetcode.math;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/strobogrammatic-number/description/
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        int left = 0, right = num.length() - 1;

        while (left <= right) {
            if (!map.containsKey(num.charAt(left)) || map.get(num.charAt(left)) != num.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        StrobogrammaticNumber solution = new StrobogrammaticNumber();

        System.out.println(solution.isStrobogrammatic("69"));   // true
        System.out.println(solution.isStrobogrammatic("88"));   // true
        System.out.println(solution.isStrobogrammatic("962"));  // false
    }
}
