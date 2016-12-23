package Linear.LinkedList;

/**
 * Created by rliu on 12/22/16.
 * 2. Add Two Numbers
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        Node l1 = new Node(new int[]{2, 4, 6, 5});
        Node l2 = new Node(new int[]{5, 6, 4});
        addTwoNumbers(l1, l2);
    }

    public static Node addTwoNumbers(Node l1, Node l2) {
        Node rt = new Node(0);
        Node newCurr = rt;
        int add = 0;
        while (l1 != null || l2 != null) {
            int a = 0, b = 0;
            if (l1 != null) {
                a = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                b = l2.val;
                l2 = l2.next;
            }
            newCurr.next = new Node((a + b + add) % 10);
            add = (a + b + add) / 10;
            newCurr = newCurr.next;
        }
        if (add != 0)
            newCurr.next = new Node(add);
        return rt.next;
    }
}
