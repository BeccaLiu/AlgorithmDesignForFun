package Linear.LinkedList;

/**
 * Created by rliu on 11/11/16.
 * Given a singly linked list and a value x, partition the list with the rule:
 * all notes less than x comes before nodes greater than or equal to x.
 * (the original relative order of the nodes in each partition need to be preserved.
 * 2-1-5-6-1-4 ,4
 * 2-1-1-5-6-4
 */
public class PartitionList {
    public static Node partList(Node head, int target) {
        Node curr = head;
        Node smHead = new Node(0);
        Node lgHead = new Node(0);
        Node smPre = smHead;
        Node lgPre = lgHead;
        while (curr != null) {
            if (curr.val < target) {
                smPre.next = curr;
                smPre = smPre.next;
            } else {
                lgPre.next = curr;
                lgPre = lgPre.next;
            }
            curr = curr.next;
        }
        smPre.next = lgHead.next;
        lgPre.next = null;

        return smHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 1, 4};
        Node head = new Node(arr);
        int target = 4;
        head = partList(head, target);
        System.out.println(head);

    }
}
