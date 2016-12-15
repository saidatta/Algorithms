package Leetcode;

import Leetcode.LinkedList.ReverseLinkedList;

public class Main {

    public static void main(String[] args) {
        getReverseLinkedList();
    }

    public static void getReverseLinkedList() {
        ReverseLinkedList rl = new ReverseLinkedList();
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        //ReverseLinkedList.ListNode ln3 = new ReverseLinkedList.ListNode(3);
        //ReverseLinkedList.ListNode ln4 = new ReverseLinkedList.ListNode(4);
        ln1.next = ln2;
        ///ln2.next = ln3;
       // ln3.next = ln4;
        System.out.println(rl.reverseList(ln1).val);
    }

}
