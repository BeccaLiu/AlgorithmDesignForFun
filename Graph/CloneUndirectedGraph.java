package Graph;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by rliu on 11/28/16.
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 */
public class CloneUndirectedGraph {
    public static void main(String[] args) {
        UndirectedGraph a = new UndirectedGraph(0);
        UndirectedGraph b = new UndirectedGraph(1);
        UndirectedGraph c = new UndirectedGraph(2);
        UndirectedGraph d = new UndirectedGraph(3);
        a.neighbors.add(b);
        a.neighbors.add(d);
        b.neighbors.add(a);
        b.neighbors.add(d);
        b.neighbors.add(c);
        c.neighbors.add(b);
        d.neighbors.add(a);
        d.neighbors.add(b);
        HashMap<UndirectedGraph, UndirectedGraph> map = new HashMap<UndirectedGraph, UndirectedGraph>();
        UndirectedGraph rt = bfsHelper(a);
        System.out.print(rt);

    }

    //TODO: why this recursion has not base case?
    //Time: O(v+2e)
    //Space O(v) for hashmap
    public static UndirectedGraph dfsHelper(UndirectedGraph a, HashMap<UndirectedGraph, UndirectedGraph> map) {
        map.put(a, new UndirectedGraph(a.label));
        for (UndirectedGraph curr : a.neighbors) {
            UndirectedGraph copy = map.get(curr);
            if (copy == null) {
                copy = dfsHelper(curr, map);
            }
            map.get(a).neighbors.add(copy);
        }
        return map.get(a);
    }

    public static UndirectedGraph bfsHelper(UndirectedGraph a) {
        ArrayDeque<UndirectedGraph> queue = new ArrayDeque<>();
        HashMap<UndirectedGraph, UndirectedGraph> map = new HashMap<UndirectedGraph, UndirectedGraph>();

        queue.add(a);
        map.put(a, new UndirectedGraph(a.label));

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                UndirectedGraph cur = queue.poll();
                UndirectedGraph copy = map.get(cur);
                for (UndirectedGraph nei : cur.neighbors) {
                    if (map.get(nei) == null) {
                        map.put(nei, new UndirectedGraph(nei.label));
                        queue.add(nei);
                    }
                    copy.neighbors.add(map.get(nei));
                }
            }
        }
        return map.get(a);
    }
}
