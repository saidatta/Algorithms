package Leetcode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * <p>
 * Created by venkatamunnangi on 9/12/19.
 */
public class KSmallerSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> ret = new ArrayList<int[]>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return ret;
        }

        //index2 is used for recording position in nums2 corresponding to given position in nums1
        int[] index2 = new int[nums1.length];
        while (k-- > 0) {
            int min = Integer.MAX_VALUE;
            //every time we should start from the first place in nums2 to find proper position
            int index = -1;
            for (int index1 = 0; index1 < nums1.length; index1++) {
                if (index2[index1] >= nums2.length) {
                    continue;
                }

                if (nums1[index1] + nums2[index2[index1]] < min) {
                    min = nums1[index1] + nums2[index2[index1]];
                    //keep record the index in nums1
                    index = index1;
                }
            }
            if (index == -1) {
                break;
            }

            int[] temp = {nums1[index], nums2[index2[index]]};
            ret.add(temp);
            index2[index]++;
        }
        return ret;
    }

    /**
     * Basic idea: Use min_heap to keep track on next minimum pair sum, and we only need to maintain K possible
     * candidates in the data structure.
     * <p>
     * Some observations: For every numbers in nums1, its best partner(yields min sum) always strats from nums2[0] since
     * arrays are all sorted; And for a specific number in nums1, its next candidate sould be [this specific number] +
     * nums2[current_associated_index + 1], unless out of boundary;)
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */

    //The run time complexity is O(kLogk) since que.size <= k and we do at most k loop.
    public List<List<Integer>> kSmallestPairsPQ(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> kSmallestSums = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        List<List<Integer>> res = new ArrayList<>();
        int targetPairs = k;

        if (nums1.length == 0 || nums2.length == 0 || targetPairs == 0) {
            return res;
        }

        for (int i = 0; i < nums1.length && i < targetPairs; i++) {
            kSmallestSums.offer(new int[]{nums1[i], nums2[0], 0});
        }

        while (targetPairs-- > 0 && !kSmallestSums.isEmpty()) {
            int[] cur = kSmallestSums.poll();
            res.add(new ArrayList<>(Arrays.asList(cur[0], cur[1])));
            if (cur[2] == nums2.length - 1) {
                // make sure we dont encounter out of bounds for second array.
                continue;
            }
            kSmallestSums.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
        }


        return res;
    }
}
