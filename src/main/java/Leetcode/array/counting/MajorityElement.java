package Leetcode.array.counting;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/majority-element/#/description
 *
 * Created by venkatamunnangi on 19/11/16.
 */
public class MajorityElement {
    HashMap<Integer, Integer> hm = new HashMap<>();

    public int majorityElement(int[] nums) {
        int target = nums.length / 2;

        for (int n : nums) {
            if (!hm.containsKey(n)) {
                hm.put(n, 1);
            } else {
                hm.put(n, hm.get(n) + 1);
            }
            int count = hm.get(n);
            if (count > target) {
                return n;
            }
        }
        return -1;
    }

    //faster algorithm
    public int majorityElement2(int... nums) {
        int majorElem = nums[0], count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                majorElem = nums[i];
            } else if (majorElem == nums[i]) {
                count++;
            } else {
                count--;
            }
        }

        return majorElem;
    }

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
//        System.out.println(majorityElement.majorityElement2(3,3,3,4,4,4,4));
        System.out.println(majorityElement.majorityElement2(3, 4, 3, 4, 3, 4, 3));

    }


}
