package Leetcode;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/min-stack/#/description
 * <p>
 * Created by venkatamunnangi on 4/2/17.
 */
class MinStack {
    private Node head;

    public void push(int x) {
        if(head == null){
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }

    class Node {
        int value;
        int min;
        Node next;

        Node( int value, int min) {
            this.value = value;
            this.min = min;
        }

        Node( int value, int min, Node next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }

    public static void main(String [] args) {
        MinStack minStack = new MinStack();
        out.println(minStack.getMin());
    }
}
