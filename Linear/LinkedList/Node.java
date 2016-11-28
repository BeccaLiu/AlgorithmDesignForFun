package Linear.LinkedList;

/**
 * Created by rliu on 11/11/16.
 */
public class Node {
    public int val;
    public Node next;
    public Node randomNext;

    public Node(int val) {
        this.val = val;
    }

    public Node(int[] arr) {
        this.val = arr[0];
        Node temp = this;
        for (int i = 1; i < arr.length; i++) {
            Node n = new Node(arr[i]);
            temp.next = n;
            temp = temp.next;
        }
    }

    public String toString() {
        Node temp = this;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append(temp.val);
            temp = temp.next;
        }
        return sb.toString();
    }
}
