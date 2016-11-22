package Linear.LinkedList;

import java.util.HashMap;

/**
 * Created by rliu on 11/13/16.
 * TODO:
 */
public class DeepCopyLinkedList {
    public static void simpleCopy(Node head) {
        if (head == null)
            return;
        Node dummy = new Node(0);
        Node pre = dummy;
        while (head != null) {
            Node curr = new Node(head.val);
            pre.next = curr;
            head = head.next;
            pre = pre.next;
        }
        System.out.println(dummy.next);
    }

    public static void deepCopy2Pass(Node head) {
        if (head == null)
            return;
        HashMap<Node, Node> map = new HashMap<>();
        Node dummy = new Node(0);
        Node copy = dummy;
        Node cur = head;
        //first pass copy next
        while (cur != null) {
            Node temp = new Node(cur.val);
            map.put(cur, temp);
            copy.next = temp;
            cur = cur.next;
            copy = copy.next;
        }
        //second pass copy random
        copy = dummy.next;
        cur = head;
        while (cur != null) {
            copy.randomNext = map.get(cur.randomNext);
            cur = cur.next;
            copy = copy.next;
        }
        System.out.println(dummy.next);
    }

    public static void deepCopy1Pass(Node head) {
        if (head == null)
            return;
        HashMap<Node, Node> map = new HashMap<>();
        Node dummy = new Node(0);
        Node cur = head;
        Node copy = new Node(cur.val);
        map.put(cur, copy);
        dummy.next = copy;

        while (cur != null) {
            //next
            if (cur.next != null && !map.containsKey(cur.next)) {
                Node nextNode = new Node(cur.next.val);
                map.put(cur.next, nextNode);
                copy.next = nextNode;
            } else if (cur.next != null) {
                copy.next = map.get(cur.next);
            }

            //random
            if (cur.randomNext != null && !map.containsKey(cur.randomNext)) {
                Node randomNode = new Node(cur.randomNext.val);
                copy.randomNext = randomNode;
            } else if (cur.randomNext != null) {
                copy.randomNext = map.get(cur.randomNext);
            }
            cur = cur.next;
            copy = copy.next;
        }
        System.out.print(dummy.next);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Node head = new Node(arr);
        head.randomNext = head.next; //1-2
        head.next.randomNext = head.next.next.next; //2-4
        head.next.next.randomNext = head.next.next; //3-3
        simpleCopy(head);
        deepCopy2Pass(head);
        deepCopy1Pass(head);
    }
}
