package lc.lc155;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(Math.min (Integer.MAX_VALUE, Integer.MAX_VALUE+ 1));
    }
}

class MinStack {
    private ArrayDeque<Integer> stack;
    private ArrayDeque<Integer> minStack;
    int min;

    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        min = Math.min(min, val);
        stack.push(val);
        minStack.push(min);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
        min = Math.max(minStack.peek() == null ? Integer.MAX_VALUE : minStack.peek(), min);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
