package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by rliu on 5/6/17.
 */
public class ShortestReachInGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }

    public static class Graph {

        int[] depth;
        ArrayList<Integer>[] graph;

        public Graph(int size) {
            graph = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            depth = new int[size];
        }

        public void addEdge(int first, int second) {
            //no direction need to add two way
            graph[first].add(second);
            graph[second].add(first);
        }

        public int[] shortestReach(int startId) { // 0 indexed
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.offer(startId);
            HashSet<Integer> visited = new HashSet<>();
            visited.add(startId);
            while (!queue.isEmpty()) {
                int prev = queue.poll();
                for (int c : graph[prev]) {
                    if (!visited.contains(c)) {
                        queue.offer(c);
                        depth[c] = depth[prev] + 6;
                        visited.add(c);
                    }
                }
            }

            for (int i = 0; i < depth.length; i++) {
                if (depth[i] == 0)
                    depth[i] = -1;
            }
            return depth;
        }
    }
}
