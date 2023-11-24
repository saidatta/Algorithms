package leetcode.design.datastructure;

import static java.lang.System.out;

/**
 * https://leetcode.com/problems/min-stack/#/description
 * <p>
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * Created by venkatamunnangi on 4/2/17.
 */
class MinStack {
    private Node head;

    public void push(int x) {
        if (head == null) {
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

        Node(int value, int min) {
            this.value = value;
            this.min = min;
        }

        Node(int value, int min, Node next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        out.println(minStack.getMin());
    }
}
