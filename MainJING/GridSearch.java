package MainJING;

import java.util.ArrayDeque;
import java.util.HashSet;

/**
 * Created by rliu on 5/1/17 9:09 PM.
 * H---->8|
 * H---->8|------>28|----->52|
 * H---->8|->25|->28|----->52|--------->81|
 * H->5->8|->25|->28|->33->52|->55->70->81|->83
 * <p>
 * return the minium number of hops that are needed in order to either:
 * 1.reach the node of the value or
 * 2.determine that the node does not existed in the data structure
 */
public class GridSearch {
    public static void main(String[] args) {
        Node level0 = new Node(new int[]{0, 8});
        Node level1 = new Node(new int[]{0, 8, 28, 52});
        Node level2 = new Node(new int[]{0, 8, 25, 28, 52, 81});
        Node level3 = new Node(new int[]{0, 5, 8, 25, 28, 33, 52, 55, 70, 81, 83});
        level0.below = level1;
        level1.below = level2;
        level2.below = level3;

        level0.next.below = level1.next;
        level1.next.below = level2.next;
        level2.next.below = level3.next.next;

        level2.next.next.below = level3.next.next.next;

        level1.next.next.below = level2.next.next.next;
        level2.next.next.next.below = level3.next.next.next.next;

        level1.next.next.next.below = level2.next.next.next.next;
        level2.next.next.next.next.below = level3.next.next.next.next.next.next;

        level2.next.next.next.next.next.below = level3.next.next.next.next.next.next.next.next.next;

        System.out.println(searchNodes(level0, 25));
        System.out.println(searchNodesBFS(level0, 25));
    }

    public static int searchNodesBFS(Node root, int val) {
        HashSet<Node> visited = new HashSet<>();
        ArrayDeque<Node> queue = new ArrayDeque<>();
        visited.add(root);
        int hop = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (curr.value == val)
                    return hop;
                if (curr.next != null && !visited.contains(curr.next) && curr.value < val) {
                    queue.offer(curr.next);
                    visited.add(curr.next);
                }
                if (curr.below != null && !visited.contains(curr.below)) {
                    queue.offer(curr.below);
                    visited.add(curr.below);
                }
            }
            hop++;
        }
        return -1;
    }

    public static int searchNodes(Node root, int val) {
        int minStep = helper(root, val, 0);
        return minStep == Integer.MAX_VALUE ? -1 : minStep;
    }

    public static int helper(Node root, int target, int hopes) {
        if (root == null)
            return Integer.MAX_VALUE;
        if (root.value == target)
            return hopes;
        int right = target > root.value ? helper(root.next, target, hopes + 1) : Integer.MAX_VALUE;
        int below = helper(root.below, target, hopes + 1);
        return Math.min(right, below);
    }


    static class Node {
        int value;
        Node next;
        Node below;

        Node(int val) {
            value = val;
        }

        Node(int[] nums) {
            value = nums[0];
            Node curr = this;
            for (int i = 1; i < nums.length; i++) {
                curr.next = new Node(nums[i]);
                curr = curr.next;
            }
        }
    }
}
