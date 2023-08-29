package leetcode.graph.topological;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/description/
public class SortItemsGroupsRespectingBoundaries {
        int findex;
        public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems){
            //keep all non grouped items into separate groups, 
            //so that we find the cycle among all items that belong to individual groups
            for(int i = 0; i < group.length; i++){
                if(group[i] == -1) group[i] = m++;
            }

            ArrayList<Integer>[] groupMap = new ArrayList[m];
            ArrayList<Integer>[] groupItemMap = new ArrayList[m];
            for(int i = 0; i < m; i++){
                groupMap[i] = new ArrayList<>();
                groupItemMap[i] = new ArrayList<>();
            }

            ArrayList<Integer>[] items = new ArrayList[n];
            //prepare group graph, group to item relation from given items
            for(int i = 0; i < n; i++){
                items[i] = new ArrayList<>();
                for(int j : beforeItems.get(i)){
                    //if dependent group is not the same group we are walking through
                    if(group[j] != group[i]) groupMap[group[i]].add(group[j]);
                    items[i].add(j);
                }
                groupItemMap[group[i]].add(i);
            }

            int[] sortedGroups = new int[m];
            //if groups cannot be topsorted, stop
            if(!topsortGroups(groupMap, sortedGroups)) return new int[]{};

            int[] answer = new int[n];
            //if items cannot be topsorted, stop
            if(!topsortItems(groupItemMap, items, answer, sortedGroups)) return new int[]{};
            return answer;
        }

        private boolean topsortGroups(ArrayList<Integer>[] groupMap, int[] sortedGroups){
            int[] visited = new int[sortedGroups.length];
            findex = 0;
            for(int i = 0; i < sortedGroups.length; i++){
                if(!dfs(groupMap, visited, sortedGroups, i)) return false;
            }
            return true;
        }

        private boolean topsortItems(ArrayList<Integer>[] groupItemMap, ArrayList<Integer>[] items, int[] answer, int[] sortedGroups){
            int[] visited = new int[items.length];
            findex = 0;
            for(int i = 0; i < sortedGroups.length; i++){
                //fetch items belong to group i
                for(int item : groupItemMap[sortedGroups[i]]){
                    if(!dfs(items, visited, answer, item)) return false;
                }
            }
            return true;
        }

        private boolean dfs(ArrayList<Integer>[] graph, int[] visited, int[] buffer, int node){
            if(visited[node] == 1) return true;
            if(visited[node] == -1) return false; //cycle
            visited[node] = -1;
            for(int i : graph[node]){
                if(!dfs(graph, visited, buffer, i)) return false;
            }
            buffer[findex++] = node;
            visited[node] = 1;
            return true;
        }
}
