package Graph;

import java.util.ArrayDeque;

/**
 * Created by rliu on 11/26/16.
 * Number of Island
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * <p>
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 * Assumption: 1. land can not be connected horizontally or veritcally
 * 2. all four edge are surrounded by water
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {0, 1, 1, 1},
                {0, 1, 0, 0}
        };
        System.out.println(UFLand(arr));
    }

    //DFS
    //Space stack: O(m*n)
    //time: m*n*
    public static int dfsLand(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    dfsLandHelper(matrix, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfsLandHelper(int[][] arr, int i, int j) {
        if (i < 0 || i > arr.length - 1 || j < 0 || j > arr[0].length - 1)
            return;
        if (arr[i][j] == 1) {
            arr[i][j] = 0;
            dfsLandHelper(arr, i - 1, j);
            dfsLandHelper(arr, i + 1, j);
            dfsLandHelper(arr, i, j - 1);
            dfsLandHelper(arr, i, j + 1);
        }
    }

    //BFS
    //TODO: Time m*n*(m*n)? //m*n two for loop, in each for loop the worst case is all is 1, and queue take m*n to process
    //Space:queue O(m*n)
    public static int bfsLand(int[][] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int count = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    queue.add(i * arr[0].length + j);
                    count++;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int index = queue.remove();
                            int m = index / arr[0].length;
                            int n = index % arr[0].length;
                            arr[m][n] = 0;
                            if (m > 0 && arr[m - 1][n] == 1)
                                queue.add((m - 1) * arr[0].length + n);
                            if (m < arr.length - 1 && arr[m + 1][n] == 1)
                                queue.add((m + 1) * arr[0].length + n);
                            if (n > 0 && arr[m][n - 1] == 1)
                                queue.add(m * arr[0].length + n - 1);
                            if (n < arr[0].length - 1 && arr[m][n + 1] == 1)
                                queue.add(m * arr[0].length + n + 1);
                        }
                    }
                }
            }
        }
        return count;
    }

    //Union-Find
    public static int UFLand(int[][] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int count = 0;
        int[] uf = new int[arr.length * arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                uf[i * arr[0].length + j] = arr[i][j] == 0 ? -1 : i * arr[0].length + j;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    if (j < arr[0].length - 1 && arr[i][j + 1] == 1)
                        union(uf, i * arr[0].length + j, i * arr[0].length + 1 + j);
                    if (i < arr.length - 1 && arr[i + 1][j] == 1)
                        union(uf, i * arr[0].length + j, (i + 1) * arr[0].length + j);
                }
            }
        }

        for (int i = 0; i < uf.length; i++) {
            if (i == uf[i])
                count++;
        }
        return count;
    }

    public static void union(int[] arr, int i, int j) {
        if (j == 14)
            j = 14;
        int rootI = find(arr, i);
        int rootJ = find(arr, j);
        if (rootJ == j) {//j is not connected to any other before
            arr[j] = rootI;
        } else {
            //as we only count++ when i==uf[i], here we just set one of the node to the other
            arr[rootI] = rootJ;
        }
    }

    public static int find(int[] arr, int i) {
        //find with compress children
        while (i != arr[i]) {
            i = arr[i];
        }
        return i;
    }

}
