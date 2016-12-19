package Linear.LinkedList;

/**
 * Created by rliu on 12/18/16.
 * Given a singly linked list, determine if it is a palindrome.
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        System.out.println(isPalindrome(new Node(new int[]{1, 2, 3, 2, 1})));
        System.out.println(isPalindrome(new Node(new int[]{1, 2, 3, 4, 1})));
        System.out.println(isPalindrome(new Node(new int[]{1, 1})));
        System.out.println(isPalindrome(new Node(new int[]{1, 2})));
    }

    //primitive idea will be using a extra array with O(n) space which take O(n) time to iterate the list to generate the array, take O(n/2) to check if arr[i]==arr[len-i-1];
    //another idea is using fast slow point to the middle of the list, and reverse the second half,and compare with head iteratively.
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node curr = slow.next;
        while (curr != null && curr.next != null) {
            Node temp = curr.next;
            curr.next = curr.next.next;
            temp.next = slow.next;
            slow.next = temp;
        }
        Node right = slow.next;
        Node left = head;
        while (right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;

        }
        return true;
    }
}
