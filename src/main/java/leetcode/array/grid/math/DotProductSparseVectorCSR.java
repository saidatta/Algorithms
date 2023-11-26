package leetcode.array.grid.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
public class DotProductSparseVectorCSR {
    private final List<Pair> pairs;

    public DotProductSparseVectorCSR(int[] nums) {
        this.pairs = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                pairs.add(new Pair(i, nums[i]));
            }
        }
    }

    /**
     * Computes the dot product of this sparse vector with another sparse vector.
     *
     * @param vec - Another sparse vector
     * @return - The dot product
     */
    public int dotProduct(DotProductSparseVectorCSR vec) {
        int result = 0;
        int p = 0, q = 0;

        while (p < pairs.size() && q < vec.pairs.size()) {
            Pair pair1 = pairs.get(p);
            Pair pair2 = vec.pairs.get(q);

            if (pair1.index == pair2.index) {
                result += pair1.value * pair2.value;
                p++;
                q++;
            } else if (pair1.index > pair2.index) {
                q++;
            } else {
                p++;
            }
        }

        return result;
    }
    // follow-up
    public static class SparseVectorBS {
        private final List<Pair> nonZeroList;

        public SparseVectorBS(List<Integer> nums) {
            this.nonZeroList = new ArrayList<>();
            for (int nIdx = 0; nIdx < nums.size(); nIdx++) {
                if (nums.get(nIdx) != 0) {
                    this.nonZeroList.add(new Pair(nIdx, nums.get(nIdx)));
                }
            }
        }

        private int dotProduct(SparseVectorBS vec1, SparseVectorBS vec2) {
            int res = 0;
            for (Pair ent : vec1.nonZeroList) {
                int searchRes = binarySearch(vec2, ent.index);
                if (searchRes != -1) {
                    res += ent.value * searchRes;
                }
            }
            return res;
        }

        private int binarySearch(SparseVectorBS vec, int idx) {
            int low = vec.nonZeroList.get(0).index;
            int high = vec.nonZeroList.get(vec.nonZeroList.size() - 1).index;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int midIdx = vec.nonZeroList.get(mid).index;
                if (midIdx == idx) {
                    return vec.nonZeroList.get(mid).value;
                } else if (midIdx > idx) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return -1;
        }

        public int dotProduct(SparseVectorBS vec) {
            if (this.nonZeroList.size() < vec.nonZeroList.size()) {
                return dotProduct(this, vec);
            } else {
                return dotProduct(vec, this);
            }
        }


        // Test Main
        public static void main(String[] args) {
            List<Integer> nums1 = Arrays.asList(1, 0, 2, 0, 3);
            List<Integer> nums2 = Arrays.asList(0, 3, 0, 4, 0);
            SparseVectorBS vec1 = new SparseVectorBS(nums1);
            SparseVectorBS vec2 = new SparseVectorBS(nums2);
            System.out.println(vec1.dotProduct(vec2)); // It should print 12, because 1*0+2*0+3*4 = 12
        }
    }

    private static class Pair {
        int index;
        int value;

        Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}

//    Update from recent FB onsite, interviewer didn't accept the HASHMAP solution and wanted to see the 2 pointers
//    solution, in addition he also came up with a follow up question, what would you do if one of the vectors werent
//    fully "sparse" -> hint use binary search


