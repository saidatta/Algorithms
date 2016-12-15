package Leetcode;

/**
 * Created by venkatamunnangi on 24/11/16.
 */
public class ArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null) {
            return null;
        }

        return bstHelper(nums, 0, nums.length);
    }

    TreeNode bstHelper(int[] nums, int start, int end) {
        if(start>=end) {
            return null;
        }

        int mid = (start+end)/2;

        TreeNode tn = new TreeNode(mid);

        tn.left = bstHelper(nums, start, mid);
        tn.right = bstHelper(nums, mid+1, end);
        return tn;
    }
}
