package Leetcode.Graph;

import Leetcode.UndirectedGraphNode;

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

    private UndirectedGraphNode clone(UndirectedGraphNode src, HashMap<UndirectedGraphNode, UndirectedGraphNode> visitedBag){
        if (src == null){
            return null;
        }
        if (visitedBag.containsKey(src)){
            return visitedBag.get(src);
        }

        UndirectedGraphNode n = new UndirectedGraphNode(src.label);
        n.neighbors = new ArrayList<>();

        visitedBag.put(src, n);
        for (UndirectedGraphNode child : src.neighbors){
            if (visitedBag.containsKey(child)){
                n.neighbors.add(visitedBag.get(child));
            } else {
                UndirectedGraphNode childCopy = clone(child, visitedBag);
                n.neighbors.add(childCopy);
            }
        }
        return n;
    }
}
