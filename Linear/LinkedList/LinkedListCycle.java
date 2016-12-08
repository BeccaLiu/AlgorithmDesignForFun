package Linear.LinkedList;

/**
 * Created by rliu on 11/11/16.
 * Return True/False to see if the linkedList has a cycle in it
 * Using slow point and fast point will definitely work, as slow point walk one node each step and will eventually meet fast point if there is a cycle
 */
public class LinkedListCycle {
    public static void main(String[] args) {
        //1-> 2-> 3-> 4 ->5
        //    ^-------v
        Node head = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        head.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        d.next = b;
        System.out.println(hasCycle(head).val);
        int[] arr = {2, 1, 5, 6, 1, 4};
        Node head2 = new Node(arr);
        System.out.println(hasCycle(head2));
    }

    //also print the cycle head
    public static Node hasCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //demonstration: separate the list to a+b+c,
            // a is length from head to cycle head
            // b is length cycle head to slow fast meet node
            // c is length meet node till cycle head
            // so 2(a+b)=a+b+c+b => a=c
            if (slow == fast) {
                Node slowRt = head;
                //slowRt is at head, slow is at meet node, when slowRt walk a nodes, slow walk c nodes, they will meet at cycle head node, as a=c
                while (slowRt != slow) {
                    slow = slow.next;
                    slowRt = slowRt.next;
                }
                return slowRt;
            }
        }
        return null;
    }
}
