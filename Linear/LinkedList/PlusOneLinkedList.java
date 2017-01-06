package Linear.LinkedList;

/**
 * Created by rliu on 1/5/17.
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 * Example:
 * Input:
 * 1->2->3
 * Output:
 * 1->2->4
 */
public class PlusOneLinkedList {
    public static void main(String[] args) {
        Node list = new Node(new int[]{9, 9, 1});
        plusOne(list);
    }

    public static Node plusOne(Node head) {
        if (head == null)
            return new Node(1);
        head = reverseRec2(head);
        Node cur = head;
        Node pre = cur;
        int add = 1;
        while (cur != null) {

            int val = cur.val + add;
            add = val / 10;
            cur.val = val % 10;
            pre = cur;
            cur = cur.next;
        }
        if (add != 0)
            pre.next = new Node(add);
        head = reverseIte2(head);
        return head;
    }

    public static Node reverseIte1(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node pre = dummy;
        Node cur = head;
        while (cur.next != null) {
            //save to mv item
            Node mv = cur.next;
            //link the cur to mv.next
            cur.next = cur.next.next;
            //insert mv to head
            mv.next = pre.next;
            pre.next = mv;
        }
        return dummy.next;
    }

    public static Node reverseRec1(Node head) {
        if (head == null || head.next == null)
            return head;


        return head;
    }

    public static Node reverseIte2(Node head) {
        if (head == null || head.next == null)
            return head;
        Node newhead = null;
        Node cur = head;
        while (cur != null) {
            //reverse
            Node post = cur.next;
            cur.next = newhead;
            //prepare for the next iteration
            newhead = cur;
            cur = post;
        }
        return newhead;
    }

    public static Node reverseRec2(Node head) {
        //base case
        if (head == null || head.next == null)
            return head;

        Node rt = reverseRec2(head.next);
        //reverse
        head.next.next = head;
        head.next = null;
        return rt;
    }

}
