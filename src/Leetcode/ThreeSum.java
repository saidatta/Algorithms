package Leetcode;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by venkatamunnangi on 9/25/16.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> ll = new ArrayList<>();
        for(int i :nums) {
            ll.add(i);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0;i<nums.length;i++) {
            for(int j = i+1; j< nums.length;j++) {

                int k = -(nums[i] + nums[j]);
                if(ll.contains(k)) {
                    List<Integer> temp = makeTriplet(k, nums[i], nums[j]);
                    if(!isPresent(result, temp) && checkToSureAllItemsExist(temp, ll)) {
                        // only unique
                        result.add(temp);
                    }

                }
            }
        }
        return result;
    }

    private boolean checkToSureAllItemsExist(List<Integer> curr, List<Integer> dict) {
        List<Integer> temp =new ArrayList<>(dict);
        for(int i : curr) {
            if(!temp.contains(i)) {
                return false;
            }
            temp.remove((Integer)i);
        }
        return true;
    }

    boolean isPresent(List<List<Integer>> result,List<Integer> temp) {
        for(List<Integer> curr: result) {

            if(temp.containsAll(curr)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> makeTriplet(int a, int b, int c) {
        List<Integer> all = new ArrayList<>();
        all.add(a);
        all.add(b);
        all.add(c);
        return all;
    }
}
