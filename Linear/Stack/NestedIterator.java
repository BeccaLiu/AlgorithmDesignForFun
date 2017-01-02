package Linear.Stack;

/**
 * Created by rliu on 1/2/17.
 * 341. Flatten Nested List Iterator
 */

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 **/
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}

public class NestedIterator implements Iterator<Integer> {
    ArrayDeque<NestedInteger> stack = new ArrayDeque<NestedInteger>();

    public NestedIterator(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.offerFirst(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pollFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
            List<NestedInteger> lists = stack.pollFirst().getList();
            for (int i = lists.size() - 1; i >= 0; i--) {
                stack.offerFirst(lists.get(i));
            }
        }
        return !stack.isEmpty() && stack.peekFirst().isInteger();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
