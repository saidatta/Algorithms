package Leetcode.Graph.shortest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public  class MinWeightedSubgraphRequiredPaths {

        private static final int only_1 = 1;    //type_1
private static final int only_2 = 2;    //type_2
private static final int both = 3;      //type_3

// a weighted edge
private static class NodeCostPair {
    int to;
    long weight;

    public NodeCostPair(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }
}

// search node for dijkstra
private static class SeacrhNode implements Comparable<SeacrhNode> {
    int to;
    long cost;
    int type;

    public SeacrhNode(int to, long cost, int type) {
        this.to = to;
        this.type = type;
        this.cost = cost;
    }

    @Override
    public int compareTo(SeacrhNode o) {
        if(this.cost < o.cost) {
            return -1;
        }
        else if(this.cost > o.cost) {
            return 1;
        }
        else {
            if(this.type == both) {
                return -1;
            }
        }
        return 1;
    }
}

    private static long dijkstra(Map<Integer, List<NodeCostPair>> map, int src_1, int src_2, int dest) {
        int n = map.size();
        long[] dist_1 = new long[n];
        long[] dist_2 = new long[n];
        long[] dist = new long[n];
        //-1 is unvisited
        Arrays.fill(dist_1, -1);
        Arrays.fill(dist_2, -1);
        Arrays.fill(dist, -1);

        PriorityQueue<SeacrhNode> pq = new PriorityQueue<>();
        pq.add(new SeacrhNode(src_1, 0, only_1));
        pq.add(new SeacrhNode(src_2, 0, only_2));

        while(!pq.isEmpty()) {
            //get the next search node
            SeacrhNode node = pq.poll();
            int cur = node.to;
            long cost = node.cost;
            int type = node.type;

            // if this node is type_1
            if(type == only_1) {
                //we have already visited this node as a type_1 node (with a shorter path)
                if(dist_1[cur] != -1) {
                    continue;
                }
                //if it's the first time we are visiting this node as a type_1 node, update dist_1
                dist_1[cur] = cost;
                //get neighbours
                List<NodeCostPair> neighbours = map.get(cur);

                //a type_2 searchNode has already visited this node, it is a potential meeting point
                //from here on out, it is useless to queue type_1 searchNodes from here,
                //as type_3 will be better(subsequent edges will only be counted once)
                if(dist_2[cur] != -1) {
                    //queueing type_3 search node
                    pq.add(new SeacrhNode(cur, dist_1[cur] + dist_2[cur], both));
                }
                //a type_2 searchNode has not visited this node
                else {
                    for(NodeCostPair e : neighbours) {
                        //queueing type_1 search nodes
                        pq.add(new SeacrhNode(e.to, dist_1[cur] + e.weight, only_1));
                    }
                }
            }
            //I'm not commenting the code for type 2 as it's symmetrical to type_1
            else if(type == only_2) {
                if(dist_2[cur] != -1) {
                    continue;
                }
                dist_2[cur] = cost;
                List<NodeCostPair> neighbours = map.get(cur);
                if(dist_1[cur] != -1) {
                    pq.add(new SeacrhNode(cur, dist_1[cur] + dist_2[cur], both));
                }
                else {
                    for(NodeCostPair e : neighbours) {
                        pq.add(new SeacrhNode(e.to, dist_2[cur] + e.weight, only_2));
                    }
                }
            }
            //type_3 searchNode
            else {
                //we have already visited this node as a type_3 node, (with lower cost)
                if(dist[cur] != -1) {
                    continue;
                }
                //the first time we visit dest as a type 3 node, we return the cost to dest
                if(cur == dest) {
                    return cost;
                }
                //the first time we visit this node as a type_3 node, update its cost
                dist[cur] = cost;
                //getting neighbours
                List<NodeCostPair> neighbours = map.get(cur);
                for(NodeCostPair e : neighbours) {
                    //queueing type_3 searchNodes
                    pq.add(new SeacrhNode(e.to, dist[cur] + e.weight, both));
                }
            }
        }
        //we have not encountered dest as a type_3 node in our dijkstra, return -1
        return -1;
    }


    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        Map<Integer, List<NodeCostPair>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            map.get(from).add(new NodeCostPair(to, weight));
        }

        return dijkstra(map, src1, src2, dest);
    }
}