package CCI;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by venkatamunnangi on 1/2/17.
 */
public class BFS {
    void search(GraphNode root) {
        if(root == null) {
            return;
        }
        Queue<GraphNode> q = new LinkedList<GraphNode>();
        root.marked = true;
        q.offer(root);

        while(!q.isEmpty()) {
            GraphNode curr = q.poll();

            for(GraphNode gn : curr.getAdjacentNodes()) {
                if(!gn.marked) {
                    gn.marked = true;
                    q.offer(gn);
                }
            }
        }
    }

    class GraphNode {
        List<GraphNode> adjacentNodes;
        Object data;
        boolean marked;

        GraphNode(Object data) {
            this.data = data;
            this.marked = false;
        }


        List<GraphNode> getAdjacentNodes() {
            return adjacentNodes;
        }
    }
}



