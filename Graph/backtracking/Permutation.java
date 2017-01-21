package Graph.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 11/26/16.
 * Permuation I
 * Given a collection of distinct numbers, return all possible permutations without duplication
 */
public class Permutation {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println(permutationPri(arr));
        //[[3, 2, 1], [2, 3, 1], [2, 1, 3], [3, 1, 2], [1, 3, 2], [1, 2, 3]]
        System.out.println(permutationDFS(arr, new boolean[arr.length], new ArrayList<>(), new ArrayList<>()));
        //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        System.out.println(permutationDfsSwap(arr, new ArrayList<>(), new ArrayList<>(), 0));
        //[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]
    }

    /* primitive idea :BFS
     *                1
     *         12           21
     *    312 132 123  321 231 213
     * Time Complexity: O(n!) = 1*2*3*...n
     * Space Complexity: on n level we need arraylist of size n! to store all the permutations
     */
    public static List<List<Integer>> permutationPri(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        if (arr == null || arr.length == 0)
            return res;
        res.add(new ArrayList<>());

        for (int anArr : arr) {
            List<List<Integer>> nextRes = new ArrayList<>(); //next level

            for (List<Integer> list : res) {
                for (int j = 0; j < list.size() + 1; j++) {
                    List<Integer> nextList = new ArrayList<>(list);
                    nextList.add(j, anArr); //add current number to index
                    nextRes.add(nextList);
                }
            }
            res = nextRes;
        }
        return res;
    }

    //time: O(n!)
    //space O(n+n+n) boolean array, recursion stack
    //    Solution Tree:
    //            *         1         2         3
    //            *      12   13   21   23   31    32
    //            *   123    132  213  231  312   321
    public static List<List<Integer>> permutationDFS(int[] nums, boolean[] visited, ArrayList<Integer> list, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return res;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) { //using boolean array to check if current index has already in the list
                list.add(nums[i]);
                visited[i] = true;
                permutationDFS(nums, visited, list, res);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
        return res;
    }

    /* Improved Idea: based on position DFS
     * Solution Tree:
     *        123       213       321
     *         1         2         3            position=0
     *      12   13   21   23   32    31        position=1
     *   123    132  213  231  321   312        position=2
     *   (see the diff from previous dfs's solution tree at 321 tree)
     *   Time: O(n!) using swap to save the time
     *   Space:O(n) using arraylist stack size is also O(n)'
     *
     *  在对应的位置,把要处理的元素 swap一下
     *  the meaning of the position is telling the function that we are currently processing this index
     *  so we iteratively swap the number afterward to current position
     *  position -1 in the recursion is all the previous fixed position
     *  position is what we are currently process
     *  position +1 is where we go dfs
     */
    public static List<List<Integer>> permutationDfsSwap(int[] arr, List<List<Integer>> res, List<Integer> list, int position) {
        if (position == arr.length) {
            res.add(new ArrayList<>(list));
            return res;
        }
        for (int i = position; i < arr.length; i++) {
            list.add(arr[i]);
            swap(arr, position, i);
            permutationDfsSwap(arr, res, list, position + 1);
            swap(arr, position, i);
            list.remove(list.size() - 1);
        }
        return res;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
