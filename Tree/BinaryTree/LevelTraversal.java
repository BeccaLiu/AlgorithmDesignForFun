package Tree.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 11/13/16.
 * Given a binary tree, return the level order of its nodes value
 * 3            {[3]
 * 9 2           ,[9,2]
 * 4          ,[4]}
 */
public class LevelTraversal {
    public static void main(String[] args) {
        int[] a = {3, 9, 2, 4};
        TreeNode t = new TreeNode(a);
        t.right.right = new TreeNode(4);
        ArrayDeque<TreeNode> q = new ArrayDeque<>();
        q.add(t);
        List<List<Integer>> rt = new ArrayList<List<Integer>>();
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            //pop and print all the nodes in current level
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                list.add(curr.val);
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
            rt.add(list);
        }
        System.out.print(rt);
    }
}
