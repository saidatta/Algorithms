package leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.List;
import leetcode.tree.util.TreeNode;

// https://leetcode.com/problems/binary-tree-paths/description/
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) {
            searchBT(root, "", paths);
        }
        return paths;
    }

    private void searchBT(TreeNode root, String path, List<String> paths) {
        if (root.left == null && root.right == null) {  // leaf node
            paths.add(path + root.val);
        }
        if (root.left != null) {
            searchBT(root.left, path + root.val + "->", paths);
        }
        if (root.right != null) {
            searchBT(root.right, path + root.val + "->", paths);
        }
    }
}
