package Linear.LinkedList;

/**
 * Created by rliu on 12/27/16.
 * 147. Insertion Sort List
 * Sort a linked list using insertion sort.
 */
public class InsertSortedList {
    public static void main(String[] args) {
        insertionSortList(new Node(new int[]{4, 3, 2, 1, 5, 0}));
    }

    public static Node insertionSortList(Node head) {
        if (head == null || head.next == null)
            return head;
        Node dummy = new Node(0);
        dummy.next = head;
        Node pre = dummy;
        Node curr = dummy.next;
        while (curr != null) {
            Node temphead = dummy;
            while (temphead.next != curr && temphead.next.val < curr.val) {
                temphead = temphead.next;
            }
            if (temphead.next == curr) {
                pre = pre.next;
                curr = curr.next;
            } else {
                pre.next = curr.next;
                curr.next = temphead.next;
                temphead.next = curr;
                curr = pre.next;
            }
        }
        return dummy.next;

    }
}
