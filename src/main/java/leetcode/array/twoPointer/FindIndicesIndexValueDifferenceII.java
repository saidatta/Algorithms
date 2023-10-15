package leetcode.array.twoPointer;

import java.util.*;

public class  FindIndicesIndexValueDifferenceII {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i >= indexDifference) {
                if (!map.isEmpty()) {
                    // Check max and min values in the map
                    int max = map.lastKey();
                    int min = map.firstKey();

                    if (max - min >= valueDifference) {
                        for (int j = i - indexDifference; j < i; j++) {
                            if (Math.abs(nums[j] - nums[i]) >= valueDifference) {
                                return new int[]{j, i};
                            }
                        }
                    }
                }

                // Remove leftmost value from map
                int leftVal = nums[i - indexDifference];
                if (map.containsKey(leftVal)) {
                    if (map.get(leftVal) == 1) {
                        map.remove(leftVal);
                    } else {
                        map.put(leftVal, map.get(leftVal) - 1);
                    }
                }
            }

            // Add new value to the map
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        FindIndicesIndexValueDifferenceII solution = new FindIndicesIndexValueDifferenceII();
        int[] nums1 = {5,1,4,1};
        int[] result1 = solution.findIndices(nums1, 2, 4);
        System.out.println(Arrays.toString(result1));  // Expected: [0, 3] or [3, 0]

        int[] nums2 = {2,1};
        int[] result2 = solution.findIndices(nums2, 0, 0);
        System.out.println(Arrays.toString(result2));  // Expected: [0, 0] or [0, 1] or [1, 0] or [1, 1]

        int[] nums3 = {1,2,3};
        int[] result3 = solution.findIndices(nums3, 2, 4);
        System.out.println(Arrays.toString(result3));  // Expected: [-1, -1]
    }
}

