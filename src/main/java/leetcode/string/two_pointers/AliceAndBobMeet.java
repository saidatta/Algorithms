package leetcode.string.two_pointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AliceAndBobMeet {

    private static class SegmentTree {
        private final int[] tree;
        private final int n;

        public SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[n * 4];
            build(arr, 0, n - 1, 0);
        }

        private void build(int[] arr, int start, int end, int node) {
            if (start == end) {
                tree[node] = start;
                return;
            }
            int mid = start + (end - start) / 2;
            build(arr, start, mid, 2 * node + 1);
            build(arr, mid + 1, end, 2 * node + 2);
            tree[node] = arr[tree[2 * node + 1]] > arr[tree[2 * node + 2]] ? tree[2 * node + 1] : tree[2 * node + 2];
        }

        public int query(int l, int r, int[] arr) {
            return queryRec(0, n - 1, l, r, 0, arr);
        }

        private int queryRec(int start, int end, int l, int r, int node, int[] arr) {
            if (l > end || r < start) {
                return -1;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            int mid = start + (end - start) / 2;
            int leftIndex = queryRec(start, mid, l, r, 2 * node + 1, arr);
            int rightIndex = queryRec(mid + 1, end, l, r, 2 * node + 2, arr);
            if (leftIndex == -1) return rightIndex;
            if (rightIndex == -1) return leftIndex;
            return arr[leftIndex] > arr[rightIndex] ? leftIndex : rightIndex;
        }
    }

    public int[] findBuildingWhereMeet(int[] heights, int[][] queries) {
        int n = heights.length;
        SegmentTree segmentTree = new SegmentTree(heights);
        int[] result = new int[queries.length];
        Map<Integer, Integer> heightToFirstIndexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            heightToFirstIndexMap.putIfAbsent(heights[i], i);
        }

        for (int i = 0; i < queries.length; i++) {
            int ai = queries[i][0];
            int bi = queries[i][1];
            int minIndex = Math.min(ai, bi);
            int maxIndex = Math.max(ai, bi);
            int highestBuildingIndex = segmentTree.query(minIndex, maxIndex, heights);

            if (heights[highestBuildingIndex] > heights[ai] && heights[highestBuildingIndex] > heights[bi]) {
                result[i] = heightToFirstIndexMap.get(heights[highestBuildingIndex]);
            } else {
                result[i] = -1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        AliceAndBobMeet solution = new AliceAndBobMeet();
        System.out.println(Arrays.toString(solution.findBuildingWhereMeet(new int[]{6, 4, 8, 5, 2, 7}, new int[][]{{0, 1}, {0, 3}, {2, 4}, {3, 4}, {2, 2}})));
        // Output: [2, 5, -1, 5, 2]
        System.out.println(Arrays.toString(solution.findBuildingWhereMeet(new int[]{5, 3, 8, 2, 6, 1, 4, 6}, new int[][]{{0, 7}, {3, 5}, {5, 2}, {3, 0}, {1, 6}})));
        // Output: [7, 6, -1, 4, 6]
    }
}
