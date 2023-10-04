package leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This problem was asked by Lyft.

 Given a list of integers and a number K, return which contiguous elements of the list sum to K.

 For example, if the list is [1, 2, 3, 4, 5] and K is 9, then it should return [2, 3, 4], since 2 + 3 + 4 = 9.
 *
 */

public class FindContiguousSumK {
    public List<Integer> findContinuousK(int[] nums, int k) {
        Map<Integer, Integer> previous = new HashMap<>();

        List<Integer> res = new ArrayList<>();
        int sum = 0;
        previous.put(0, -1);
        for(int i = 0; i < nums.length; i++) {
            int current = nums[i];
            sum += current;
            previous.put(sum, i);
            Integer startIndex = previous.get(sum - k);
            if(startIndex != null) {
//               return
                for(int j = startIndex + 1; j < i+1; j++) {
                    res.add(nums[j]);
                }
                return res;
            }

        }

        return res;
    }

    public static void main(String [] args) {

        int[] nums = new int[]{1, 2, 3, 4, 5};
        FindContiguousSumK findContiguousSumK = new FindContiguousSumK();
        System.out.println(findContiguousSumK.findContinuousK(nums,7));
    }
}
