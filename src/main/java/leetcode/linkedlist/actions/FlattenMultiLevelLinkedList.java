package leetcode.linkedlist.actions;

import leetcode.linkedlist.util.MultiLevelDoubleNode;

public class FlattenMultiLevelLinkedList {
    public MultiLevelDoubleNode flatten(MultiLevelDoubleNode head) {
        if (head == null) {
            return null;
        }

        MultiLevelDoubleNode pseudoHead = new MultiLevelDoubleNode();
        pseudoHead.next = head;
        head.prev = pseudoHead;

        flattenDFS(pseudoHead, head);

        // Detach the pseudo head
        pseudoHead.next.prev = null;
        return pseudoHead.next;
    }

    private MultiLevelDoubleNode flattenDFS(MultiLevelDoubleNode prev, MultiLevelDoubleNode curr) {
        if (curr == null) {
            return prev;
        }

        curr.prev = prev;
        prev.next = curr;

        // Save the next node before we potentially overwrite curr.next with the tail of the child list
        MultiLevelDoubleNode tempNext = curr.next;

        MultiLevelDoubleNode tail = flattenDFS(curr, curr.child);
        curr.child = null; // Remember to set the child pointer to null

        return flattenDFS(tail, tempNext);
    }
}

//    you need to traverse through the list and whenever you encounter a node with a child node, you recursively flatten
//    the child list and insert it between the current node and the next node. After inserting the child list, you need
//    to update the prev and next pointers accordingly.
