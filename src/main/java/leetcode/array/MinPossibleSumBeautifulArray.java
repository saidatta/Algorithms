package leetcode.array;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/find-the-minimum-possible-sum-of-a-beautiful-array/description/
public class MinPossibleSumBeautifulArray {
    public static long minimumPossibleSum(int n, int target) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return target == 3 ? 4L : 3L;
        }

        Set<Integer> nums = new HashSet<>();
        int i = 1;
        while (nums.size() < n) {
            if (!nums.contains(target - i)) {
                nums.add(i);
            }
            i++;
        }

        return nums.stream().mapToLong(Integer::longValue).sum();
    }

    public static void main(String [] args) {
        System.out.println(minimumPossibleSum(2,3));
    }
}
