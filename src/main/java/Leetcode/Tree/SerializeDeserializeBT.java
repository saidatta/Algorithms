package Leetcode.Tree;

import Leetcode.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/#/description
 *
 * Created by venkatamunnangi on 3/12/17.
 */
public class SerializeDeserializeBT {
    private static final String SEPERATOR = ",";
    private static final String NULL_NODE = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL_NODE).append(SEPERATOR);
        } else {
            sb.append(node.val).append(SEPERATOR);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(SEPERATOR)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NULL_NODE)) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }

    public static void main(String [] args) {
        SerializeDeserializeBT ss = new SerializeDeserializeBT();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        String x = ss.serialize(root);

        TreeNode tr = ss.deserialize(x);
        System.out.println(x);
        System.out.println(tr.left.val);
        System.out.println(tr.right.val);
    }
}
