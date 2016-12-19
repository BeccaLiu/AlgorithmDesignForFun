package Linear.LinkedList;

/**
 * Created by rliu on 12/18/16.
 * Remove Duplicates from Sorted List
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        Node head = new Node(new int[]{1, 1, 2, 3, 3, 4, 4, 4});
        System.out.print(deleteDuplicates(head));
    }

    public static Node deleteDuplicates(Node head) {
        if (head == null || head.next == null)
            return head;
        Node curr = head;
        while (curr != null) {
            Node pre = curr;
            curr = curr.next;
            while (curr != null && curr.val == pre.val)
                curr = curr.next;
            pre.next = curr;
        }
        return head;
    }
}
