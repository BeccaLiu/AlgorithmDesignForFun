package Linear.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by rliu on 12/28/16.
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in *constant* time.
 */
public class MinStack {
    Deque<Integer> stackData;
    Deque<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stackData = new ArrayDeque<Integer>();
        minStack = new ArrayDeque<Integer>();
    }

    public static void main(String[] args) {
        /**
         * Your MinStack object will be instantiated and called as such:
         * MinStack obj = new MinStack();
         * obj.push(x);
         * obj.pop();
         * int param_3 = obj.top();
         * int param_4 = obj.getMin();
         */
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());   //--> Returns -3.
        minStack.pop();
        System.out.println(minStack.top());      //--> Returns 0.
        System.out.println(minStack.getMin());   //--> Returns -2.
    }

    public void push(int x) {
        stackData.push(x);
        if (minStack.isEmpty())
            minStack.push(x);
        else {
            if (x < minStack.peek())
                minStack.push(x);
            else
                minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stackData.pop();
        minStack.pop();
    }

    public int top() {
        return stackData.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}


