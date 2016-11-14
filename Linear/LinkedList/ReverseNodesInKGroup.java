package Linear.LinkedList;

/**
 * Created by rliu on 11/12/16.
 * reverse Node in k group
 */
public class ReverseNodesInKGroup {
    //TODO: reverse 1357642 to 7531642

    public static Node reverseK(Node head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;
        Node dummy = new Node(0);
        dummy.next = head;
        Node pre = dummy;

        while (head != null) {
            int i = 0;
            while (i++ < k - 1 && head != null && head.next != null) {
                Node next = head.next;
                head.next = head.next.next;
                next.next = pre.next;
                pre.next = next;
            }
            pre = head;
            head = pre.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 6, 4, 2};
        Node head = new Node(arr);
        System.out.println(reverseK(head, 4));
    }
}
