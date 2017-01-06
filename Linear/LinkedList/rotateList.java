package Linear.LinkedList;

/**
 * Created by rliu on 1/5/17.
 * 61.rotate list
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 */
public class RotateList {
    public static void main(String[] args) {

    }

    //the question is simple, but be aware some corner case!
    public Node rotateRight(Node head, int k) {
        if (head == null || head.next == null) //corner case 1: if the input is null
            return head;
        int len = getLength(head); //corner case 2: if input is larger than length of the list
        k = k % len;
        if (k == 0 || k == len) //corner case 3: if the k is 0 or same as len, which no need to rotate
            return head;
        Node dummy = new Node(0);
        dummy.next = head;
        Node post = dummy;
        for (int i = 0; i < k; i++) {
            post = post.next;
        }
        Node pre = dummy;
        while (post.next != null) {
            post = post.next;
            pre = pre.next;
        }
        Node newHead = pre.next;
        post.next = head;
        pre.next = null;
        return newHead;
    }

    public int getLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;

    }


}
