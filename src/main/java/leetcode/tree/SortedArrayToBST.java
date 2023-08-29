package leetcode.tree;

import leetcode.TreeNode;

/**
 * Created by venkatamunnangi on 3/12/17.
 */
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return bstHelper(nums, 0, nums.length);
    }

    TreeNode bstHelper(int[] nums, int start, int end) {
        if(start==end) {
            return null;
        }

        int mid = (start+end)/2;

        TreeNode tn = new TreeNode(nums[mid]);
        tn.right = bstHelper(nums, mid+1, end);
        tn.left = bstHelper(nums, start, mid);

        return tn;
    }
}
