package Linear.LinkedList;

import Tree.BinaryTree.TreeNode;

/**
 * Created by rliu on 1/26/17.
 * 109.Convert Sorted List to Binary Search Tree
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class ConvertSortedListToTree {
    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(sortedListToBST(head));
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;

        return sortedListToBST(head, null);

    }

    public static TreeNode sortedListToBST(ListNode head, ListNode tail) {
        //Attention: compare to convert sorted array to tree, here we should return null but not new TreeNode(head)
        //because the initial tail is null, which is like arr.size()but not arr.size()-1, so here we x.next=tail, the x is the real tail
        if (head == tail)
            return null;

        ListNode mid = findMed(head, tail);
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head, mid);
        root.right = sortedListToBST(mid.next, tail);
        return root;
    }

    public static ListNode findMed(ListNode head, ListNode tail) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
