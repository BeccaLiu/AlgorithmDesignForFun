package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rliu on 3/19/17.
 * 444. Sequence Reconstruction Add to List
 * Example 1:
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3]]
 * Output:
 * false
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
 */
public class SequenceReconstruction {
    public static void main(String[] args) {
        int[] org = new int[]{1};

        List<List<Integer>> seqs = new ArrayList<>();
        seqs.add(Arrays.asList(1));
        seqs.add(Arrays.asList(10000000));
        //seqs.add(Arrays.asList(new Integer[]{2, 3}));

        System.out.println(sequenceReconstruction(org, seqs));
    }

    public static boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int[] indegree = new int[org.length + 1];
        ArrayList<Integer>[] graph = new ArrayList[org.length + 1];

        boolean isEmpty = true;
        for (List<Integer> list : seqs) {
            //deal with case [1] , [[],[]]
            if (list.size() >= 1)
                isEmpty = false;
            for (int i = 0; i < list.size(); i++) {
                int from = list.get(i);
                if (from > indegree.length || from < 0)
                    return false;
                //deal with case 10000000 and also some case there is not from and to, just one digit like org[1] seqs[[1][1]]
                if (i + 1 < list.size()) {
                    int to = list.get(i + 1);
                    if (to > indegree.length || to < 0)
                        return false;
                    indegree[to]++;
                    if (graph[from] == null)
                        graph[from] = new ArrayList<Integer>();
                    graph[from].add(to);
                }
            }
        }

        if (org.length == 1 && isEmpty)
            return false;

        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        int finished = 0;

        while (!queue.isEmpty() && queue.size() <= 1) {
            int from = queue.poll();
            finished++;

            if (graph[from] != null)
                for (Integer next : graph[from]) {
                    //Integer next=graph[from].get(i);
                    indegree[next]--;
                    if (indegree[next] == 0)
                        queue.offer(next);
                }

        }
        return finished == org.length;

    }
}
