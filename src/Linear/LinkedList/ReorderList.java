package Linear.LinkedList;

/**
 * Created by rliu on 11/11/16.
 * Reorder the List
 * 2-1-5-6-1-4 => 2-4-1-1-5-6
 * L0-L1-L2....Ln => L0-Ln-L1...
 * Key: 1. two pointer: one move from left to right, one move from right to left, however, it is a singly list can only move from left to right
 * 2. find the middle
 * 3. reverse the second half
 * 4. merge
 */
public class ReorderList {
    //searching for the end and reorder every time, Time Complexity is O(N^2)
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

    public static Node reOrderFast(Node head) {
        Node fast = head;
        Node slow = head;
        //find mid
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //reverse
        Node curr = slow.next;
        while (curr != null && curr.next != null) {
            Node temp = curr.next;
            curr.next = curr.next.next;
            temp.next = slow.next;
            slow.next = temp;
        }
        Node rightHead = slow.next;
        slow.next = null;

        //merge
        Node tempRight = rightHead;
        Node tempLeft = head;
        while (tempRight != null) {
            Node temp = tempRight;
            tempRight = tempRight.next;
            temp.next = tempLeft.next;
            tempLeft.next = temp;
            tempLeft = tempLeft.next.next;
        }
        return head;

    }

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 6, 4, 2};
        Node head = new Node(arr);
        System.out.println(reOrder(head));
        System.out.println(reOrderFast(new Node(arr)));
    }
}
