package leetcode.graph;

import leetcode.UndirectedGraphNode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/clone-graph/#/description
 *
 * Created by venkatamunnangi on 5/22/17.
 */
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node, new HashMap<>());
    }

    private UndirectedGraphNode clone(UndirectedGraphNode src,
                                      HashMap<UndirectedGraphNode, UndirectedGraphNode> visitedBag) {
        if (src == null) {
            return null;
        }

        if (visitedBag.containsKey(src)) {
            return visitedBag.get(src);
        }

        UndirectedGraphNode newNode = new UndirectedGraphNode(src.label);
        newNode.neighbors = new ArrayList<>();

        visitedBag.put(src, newNode);
        for (UndirectedGraphNode child : src.neighbors) {
            if (visitedBag.containsKey(child)) {
                newNode.neighbors.add(visitedBag.get(child));
            } else {
                UndirectedGraphNode childCopy = clone(child, visitedBag);
                newNode.neighbors.add(childCopy);
            }
        }
        return newNode;
    }
}
