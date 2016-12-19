package Linear.LinkedList;

/**
 * Created by rliu on 12/18/16.
 * 82. Remove Duplicates from Sorted List II
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * <p>
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 */
public class RemoveDuplicates2 {
    public static void main(String[] args) {
        Node head = new Node(new int[]{1, 2, 2, 3, 3, 3, 4});
        System.out.print(deleteDuplicates(head));
    }

    public static Node deleteDuplicates(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node curr = head;
        Node pre = dummy;
        int count = 0;
        while (curr != null && curr.next != null) {
            while (curr != null && curr.next != null && curr.val == curr.next.val) {
                count++;
                curr = curr.next;
            }
            if (count == 0) {
                pre = pre.next;
            } else {
                count = 0;
                pre.next = curr.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}
