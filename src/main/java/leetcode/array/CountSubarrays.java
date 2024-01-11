package leetcode.array;

public class CountSubarrays {
    SegmentTreeNode root = null;

    public int numberOfSubarrays(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
        return countContinuousSubarrays(0, nums.length - 1);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        } else {
            SegmentTreeNode node = new SegmentTreeNode(start, end);
            if(start == end) {
                node.min = nums[start];
                node.max = nums[start];
            } else {
                int mid = start + (end - start) / 2;
                node.left = buildTree(nums, start, mid);
                node.right = buildTree(nums, mid + 1, end);
                assert node.right != null;
                node.min = Math.min(node.left.min, node.right.min);
                node.max = Math.max(node.left.max, node.right.max);
            }
            return node;
        }
    }

    private int countContinuousSubarrays(int start, int end) {
        int count = 0;
        for(int i = start; i <= end; i++) {
            int j = i;
            while(j <= end) {
                int max = queryMax(root, i, j);
                int min = queryMin(root, i, j);
                if(max - min <= 2) {
                    count++;
                    j++;
                } else {
                    break;
                }
            }
        }
        return count;
    }

    private int queryMax(SegmentTreeNode root, int start, int end) {
        if(root == null || start > end || start > root.end || end < root.start) {
            return Integer.MIN_VALUE;
        }
        if(start <= root.start && end >= root.end) {
            return root.max;
        }
        return Math.max(queryMax(root.left, start, end), queryMax(root.right, start, end));
    }

    private int queryMin(SegmentTreeNode root, int start, int end) {
        if(root == null || start > end || start > root.end || end < root.start) {
            return Integer.MAX_VALUE;
        }
        if(start <= root.start && end >= root.end) {
            return root.min;
        }
        return Math.min(queryMin(root.left, start, end), queryMin(root.right, start, end));
    }

    public int numberOfSubarrays2(int[] nums) {
        int[] count = new int[3];
        int j = 0, result = 0;

        for (int i = 0; i < nums.length; i++) {
            count[nums[i] % 3]++;
            while (count[nums[i] % 3] > 1) {
                count[nums[j++] % 3]--;
            }
            result += i - j + 1;
        }
        return result;
    }
}
class SegmentTreeNode {
    int start, end;
    int max, min;
    SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.max = Integer.MIN_VALUE;
        this.min = Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        CountSubarrays solution = new CountSubarrays();

        int[] nums1 = {5, 4, 2, 4};
        System.out.println(solution.numberOfSubarrays(nums1));  // Expected output: 8

        int[] nums2 = {1, 2, 3};
        System.out.println(solution.numberOfSubarrays(nums2));  // Expected output: 6
    }
}
