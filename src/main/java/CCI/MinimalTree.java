package CCI;


import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/#/description
 *
 * Created by venkatamunnangi on 12/16/16.
 */
public class MinimalTree {

    TreeNode createBST(int array[]) {
        return createBSTHelper(array, 0, array.length-1);
    }

    TreeNode createBSTHelper(int[] array, int start, int end) {
        if(start > end) {
            return null;
        }

        int mid = (start + end) / 2;

        TreeNode root = new TreeNode(array[mid]);

        root.left = createBSTHelper(array, 0, mid-1);
        root.right = createBSTHelper(array, mid+1, end);

        return root;
    }
}
