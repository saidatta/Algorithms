package leetcode.design;

import java.util.ArrayList;
import java.util.List;

public class DotProductSparseVector {
    private List<Integer> nonZeroIndices;
    private int[] nums;

    // Constructor
    public DotProductSparseVector(int[] nums) {
        this.nums = nums;
        this.nonZeroIndices = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeroIndices.add(i);
            }
        }
    }

    // Binary search to find the index in the non-zero indices list
    private boolean hasIndex(int index) {
        int left = 0, right = nonZeroIndices.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nonZeroIndices.get(mid) == index) return true;
            if (nonZeroIndices.get(mid) < index) left = mid + 1;
            else right = mid - 1;
        }

        return false;
    }

    // Compute the dot product between this vector and another SparseVector
    public int dotProduct(DotProductSparseVector vec) {
        int result = 0;

        for (int index : this.nonZeroIndices) {
            if (vec.hasIndex(index)) {
                result += this.nums[index] * vec.nums[index];
            }
        }

        return result;
    }
}
