package leetcode.array;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/#/description
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and
 * others appear once.
 * <p>
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not
 * count as extra space.
 * <p>
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 *
 * Created by venkatamunnangi on 5/15/17.
 */
public class AllNumbersDisappeared {

    //o(n) o(1) space
    public List<Integer> findDisappearedNumbers(int... nums) {
        List<Integer> ret = new ArrayList<>();

        // the goal is to leverage the indices. if you encounter a number, then go to that
        // array index and mark its value as -1*val
        for(int i = 0; i < nums.length; i++) {
            // transforming into an array index
            int val = Math.abs(nums[i]) - 1;

            // assigning a visited flag
            if(nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }

        for(int i = 0; i < nums.length; i++) {
            // if not encountered a visited flag.
            if(nums[i] > 0) {
                ret.add(i+1);
            }
        }
        return ret;
    }

    public static void main(String... args) {
        AllNumbersDisappeared allNumbersDisappeared = new AllNumbersDisappeared();

        out.println(allNumbersDisappeared.findDisappearedNumbers(4,3,2,7,8,2,3,1));
        out.println(allNumbersDisappeared.findDisappearedNumbers(1));

    }
}
