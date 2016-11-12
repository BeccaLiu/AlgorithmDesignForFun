package Linear;

/**
 * Created by rliu on 11/11/16.
 * Given a singly linked list and a value x, partition the list with the rule:
 * all notes less than x comes before nodes greater than or equal to x.
 * (the original relative order of the nodes in each partition need to be preserved.
 * 2-1-5-6-1-4 ,4
 * 2-1-1-5-6-4
 */
public class PartitionList {

    public static void main(String[] args) {

    }

    private static class Node {
        int val;
        Node next;
    }
}
