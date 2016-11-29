package Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 11/28/16.
 */
public class UndirectedGraph {
    int label;
    List<UndirectedGraph> neighbors;

    UndirectedGraph(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraph>();
    }

//    void add(UndirectedGraph i) {
//        neighbors.add(i);
//    }

}
