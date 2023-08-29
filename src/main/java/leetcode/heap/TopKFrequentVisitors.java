package leetcode.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 */
public class TopKFrequentVisitors {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freqCount = new HashMap<>();
        for (int n : nums) {
            freqCount.put(n, freqCount.getOrDefault(n,0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (Map.Entry<Integer, Integer> eachFreqCount : freqCount.entrySet()) {
            pq.add(new int[]{eachFreqCount.getKey(), eachFreqCount.getValue()});

            while (pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll()[0];
        }

        return result;
    }

    public static void main(String[] args) {
        TopKFrequentVisitors obj = new TopKFrequentVisitors();
        int[] nums = {1,1,1,2,2,3,4};
        int k = 2;
        int[] result = obj.topKFrequent(nums, k);

        System.out.println("Top " + k + " frequent numbers are: ");
        for(int i : result){
            System.out.println(i);
        }
    }

}
