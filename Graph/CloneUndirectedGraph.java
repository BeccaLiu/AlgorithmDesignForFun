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

    //why this recursion has not base case?
    //ANS: base case of recursive solution is that if the neighbors of curr node is already in map, aka visited, we will return directly
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
                UndirectedGraph cur = queue.poll(); //cur
                UndirectedGraph copy = map.get(cur); //copy of cur
                for (UndirectedGraph nei : cur.neighbors) { //iterate neighbor of cur
                    if (map.get(nei) == null) { //if the neighbor of cur is not in the map, create a copy of it
                        map.put(nei, new UndirectedGraph(nei.label));
                        //Attention: only add neighbor to queue when it does not exist in map, ex cur=1 and neighbor(cur)=[2,3,4] when we iterate till cur=2, we do not want to put 1 to queue again
                        //we do this is because, we want to have a one direction solution like tree from top to down, else the queue will never be empty if you move queue,add(nei) out of the if condition
                        queue.add(nei);
                    }
                    copy.neighbors.add(map.get(nei)); //add copy of neighbor to the neighbor of copy of cur
                }
            }
        }
        return map.get(a);
    }
}
