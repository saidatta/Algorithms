package Leetcode.Tree;

import Leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkatamunnangi on 11/29/16.
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> paths = new ArrayList<>();
        if(root != null) {
            treePathHelper(root, "", paths);
        }
        return paths;
    }

    public void treePathHelper(TreeNode root, String path, List<String> paths) {
        if(root.right == null && root.left == null) {
            paths.add(path + root.val);
        }

        if(root.left != null) {
            treePathHelper(root.left, path+root.val+"->" , paths);
        }
        if(root.right != null) {
            treePathHelper(root.right, path+root.val+"->", paths);
        }
    }
}
