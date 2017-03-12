package Linear.LinkedList;

/**
 * Created by rliu on 3/11/17.
 */
public class SortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{5, 4, 3, 2, 1});
        System.out.println(sortList(head));

    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode head2 = splitList(head);
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(head2);
        return mergeList(l1, l2);
    }

    public static ListNode splitList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode rt = slow.next;
        slow.next = null;
        return rt;
    }

    public static ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode rt = new ListNode(0);
        ListNode curr = rt;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if (l1 != null)
            curr.next = l1;
        if (l2 != null)
            curr.next = l2;

        return rt.next;
    }
}
