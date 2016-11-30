package Graph;

import java.util.ArrayDeque;

/**
 * Created by rliu on 11/29/16.
 * You are given a m x n 2D grid initialized with these three possible values.
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * Analysis: in an unweighted graph, if we want to know the shortest path between two node, we will think of BFS
 */
public class GatesAndWalls {

    public static void main(String[] args) {
        /*
        INF  -1  0  INF
        INF INF INF  -1
        INF  -1 INF  -1
          0  -1 INF INF
         */
        int[][] gw = {
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE,}
        };
        bfsStartWithDoor(gw);
        for (int i = 0; i < gw.length; i++) {
            for (int j = 0; j < gw[0].length; j++) {
                System.out.print(String.format("%3d", gw[i][j]));
            }
            System.out.println();
        }
    }

    //as there are more room than doors, rather than BFS start with Room , we can start with doors
    //Time complexity assume there are k doors, k*m*n
    public static void bfsStartWithDoor(int[][] gw) {

        for (int i = 0; i < gw.length; i++) {
            for (int j = 0; j < gw[0].length; j++) {
                if (gw[i][j] == 0) {
                    ArrayDeque<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] curr = queue.remove();
                        int x = curr[0];
                        int y = curr[1];
                        if (x - 1 >= 0 && gw[x - 1][y] > 0 && gw[x][y] + 1 < gw[x - 1][y]) {
                            gw[x - 1][y] = gw[x][y] + 1;
                            queue.add(new int[]{x - 1, y});
                        }
                        if (x + 1 < gw.length && gw[x + 1][y] > 0 && gw[x][y] + 1 < gw[x + 1][y]) {
                            gw[x + 1][y] = gw[x][y] + 1;
                            queue.add(new int[]{x + 1, y});
                        }
                        if (y - 1 >= 0 && gw[x][y - 1] > 0 && gw[x][y] + 1 < gw[x][y - 1]) {
                            gw[x][y - 1] = gw[x][y] + 1;
                            queue.add(new int[]{x, y - 1});
                        }
                        if (y + 1 < gw[0].length && gw[x][y + 1] > 0 && gw[x][y] + 1 < gw[x][y + 1]) {
                            gw[x][y + 1] = gw[x][y] + 1;
                            queue.add(new int[]{x, y + 1});

                        }
                    }
                }
            }
        }
    }

}
