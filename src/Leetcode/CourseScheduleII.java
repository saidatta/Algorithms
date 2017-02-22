package Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * Created by venkatamunnangi on 13/11/16.
 */
public class CourseScheduleII {

    int index = 0;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //build the adjacency list, in-degree array, start list
        List<Integer>[] adj = new ArrayList[numCourses];
        int[] indeg = new int[numCourses];
        for(int i=0; i<numCourses; i++)
            adj[i] = new ArrayList<Integer>();
        for(int[] pre : prerequisites){
            adj[pre[1]].add(pre[0]);
            indeg[pre[0]]++;
        }
        List<Integer> starts = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            if(indeg[i]==0)
                starts.add(i);
        }
        //do dfs/bfs from each in-degree=0 vertex
        int[] res = new int[numCourses];
        for(Integer start : starts){
            // 	dfs(start, adj, indeg, res);
            bfs(start, adj, indeg, res);
        }

        if(index != numCourses) return new int[0];
        return res;
    }

    private void bfs(Integer s, List<Integer>[] adj, int[] indeg, int[] res){
        Queue<Integer> que = new LinkedList<>();
        que.offer(s);
        res[index] = s;
        index++;
        while(!que.isEmpty()){
            Integer node = que.poll();
            for(Integer neighbor : adj[node]){
                indeg[neighbor]--;
                if(indeg[neighbor]==0){
                    que.offer(neighbor);
                    res[index] = neighbor;
                    index++;
                }
            }
        }
    }

    private void dfs(Integer s, List<Integer>[] adj, int[] indeg, int[] res){
        res[index] = s;
        index++;
        for(Integer neighbor : adj[s]){
            indeg[neighbor]--;
            if(indeg[neighbor] == 0){
                dfs(neighbor, adj, indeg, res);
            }
        }
    }
}

