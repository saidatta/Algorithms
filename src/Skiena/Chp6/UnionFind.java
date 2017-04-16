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
 * For m operations and total n elements time complexity is O(m*f(n)) where f(n) is
 * very slowly growing function. For most cases f(n) <= 4 so effectively
 * total time will be O(m). Proof in Coreman book.
 *
 * Created by venkatamunnangi on 1/3/17.
 */
public class UnionFind {

    private Map<Long, Node> nodeMap = new HashMap<>();

    class Node {
        int rank;
        long data;
        Node parent;
    }


    public void makeSet(long data) {
        Node node = new Node();
        node.rank = 0;
        node.parent = node;
        node.data = data;
        nodeMap.put(data, node);
    }

    /**
     *
     * Combines two sets together to one.
     * Does union by rank.
     *
     * @param d1 - first data
     * @param d2 - 2nd data.
     * @return true if d1 & d2 are in a different connected components.
     */
    public boolean union(long d1, long d2) {

        Node n1 = nodeMap.get(d1);
        Node n2 = nodeMap.get(d2);

        Node p1 = findSet(n1);
        Node p2 = findSet(n2);

        //if they are part of same set do nothing
        if(p1.data == p2.data) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if(p1.rank >= p2.rank) {
            //increment rank only if both sets have same rank
            p1.rank = (p1.rank == p2.rank) ? p1.rank+1 : p1.rank;
            p2.parent = p1;
        } else {
            p1.parent = p2;
        }
        return true;
    }

    /**
     * Finds the representative of this set
     */
    public long findSet(long data) {
        return findSet(nodeMap.get(data)).data;
    }

    /**
     * Find the representative recursively and does path
     * compression as well.
     */
    private Node findSet(Node node) {
        Node parentNode = node.parent;
        if(parentNode == node) {
            return  parentNode;
        }

        // path compression once the parent root node is evaluated
        // then update the child Nodes with the correct root node.
        node.parent = findSet(node.parent);

        return node.parent;
    }

    public static void main(String args[]) {
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

        out.println(ds.findSet(1));
        out.println(ds.findSet(2));
        out.println(ds.findSet(3));
        out.println(ds.findSet(4));
        out.println(ds.findSet(5));
        out.println(ds.findSet(6));
        out.println(ds.findSet(7));
    }
}
