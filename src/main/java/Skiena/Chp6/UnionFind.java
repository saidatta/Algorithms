package Skiena.Chp6;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.*;

/**
 * Video link - https://youtu.be/ID00PMy0-vE
 *
 * Disjoint sets using path compression and union by rank
 * Supports 3 operations
 * 1) makeSet
 * 2) union
 * 3) findSet
 *
 * For m operations and total n elements time complexity is O(m*f(n)) ~ O(m log(n)) where f(n) is
 * very slowly growing function. For most cases f(n) <= 4 so effectively
 * total time will be O(m). Proof in Coreman book.
 *
 * Created by venkatamunnangi on 1/3/17.
 */
public class UnionFind {
    private final Map<Long, Node> nodeMap = new HashMap<>();

    /**
     * Creates a set with only one element.
     *
     * @param data The element to be added.
     */
    public void makeSet(long data) {
        Node node = new Node();
        node.rank = 0;
        node.parent = node; // Parent is itself
        node.data = data;
        nodeMap.put(data, node);
    }

    /**
     * Merges two sets that contain the elements d1 and d2.
     *
     * @param d1 First element.
     * @param d2 Second element.
     * @return True if the sets were merged, false if they were already in the same set.
     */
    public boolean union(long d1, long d2) {
        Node node1 = nodeMap.get(d1);
        Node node2 = nodeMap.get(d2);

        Node parent1 = findParent(node1);
        Node parent2 = findParent(node2);

        // Check if they are part of the same set
        if (parent1.data == parent2.data) {
            return false;
        }

        // Union by rank: attach the smaller rank tree under the root of the higher rank tree
        if (parent1.rank >= parent2.rank) {
            // make parent2's parent  -> parent 1
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            // make parent1's parent  -> parent 2
            parent1.parent = parent2;
        }

        return true;
    }

    /**
     * Finds the representative (root node) of the set that contains data.
     *
     * @param data The element to find the set for.
     * @return The representative's data of the set.
     */
    public long findParent(long data) {
        return findParent(nodeMap.get(data)).data;
    }

    /**
     * Finds the representative of the set that node is part of.
     * Applies path compression to flatten the structure of the tree.
     *
     * @param node The node to find the representative for.
     * @return The representative node.
     */
    private Node findParent(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findParent(node.parent); // Path compression
        return node.parent;
    }

    // Node class representing each element in a set
    private static class Node {
        int rank;      // Rank of the node used for union by rank
        long data;     // Data or identifier of the node
        Node parent;   // Parent node in the set
    }

    public static void main(String[] args) {
        UnionFind ds = new UnionFind();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        out.println(ds.findParent(1));
        out.println(ds.findParent(2));
        out.println(ds.findParent(3));
        out.println(ds.findParent(4));
        out.println(ds.findParent(5));
        out.println(ds.findParent(6));
        out.println(ds.findParent(7));
    }
}
