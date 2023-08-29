package leetcode.graph.topological;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * Created by venkatamunnangi on 13/11/16.
 */
public class CourseScheduleII {
    private final Set<Integer> processedCourses = new HashSet<>();
    private final List<Integer> courseOrder = new ArrayList<>();

    public int[] findOrder(int toBeProcessedCoursesNum, int[][] prerequisites) {
        List<List<Integer>> prereqs = initializeAdjacencyList(toBeProcessedCoursesNum);
        int[] prereqCounts = new int[toBeProcessedCoursesNum];

        for (int[] prerequisite : prerequisites) {
            prereqCounts[prerequisite[0]]++;
            prereqs.get(prerequisite[1]).add(prerequisite[0]);
        }

        for (int course = 0; course < prereqCounts.length; course++) {
            if (prereqCounts[course] == 0 && !processedCourses.contains(course)) {
                toBeProcessedCoursesNum = DFS(prereqs, prereqCounts, toBeProcessedCoursesNum, course);
            }
        }

        int[] result = new int[courseOrder.size()];
        int index = 0;
        for (int course : courseOrder) {
            result[index++] = course;
        }

        return toBeProcessedCoursesNum == 0 ? result : new int[0];
    }

    private int DFS(List<List<Integer>> prereqs, int[] prereqCounts, int tobeProcessedCourses, int course) {
        tobeProcessedCourses--;
        processedCourses.add(course);
        courseOrder.add(course);

        for (int prereqCourse : prereqs.get(course)) {
            prereqCounts[prereqCourse]--;
            if (prereqCounts[prereqCourse] == 0) {
                tobeProcessedCourses = DFS(prereqs, prereqCounts, tobeProcessedCourses, prereqCourse);
            }
        }

        return tobeProcessedCourses;
    }

    private List<List<Integer>> initializeAdjacencyList(int numCourses) {
        List<List<Integer>> prereqs = new ArrayList<>();
        for (int course = 0; course < numCourses; course++) {
            prereqs.add(new ArrayList<>());
        }
        return prereqs;
    }
}

