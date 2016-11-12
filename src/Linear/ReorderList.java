package Linear;

/**
 * Created by rliu on 11/11/16.
 * Reorder the List
 * 2-1-5-6-1-4 => 2-4-1-1-5-6
 * L0-L1-L2....Ln => L0-Ln-L1...
 */
public class ReorderList {
    //everytime searching for the end and reorder, Time Complexity is O(N^2)
    public static Node reOrder(Node head) {
        if (head == null)
            return head;
        Node curr = head;
        while (curr.next != null && curr.next.next != null) {
            Node temp = curr;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next.next = curr.next;
            curr.next = temp.next;
            temp.next = null;
            curr = curr.next.next;
        }
        return head;

    }

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 6, 4, 2};
        Node head = new Node(arr);
        System.out.println(reOrder(head));
    }
}
