package Linear.LinkedList;

/**
 * Created by rliu on 11/11/16.
 * Return True/False to see if the linkedList has a cycle in it
 * Using slow point and fast point will definitely work, as slow point walk one node each step and will eventually meet fast point if there is a cycle
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        Node head = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        head.next = b;
        b.next = c;
        c.next = d;
        c.next = b;
        System.out.println(hasCycle(head));
        int[] arr = {2, 1, 5, 6, 1, 4};
        Node head2 = new Node(arr);
        System.out.println(hasCycle(head2));
    }

    public static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }
}
