package Int;

import java.util.Stack;

/**
 * https://leetcode.com/problems/implement-queue-using-stacks/
 *
 * Created by venkatamunnangi on 9/20/19.
 */
import java.util.Stack;

// Define a Queue interface
interface Queue<T> {
    void push(T item);
    void pop();
    T peek();
    boolean empty();
}

// QueueStack now implements the Queue interface
public class QueueStack implements Queue<Integer> {
    private final Stack<Integer> s1 = new Stack<>();
    private final Stack<Integer> s2 = new Stack<>();

    @Override
    public void push(Integer x) {
        s1.push(x);
    }

    @Override
    public void pop() {
        ensureDataInS2();
        s2.pop();
    }

    @Override
    public Integer peek() {
        ensureDataInS2();
        return s2.peek();
    }

    @Override
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    private void ensureDataInS2() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new QueueStack();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // Outputs: 1
        queue.pop();
        System.out.println(queue.peek());  // Outputs: 2
    }
}
