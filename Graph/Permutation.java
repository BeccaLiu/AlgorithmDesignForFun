package Graph;

import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println(permutationDFS(arr, new ArrayList<List<Integer>>(), new ArrayList<Integer>(), 0));
    }

    /* primitive idea :BFS
     *                1
     *         12           21
     *    312 132 123  321 231 213
     * Time Complexity: O(n!) = 1*2*3*...n
     * Space Complexity: on n level we need arraylist of size n! to store all the permutations
     */
    public static List<List<Integer>> permutationPri(int[] arr) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (arr == null || arr.length == 0)
            return res;
        res.add(new ArrayList<Integer>());

        for (int i = 0; i < arr.length; i++) {
            List<List<Integer>> nextRes = new ArrayList<List<Integer>>();
            for (List<Integer> list : res) {
                for (int j = 0; j < list.size() + 1; j++) {
                    List<Integer> nextList = new ArrayList<Integer>(list);
                    nextList.add(j, arr[i]);
                    nextRes.add(nextList);
                }
            }
            res = nextRes;
        }
        return res;
    }

    /* Improved Idea: based on position DFS
     * Solution Tree:
     *         1         2         3
     *      12   13   21   23   31    32
     *   123    132  213  231  312   321
     *   Time: O(n!) using swap to save the time
     *   Space:O(n) using arraylist stack size is also O(n)'
     *   TODO:?
     */
    public static List<List<Integer>> permutationDFS(int[] arr, List<List<Integer>> res, List<Integer> list, int position) {
        if (position == arr.length) {
            res.add(new ArrayList<Integer>(list));
            return res;
        }
        for (int i = position; i < arr.length; i++) {
            list.add(arr[i]);
            swap(arr, position, i);
            System.out.println(position + ":/" + list + "/" + Arrays.toString(arr));
            permutationDFS(arr, res, list, position + 1);
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
