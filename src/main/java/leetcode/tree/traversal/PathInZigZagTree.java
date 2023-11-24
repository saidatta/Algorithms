package leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree/description/
public class PathInZigZagTree {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> path = new ArrayList<>();
        int level = 1, levelMax = 1;

        // Determine the level where the label is located
        while (levelMax < label) {
            level++;
            levelMax = (int) Math.pow(2, level) - 1;
        }

        // Traverse upwards to the root
        while (label > 0) {
            path.add(label);
            int levelMin = (int) Math.pow(2, level - 1);

            // Calculating parent in Zigzag Tree:
            // In a standard binary tree, the parent is label / 2.
            // In a zigzag tree, labels are mirrored at each level.
            // To find the parent, we first mirror the label within the current level range.
            // This is done by (levelMin + levelMax - label) / 2.
            label = (levelMin + levelMax - label) / 2;
            level--;
            levelMax = levelMin - 1;
        }

        // Reverse the path to get it from root to the label
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        PathInZigZagTree treeSolver = new PathInZigZagTree();
        int label = 14; // Example label

        List<Integer> path = treeSolver.pathInZigZagTree(label);
        System.out.println("Path from root to " + label + ": " + path);
    }
}

//    1. Determine the Level of the Label: First, find out the level of the tree where the label is located. We can do
//    this by checking the range of labels at each level. The number of nodes at each level doubles, starting with 1
//    at the root.
//    2. Traverse Upwards to the Root: We start from the given label and move up the tree to the root. In a regular
//    binary tree (without zigzag), the parent of a node with label n would be n / 2. However, due to the zigzag
//    arrangement, we need to adjust this calculation.
//    3. Adjust for Zigzag Arrangement: In even levels, the labels are reversed. To find the parent in such a level, we
//    find the parent as if the labels were normal (i.e., parent = label / 2), then we adjust this parent by considering
//    the reversal of labels in that level.
//    4. Build the Path: Keep track of the labels as we move up the tree and reverse this list in the end to get the
//    path from the root to the given label.