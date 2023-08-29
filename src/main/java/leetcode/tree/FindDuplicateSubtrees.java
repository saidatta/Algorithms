package leetcode.tree;

import leetcode.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/find-duplicate-subtrees/
 *
 * Created by venkatamunnangi on 10/7/19.
 */
public class FindDuplicateSubtrees {

//    If it's preorder, while you entering a node, you doesn't know the structure of its children yet, so you cannot do the serialization of this subtree at this moment.
//    To serialize a subtree, you have to dive into the children and get their serialization first, then you can serialize for the current node.
//    That is what is been done in:
//    String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);
//    Therefore, only postorder works.

    // pre-order string serialization, post-order traversal
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        postorder(root, new HashMap<>(), res);
        return res;
    }

    public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
        if (cur == null) {
            return "#";
        }
        String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);
        if (map.getOrDefault(serial, 0) >= 1) {
            // == 1 is to make sure there is only 1 occurence of subtree is stored in the end.
            res.add(cur);
        }
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
}
