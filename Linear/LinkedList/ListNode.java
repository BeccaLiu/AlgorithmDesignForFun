package Linear.LinkedList;

/**
 * Created by rliu on 1/26/17.
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode randomNext;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int[] arr) {
        this.val = arr[0];
        ListNode temp = this;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }
    }

    public String toString() {
        ListNode temp = this;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append(temp.val);
            temp = temp.next;
        }
        return sb.toString();
    }
}

