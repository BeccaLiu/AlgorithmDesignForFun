package Graph;

/**
 * Created by rliu on 2/19/17.
 * 323. Number of Connected Components in an Undirected Graph
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
 * Example 1:
 * 0          3
 * |          |
 * 1 --- 2    4
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 */
public class NumberOfComponentGraph {
    public static void main(String[] args) {
        System.out.println(countComponents(5, new int[][]{}));
    }

    public static int countComponents(int n, int[][] edges) {
        if (n <= 1 || edges.length == 0) //if there are only one nodes or the edges is empty means every node is isolated
            return n;
        int[] uf = new int[n];
        for (int i = 0; i < n; i++)
            uf[i] = i;
        for (int i = 0; i < edges.length; i++) {
            int parentP = find(edges[i][0], uf);
            int parentQ = find(edges[i][1], uf);
            if (parentP != parentQ) {
                n--;
                uf[parentP] = parentQ;
            }
        }
        return n;
    }

    //path compression
    public static int find(int p, int[] uf) {
        while (uf[p] != p) {
            uf[p] = uf[uf[p]];
            p = uf[p];
        }
        return p;
    }
}
