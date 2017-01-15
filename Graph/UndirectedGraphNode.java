package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 1/14/17.
 * Graph ->  List<List<Integer>();
 * adjacentMatrix ArrayList<ArrayList<Integer> using 0 and 1 to represent unweighted graph
 * edge vector -> ArrayList<ArrayList<Integer> [0,1],[1,0],[3,2],[5,0]
 */
public class UndirectedGraphNode {
    int val;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int val) {
        this.val = val;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
