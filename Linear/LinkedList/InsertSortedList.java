package Linear.LinkedList;

/**
 * Created by rliu on 12/27/16.
 * 147. Insertion Sort List
 * Sort a linked list using insertion sort.
 */
public class InsertSortedList {
    public static void main(String[] args) {

        insertionSortList(new Node(new int[]{4, 3, 2, 1, 5, 0}));
        System.out.println(insertionSortList2(new ListNode(new int[]{4, 3, 2, 1, 5, 0})));
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

    public static ListNode insertionSortList2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode curr = dummy.next;

        while (curr.next != null) {
            ListNode p = curr.next;
            if (p.val >= curr.val) {
                curr = curr.next;
            } else {
                //need to move and insert
                curr.next = p.next;
                ListNode tempPre = pre;
                while (tempPre != curr && tempPre.next != curr && p.val > tempPre.next.val)
                    tempPre = tempPre.next;
                p.next = tempPre.next;
                tempPre.next = p;
            }
        }
        return dummy.next;
    }
}
