package leetcode.linkedlist;

// https://leetcode.com/problems/split-linked-list-in-parts/description/
public class SplitLLParts {

    public ListNode[] splitListToParts(ListNode head, int k) {
        // Get the length of the linked list.
        int length = getLength(head);

        // Calculate size for each part and number of parts with an extra node.
        int minSize = length / k;
        int extra = length % k;

        ListNode[] result = new ListNode[k];
        ListNode current = head;
        for (int i = 0; i < k && current != null; i++) {
            result[i] = current;

            // Determine the size for the current part.
            int currentPartSize = minSize + (i < extra ? 1 : 0);

            // Traverse to the end of the current part.
            for (int j = 1; j < currentPartSize; j++) {
                current = current.next;
            }

            // Move the current pointer and disconnect the current part from the list.
            ListNode temp = current.next;
            current.next = null;
            current = temp;
        }
        return result;
    }

    // Helper function to calculate the length of the linked list.
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    public static void main(String[] args) {
        SplitLLParts solution = new SplitLLParts();

        // Test case 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        ListNode[] result1 = solution.splitListToParts(head1, 5);
        printResult(result1);  // Expected output: [[1],[2],[3],[],[]]

        // Test case 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(6);
        head2.next.next.next.next.next.next = new ListNode(7);
        head2.next.next.next.next.next.next.next = new ListNode(8);
        head2.next.next.next.next.next.next.next.next = new ListNode(9);
        head2.next.next.next.next.next.next.next.next.next = new ListNode(10);
        ListNode[] result2 = solution.splitListToParts(head2, 3);
        printResult(result2);  // Expected output: [[1,2,3,4],[5,6,7],[8,9,10]]
    }

    // Helper function to print the result
    public static void printResult(ListNode[] parts) {
        System.out.print("[");
        for (int i = 0; i < parts.length; i++) {
            System.out.print("[");
            ListNode current = parts[i];
            while (current != null) {
                System.out.print(current.val);
                current = current.next;
                if (current != null) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
            if (i != parts.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

}
