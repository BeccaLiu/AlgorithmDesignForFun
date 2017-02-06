package Linear.LinkedList;

/**
 * Created by rliu on 2/5/17.
 * 160.Intersection of Two Linked Lists
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class IntersectionOf2LinkedList {
    //naive solution will be get the length of two sorted list first, and make two list starting at same point, and start iteration
    //get length will take O(m)+O(n) and iteration the intersection will take O(Math.max(m,n))
    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{1, 2, 3, 4, 5});
        ListNode l2 = new ListNode(1);
        l2.next = l1.next.next;

        System.out.println(getIntersectionNode(l1, l2).val);

    }

    //if the diff length of a an b is diff, a is the short one, and b is longest one
    //when a reach the end, b is still diff step from the end,
    //then we set a to headB,
    //when b reach the endB, a is running for diff len
    //we set b to headA
    //at this point, the longer b has runnint diff len, and now is same start as short a leng
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }


}
