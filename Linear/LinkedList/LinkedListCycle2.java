package Linear.LinkedList;

/**
 * Created by rliu on 12/27/16.
 * 142. Linked List Cycle II
 */
public class LinkedListCycle2 {
    public static void main(String[] args) {

    }

    public Node detectCycle(Node head) {
        if (head == null)
            return null;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                Node temp = head;
                while (temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return temp;
            }
        }
        return null;
    }
}
