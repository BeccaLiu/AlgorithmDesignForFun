package Graph;

import Linear.LinkedList.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by rliu on 11/27/16.
 * Merge K sorted Linked List
 * Given K sorted singly linked list, merge it and return as one
 * Primitive Idea: using heap
 * TODO: Time: O(k+ave(n)*klogk)
 * Space: O(k)
 */
public class MergeKSortedList {
    public static void main(String[] args) {
        int[] a = {1, 3, 5};
        Node aList = new Node(a);
        int[] b = {-1, 4};
        Node bList = new Node(b);
        int[] c = {1, 2, 3, 4};
        Node cList = new Node(c);
        ArrayList<Node> list = new ArrayList<>();
        list.add(aList);
        list.add(bList);
        list.add(cList);
        // heapSolution(list);
        noHeapSolution(list);

    }

    public static Node heapSolution(ArrayList<Node> list) {
        Node dummy = new Node(-1);
        Node pre = dummy;
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.val - o2.val;
            }
        });
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                minHeap.add(list.get(i));
            }
        }

        while (!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            pre.next = cur;
            cur = cur.next;
            pre = pre.next;
            if (cur != null)
                minHeap.add(cur);
        }
        return dummy.next;
    }

    //bottom up merge sort
    //Time ave(n)*klogk logk levels and each levels has nk node
    //Space 1;
    public static Node noHeapSolution(ArrayList<Node> list) {
        int n = list.size();
        while (list.size() > 1) {
            Node a = list.remove(0);
            Node b = list.remove(0);
            Node c = mergeTwoNode(a, b);
            list.add(c);
        }
        return list.get(0);
    }

    public static Node mergeTwoNode(Node a, Node b) {
        Node dummy = new Node(-1);
        Node pre = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                pre.next = a;
                a = a.next;
            } else {
                pre.next = b;
                b = b.next;
            }
            pre = pre.next;
        }
        if (a == null)
            pre.next = b;
        if (b == null)
            pre.next = a;
        return dummy.next;
    }

}
