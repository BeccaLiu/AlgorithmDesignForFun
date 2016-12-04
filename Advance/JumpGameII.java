package Advance;

/**
 * Created by rliu on 11/30/16.
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
public class JumpGameII {

    public static void main(String[] args) {
        int[] step = {2, 3, 1, 1, 4};
        System.out.println(recursion(step, 0));
        System.out.println(jump(step));
    }

    public static int recursion(int[] steps, int i) {
        if (i == steps.length - 1)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= steps[i]; j++) {
            min = Math.min(recursion(steps, i + j), min);
        }
        return min + 1;
    }

    //from solution tree to graph
    //the difference between this dp and other dp, is because here in order to reduce the duplicate case,
    // we can convert a tree to graph, but this only happened minlingful when we want to get result that graph can provide, like shortest path
    //and using BFS to get the shortest path between start s to end e
    //dealing with tree, we need pruning
    //two pointer with greedy is actually BFS
    //space and time O(n)
    public static int jump(int[] steps) {
        if (steps == null || steps.length == 0)
            return 0;
        int[] minSteps = new int[steps.length];
        minSteps[0] = 0;
        int curIndex = 0;
        for (int i = 1; i < steps.length; i++) {//i is right index;
            for (int j = curIndex; j < i; j++) {////j is left index
                System.out.println(j + "/" + i);
                if (steps[j] + j >= i) {
                    minSteps[i] = minSteps[j] + 1;
                    curIndex = j;
                    break;
                }
            }
        }
        return minSteps[steps.length - 1];
    }

}
