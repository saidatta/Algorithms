package leetcode.graph.topological;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem Link: https://leetcode.com/problems/course-schedule-ii/
 * This class provides a solution to finding a course schedule order, given prerequisites.
 */
public class CourseScheduleII {
    // Set to store courses that are already processed.
    private final Set<Integer> processedCourses = new HashSet<>();
    // List to store the order of courses.
    private final List<Integer> courseOrder = new ArrayList<>();

    /**
     * Determines a valid order in which courses can be taken.
     *
     * @param totalCourses    The total number of courses.
     * @param prerequisites   The array of course-prerequisite pairs.
     * @return An array representing a valid order in which courses can be taken. Empty array if not possible.
     */
    public int[] findOrder(int totalCourses, int[][] prerequisites) {
        List<List<Integer>> prereqs = initializeAdjacencyList(totalCourses);
        int[] prereqCounts = new int[totalCourses];

        // Populate prerequisites and their count.
        for (int[] prerequisitePair : prerequisites) {
            int course = prerequisitePair[0];
            int prereq = prerequisitePair[1];
            prereqs.get(prereq).add(course);
            prereqCounts[course]++;
        }

        // Start with courses that don't have any prerequisites.
        for (int course = 0; course < totalCourses; course++) {
            if (prereqCounts[course] == 0 && !processedCourses.contains(course)) {
                totalCourses = DFS(prereqs, prereqCounts, totalCourses, course);
            }
        }

        // Convert the list to an array.
        return totalCourses == 0 ? courseOrder.stream().mapToInt(i -> i).toArray() : new int[0];
    }

    /**
     * Initializes the adjacency list for a given number of courses.
     *
     * @param numCourses Total number of courses.
     * @return The initialized adjacency list.
     */
    private List<List<Integer>> initializeAdjacencyList(int numCourses) {
        List<List<Integer>> prereqs = new ArrayList<>();
        for (int course = 0; course < numCourses; course++) {
            prereqs.add(new ArrayList<>());
        }
        return prereqs;
    }

    /**
     * Recursive method to process the current course and its prerequisites.
     *
     * @param prereqs              The adjacency list of prerequisites.
     * @param prereqCounts         The count of prerequisites for each course.
     * @param remainingCourses     The number of courses left to be processed.
     * @param currentCourse        The current course being processed.
     * @return The updated number of courses left to be processed.
     */
    private int DFS(List<List<Integer>> prereqs, int[] prereqCounts, int remainingCourses, int currentCourse) {
        remainingCourses--;
        processedCourses.add(currentCourse);
        courseOrder.add(currentCourse);

        for (int nextCourse : prereqs.get(currentCourse)) {
            prereqCounts[nextCourse]--;
            if (prereqCounts[nextCourse] == 0) {
                remainingCourses = DFS(prereqs, prereqCounts, remainingCourses, nextCourse);
            }
        }

        return remainingCourses;
    }

    public static void main(String[] args) {
        CourseScheduleII courseScheduleII = new CourseScheduleII();

        // Various test cases.
        int[][] prerequisites0 = {
                {5, 0},
                {4, 0},
                {0, 1},
                {1, 3},
                {3, 2},
                {0, 2}
        };
        int[][] prerequisites1 = {
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };
        int[][] prerequisites2 = {
                {1, 0}
        };
        int[][] prerequisites3 = {
                {1, 0},
                {0, 1}
        };

        // Print the results for each test case.
        printCourseOrder(courseScheduleII.findOrder(6, prerequisites0));
        printCourseOrder(courseScheduleII.findOrder(4, prerequisites1));
        printCourseOrder(courseScheduleII.findOrder(2, prerequisites2));
        printCourseOrder(courseScheduleII.findOrder(2, prerequisites3));
    }

    /**
     * Utility method to print the course order.
     *
     * @param order The course order to be printed.
     */
    private static void printCourseOrder(int[] order) {
        for (int i : order) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
