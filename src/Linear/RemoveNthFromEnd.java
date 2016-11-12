package Linear;

/**
 * Created by rliu on 11/11/16.
 * input: n==0? || n>nodelist.size?
 */
public class RemoveNthFromEnd {
    public static Node removeNthFromEnd(Node node, int n) {
        if (node == null)
            return null;
        Node dummy = new Node(0);
        dummy.next = node;
        Node right = dummy;

        while (n-- > 0) {
            right = right.next;
            if (right == null)
                return null; //if n is larger than the node size
        }
        Node left = dummy;
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5};
        Node head = new Node(arr);
        System.out.println(removeNthFromEnd(head, 3));
    }
}
