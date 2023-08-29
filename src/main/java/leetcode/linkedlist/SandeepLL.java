package leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by venkatamunnangi on 5/7/17.
 */
public class SandeepLL {

    public static class LinkedListJump {
        public LinkedListJump next;
        LinkedListJump jump;
        int data;
        LinkedListJump(int d) {this.data = d;}
    }

    public LinkedListJump reverseLL(LinkedListJump head) {

        if (head == null || head.next == null) {
            return head;
        }

        Map<LinkedListJump, LinkedListJump> dict = new HashMap<>();
        LinkedListJump curr = head;
        LinkedListJump ln2 = null;

        while(curr != null) {
            dict.put(curr.jump, new LinkedListJump(curr.data));
            LinkedListJump temp = curr.next;
            curr.next = ln2;
            ln2 = curr;

            if(dict.containsKey(curr)) {
                curr.jump = dict.get(curr);
            }
            curr = temp;
        }

        return ln2;
    }

    public static void main(String... args) {

    }
}
