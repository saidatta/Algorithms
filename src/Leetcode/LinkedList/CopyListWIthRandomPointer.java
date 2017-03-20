package Leetcode.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 138
 * https://leetcode.com/problems/copy-list-with-random-pointer/#/description
 *
 * Created by venkatamunnangi on 12/11/16.
 */
public class CopyListWIthRandomPointer {

        //O (n)
        public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        Map<RandomListNode, RandomListNode> dict = new HashMap<>();
        RandomListNode randomListNode = head;
        //copy the nodes
        copyTheNodes(dict, randomListNode);

        // assign the next node and
        // also assigning the random pointers
        randomListNode = head;
        assignRandomizer(dict, randomListNode);

        return dict.get(head);
    }

    private void copyTheNodes(Map<RandomListNode, RandomListNode> dict, RandomListNode randomListNode) {
        while (randomListNode != null) {
            dict.put(randomListNode, new RandomListNode(randomListNode.label));
            randomListNode = randomListNode.next;
        }
    }

    private void assignRandomizer(Map<RandomListNode, RandomListNode> dict, RandomListNode randomListNode) {
        while (randomListNode != null) {
            dict.get(randomListNode).next = dict.get(randomListNode.next);
            dict.get(randomListNode).random = dict.get(randomListNode.random);
            randomListNode = randomListNode.next;
        }
    }
}

