package Linear;

/**
 * Created by rliu on 11/11/16.
 * Reorder the List
 * 2-1-5-6-1-4 => 2-4-1-1-5-6
 * L0-L1-L2....Ln => L0-Ln-L1...
 */
public class ReorderList {
    public static Node reOrder(Node head) {
        return head;

    }

    public static void main(String[] args) {
        Node head = new Node(0);
        int[] arr = {2, 1, 5, 6, 1, 4};
        Node temp = head;
        for (int i : arr) {
            Node n = new Node(i);
            temp.next = n;
            temp = temp.next;
        }

    }
}
