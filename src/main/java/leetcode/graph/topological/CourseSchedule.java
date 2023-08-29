package leetcode.graph.topological;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/course-schedule/description/
 */
import java.util.*;

public class CourseSchedule {
    private int numCourses;
    private int[][] prerequisites;

    public CourseSchedule(int numCourses, int[][] prerequisites) {
        this.numCourses = numCourses;
        this.prerequisites = prerequisites;
    }

    public boolean canFinish() {
        ArrayList<Integer>[] adjList = buildAdjList();
        Stack<Integer> topoSort = getTopologicalOrder(adjList);
        return checkPrequisitesOrder(topoSort);
    }

    private ArrayList<Integer>[] buildAdjList() {
        ArrayList<Integer>[] adjList = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] edge : prerequisites) {
            // prereq -> actual.
            adjList[edge[1]].add(edge[0]);
        }
        return adjList;
    }

    private Stack<Integer> getTopologicalOrder(ArrayList<Integer>[] adjList) {
        Stack<Integer> topoSort = new Stack<>();
        boolean[] visited = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                populateTopoStackDFS(i, visited, adjList, topoSort);
            }
        }
        return topoSort;
    }

    private void populateTopoStackDFS(int current, boolean[] visited, ArrayList<Integer>[] adjList, Stack<Integer> topoSort) {
        if (!visited[current]) {
            visited[current] = true;
            for (int neighbor : adjList[current]) {
                if (!visited[neighbor]) {
                    populateTopoStackDFS(neighbor, visited, adjList, topoSort);
                }
            }
        }
        topoSort.push(current);
    }

    private boolean checkPrequisitesOrder(Stack<Integer> topoSort) {
        List<Integer> toReturn = new ArrayList<>();
        int[] index = new int[numCourses];
        while (!topoSort.empty()) {
            toReturn.add(topoSort.peek());
            topoSort.pop();
        }

        for (int i = 0; i < toReturn.size(); i++) {
            index[toReturn.get(i)] = i;
        }

        for (int[] edge : prerequisites) {
            if (index[edge[1]] >= index[edge[0]])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int numCourses = 2;
//        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[][] prerequisites = {{1, 0}};

        CourseSchedule finder = new CourseSchedule(numCourses, prerequisites);
        boolean order = finder.canFinish();
        System.out.println(order);
    }
}
