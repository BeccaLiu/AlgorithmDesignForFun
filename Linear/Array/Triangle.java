package Linear.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rliu on 1/5/17.
 * 120. Triangle
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * <p>
 * Analysis: the key of this problem is figure out what is the adjacent number of below row
 * the two adjacent number of number arr[row][i] : arr[row+1][i] and arr[row+1][i+1];
 * if we want to do it O(n) it is a typical dp problem
 */
public class Triangle {
    public static void main(String[] args) {
        Integer[][] arr = new Integer[][]{{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        for (Integer[] a : arr) {
            ArrayList<Integer> cur = new ArrayList<>(Arrays.asList(a));
            list.add(cur);
        }
        minimumTotal(list);
    }

    //can not pass the test case, as here, I am using pre=cur
    public static int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int[] pre = new int[triangle.size()];
        int[] cur = new int[triangle.size()];
        pre[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                int value = list.get(j);
                if (j - 1 < 0)
                    cur[j] = value + pre[j];
                else if (j >= list.size() - 1)
                    cur[j] = value + pre[j - 1];
                else
                    cur[j] = Math.min(pre[j], pre[j - 1]) + value;

            }
            //if you want to successfully swap the reference, you need using a temp like you deal with content in array
            int[] temp = pre;
            pre = cur;
            cur = temp;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < pre.length; i++) {
            if (pre[i] < min)
                min = pre[i];
        }
        return min;
    }

}
