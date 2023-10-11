package leetcode.linkedlist.traversal;

import java.util.ArrayList;
import java.util.List;
import leetcode.linkedlist.util.ListNode;

// https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/solutions/4054925/easy-java-solution-t-c-o-n/
public class FindLocalMinimaMaximaDistances {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // Define initial result values as -1.
        int[] result = new int[]{-1, -1};

        // A linked list with less than 3 nodes can't have critical points.
        if (head == null || head.next == null || head.next.next == null) {
            return result;
        }

        // List to hold positions of the critical points.
        List<Integer> criticalPoints = new ArrayList<>();

        // Pointers to current and previous nodes and an index to keep track of current position.
        ListNode current = head.next;
        ListNode previous = head;
        int currentPosition = 1;

        // Traverse the linked list.
        while (current.next != null) {
            // Check for local maxima and local minima.
            if ((current.val > previous.val && current.val > current.next.val)
                    || (current.val < previous.val && current.val < current.next.val)) {
                criticalPoints.add(currentPosition);
            }

            currentPosition++;
            previous = current;
            current = current.next;
        }

        // If there are less than 2 critical points, return [-1, -1].
        if (criticalPoints.size() < 2) {
            return result;
        }

        // The maximum distance will be the difference between the first and last critical points.
        result[1] = criticalPoints.get(criticalPoints.size() - 1) - criticalPoints.get(0);

        // To find the minimum distance, check the difference between adjacent critical points.
        int minDistance = Integer.MAX_VALUE;
        for (int i = 1; i < criticalPoints.size(); i++) {
            int distance = criticalPoints.get(i) - criticalPoints.get(i - 1);
            minDistance = Math.min(distance, minDistance);
        }
        result[0] = minDistance;

        return result;
    }

    public static void main(String[] args) {
        FindLocalMinimaMaximaDistances solution = new FindLocalMinimaMaximaDistances();

        // Sample test case 1
        ListNode head1 = new ListNode(5,
                new ListNode(3,
                        new ListNode(1,
                                new ListNode(2,
                                        new ListNode(5,
                                                new ListNode(1,
                                                        new ListNode(2)))))));
        int[] result1 = solution.nodesBetweenCriticalPoints(head1);
        System.out.println("Sample Test 1: [" + result1[0] + ", " + result1[1] + "]"); // Expected output: [1, 3]

        // Sample test case 2
        ListNode head2 = new ListNode(1,
                new ListNode(3,
                        new ListNode(2,
                                new ListNode(2,
                                        new ListNode(3,
                                                new ListNode(2,
                                                        new ListNode(2,
                                                                new ListNode(2,
                                                                        new ListNode(7)))))))));
        int[] result2 = solution.nodesBetweenCriticalPoints(head2);
        System.out.println("Sample Test 2: [" + result2[0] + ", " + result2[1] + "]"); // Expected output: [3, 3]
    }
}
