package leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import leetcode.tree.util.TreeNode;

/**
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/#/description
 * <p>
 *
 * Created by venkatamunnangi on 5/21/17.
 */
public class FindModeBST {
    Integer prev = null;
    int count = 1;
    int max = 0;

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();
        traverseInorder(root, list);

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void traverseInorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        traverseInorder(root.left, list);
        if (prev != null) {
            if (root.val == prev) {
                count++;
            } else {
                count = 1;
            }
        }
        if (count > max) {
            max = count;
            list.clear();
            list.add(root.val);
        } else if (count == max) {
            list.add(root.val);
        }
        prev = root.val;
        traverseInorder(root.right, list);
    }
}
