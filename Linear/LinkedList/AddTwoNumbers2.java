package Linear.LinkedList;

/**
 * Created by rliu on 12/22/16.
 * 445. Add Two Numbers II
 * You are given two linked lists representing two non-negative numbers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * <p>
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 * what about (3->9->9->9)+(1) ->0-> 0->0->4
 */
public class AddTwoNumbers2 {
    public static void main(String[] args) {
        Node l1 = new Node(new int[]{9, 1});
        Node l2 = new Node(new int[]{1, 2, 3});
        addTwoNumbers(l1, l2);
    }

    //special case: 999+1=1000
    public static Node addTwoNumbers(Node l1, Node l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        Node rt = null;
        int len1 = getNodeLength(l1);
        int len2 = getNodeLength(l2);

        Node temp1, temp2;
        if (len1 > len2) {
            temp1 = l1;
            temp2 = l2;
        } else {
            temp1 = l2;
            temp2 = l1;
        }
        int lenMax = Math.max(len1, len2);
        int lenMin = Math.min(len1, len2);
        while (temp1 != null & temp2 != null) {
            // (3->9->9->9)+(1) -> rt=9->9->3
            while (lenMax > lenMin) {
                Node temp = new Node(temp1.val);
                temp.next = rt;
                temp1 = temp1.next;
                rt = temp;
                lenMax--;
            }
            int sum = temp1.val + temp2.val;
            int add = sum / 10;
            Node curr = new Node(sum % 10);
            curr.next = rt;
            rt = curr;
            //process carry : 0->9->9->3 ->0->0->0->4
            if (add != 0) {
                Node pre = rt;
                Node nextCarry = rt.next;
                while (nextCarry != null && add != 0) {
                    pre = nextCarry;
                    int tempSum = nextCarry.val + add;
                    nextCarry.val = tempSum % 10;
                    add = tempSum / 10;
                    nextCarry = nextCarry.next;
                }
                if (nextCarry == null && add != 0) {
                    pre.next = new Node(add);
                }
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        //reverse rt 0->0->0->4 => 4->0->0->0
        Node dummy = new Node(0);
        dummy.next = rt;
        while (rt != null && rt.next != null) {
            Node mv = rt.next;
            rt.next = rt.next.next;
            mv.next = dummy.next;
            dummy.next = mv;
        }
        return dummy.next;
    }

    public static int getNodeLength(Node l) {
        int size = 0;
        while (l != null) {
            size++;
            l = l.next;
        }
        return size;
    }
}
