package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by rliu on 4/6/17.
 * 261. Graph Valid Tree
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), check if these edges form a valid tree.
 * This problem can be converted to finding a cycle in a graph. It can be solved by using DFS (Recursion) or BFS (Queue).
 */
public class GraphValidTree {
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(isValidTree(n, edges));

    }

    public static boolean isValidTree(int n, int[][] edges) {
        boolean[] isVisited = new boolean[n];
        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < graph.length; i++)
            graph[i] = new ArrayList<Integer>();

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]); //undirected graph, need to add both side
        }
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();

        queue.offer(0);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            if (isVisited[curr])
                return false;
            else
                isVisited[curr] = true;
            for (int i = 0; i < graph[curr].size(); i++) {
                if (!isVisited[graph[curr].get(i)]) //加入未访问的
                    queue.add(graph[curr].get(i));
            }
        }
        return true;
    }

}

