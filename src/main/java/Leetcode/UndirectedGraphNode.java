package Leetcode; /**
 * Created by venkatamunnangi on 12/16/16.
 */

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }
}