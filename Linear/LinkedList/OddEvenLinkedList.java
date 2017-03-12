package Linear.LinkedList;

/**
 * Created by rliu on 3/11/17.
 * 328. Odd Even Linked List
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 */
public class OddEvenLinkedList {
    public static void main(String[] args) {
        System.out.println(oddEvenList(new ListNode(new int[]{1, 2, 3, 4, 5, 6})));
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return head;

        ListNode sec = new ListNode(0);
        ListNode secCurr = sec;
        ListNode curr = head;
        ListNode pre = curr;
        while (curr != null && curr.next != null) {
            secCurr.next = curr.next;
            curr.next = curr.next.next;
            secCurr.next.next = null;
            secCurr = secCurr.next;
            pre = curr;
            curr = curr.next;
        }

        if (curr == null)
            pre.next = sec.next;
        else
            curr.next = sec.next;

        return head;
    }
}
