package Tree.BinaryTree;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rliu on 1/9/17.
 * 437. Path Sum III
 * you are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * count including root=leaf =target aka root.val=target, count++;
 */
public class PathSumIII {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new int[]{10, 5, -3, 3, 2, 0, 11, 3, -2, 1});
        System.out.println(pathSum(root, 8));
    }

    public static int pathSum(TreeNode root, int sum) {
        // return pathSumCount(root, sum, new ArrayList<Integer>());
        return pathSumHashCount(root, sum, 0, new HashMap<Integer, Integer>());
    }

    //this is a slower solution,as it is add and remove and modified the arraylist frequently
    //top down solution
    public static int pathSumCount(TreeNode root, int sum, ArrayList<Integer> preSum) {
        if (root == null)
            return 0;

        int count = 0;
        for (int i = 0; i < preSum.size(); i++) {
            preSum.set(i, preSum.get(i) + root.val);
            if (preSum.get(i) == sum)
                count++;
        }

        preSum.add(root.val);
        if (root.val == sum)
            count++;
        count += pathSumCount(root.left, sum, preSum) + pathSumCount(root.right, sum, preSum);
        preSum.remove(preSum.size() - 1);
        for (int i = 0; i < preSum.size(); i++) {
            preSum.set(i, preSum.get(i) - root.val);
        }
        return count;
    }

    //faster way using hashmap instead of arraylist
    //Each time find all the path start from current node
    //compare to arraylist solution, here we save the time to calculate all the updated sum along the path, ex: 10->15,5->17,7,2
    //here in hashmap,key is suffix sum, and value is count;
    //why it will work? (a+b+c+d)=e, and a, a+b, a+b+c is in the hashmap, while we are at d, we want to know if d-target is in the hashmap
    //ex: if d-target=a+b which is in the hashmap, we know that c+d is=target
    //related question: range sum
    public static int pathSumHashCount(TreeNode root, int target, int curSum, HashMap<Integer, Integer> preSum) {
        if (root == null)
            return 0;
        curSum += root.val;
        int count = 0;
        if (preSum.containsKey(curSum - target))
            count += preSum.get(curSum - target);
        preSum.put(curSum, preSum.getOrDefault(curSum, 1));
        count += pathSumHashCount(root.left, target, curSum, preSum) + pathSumHashCount(root.right, target, curSum, preSum);
        preSum.put(curSum, preSum.get(curSum) - 1);
        return count;
    }
}
