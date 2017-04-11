package Design;

import java.util.HashMap;

/**
 * Created by rliu on 2/3/17.
 * <p>
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * get(key) - Get the val (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, val) - Set or insert the val if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 * need to record visited frequency, also keep recently visited order
 * always keep track of least frequency node, every time if we want to removePiece a node, we compare the head node and least frequency node, to decide which to removePiece
 */
public class LFUCache {
    HashMap<Integer, Node> map;
    int capacity;
    HashMap<Integer, DoubleLinkedList> freqLists;
    Node head;

    public LFUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        freqLists = new HashMap<>();
    }

    public static void main(String[] args) {

        LFUCache cache = new LFUCache(15 /* capacity */);

        String[] ex = {"LFUCache", "put", "get", "put", "get", "get", "get", "put", "get", "put", "put", "put", "put", "put", "put", "get", "put", "put", "get", "put", "put", "put", "put", "put", "put", "put", "get", "put", "put", "put", "put", "put", "put", "get", "put", "put", "get", "put", "get", "put", "get", "put", "put", "get", "put", "put", "put", "get", "get"};
        int[][] data = {{15}, {33, 219}, {39}, {96, 56}, {129}, {115}, {112}, {3, 280}, {40}, {85, 193}, {10, 10}, {100, 136}, {12, 66}, {81, 261}, {33, 58}, {3}, {121, 308}, {129, 263}, {105}, {104, 38}, {65, 85}, {3, 141}, {29, 30}, {80, 191}, {52, 191}, {8, 300}, {136}, {48, 261}, {3, 193}, {133, 193}, {60, 183}, {128, 148}, {52, 176}, {48}, {48, 119}, {10, 241}, {124}, {130, 127}};

        for (int i = 1; i < data.length; i++) {
            if (ex[i].equals("put")) {
                if (data[i][0] == 130 && data[i][1] == 127)
                    System.out.println();
                cache.put(data[i][0], data[i][1]);

            } else if (ex[i].equals("get")) {
                int rt = cache.get(data[i][0]);
            }
        }

        cache.put(1, 1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(1));
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    public int get(int key) {
        System.out.println("get " + key + ":");
        if (capacity == 0)
            return -1;
        Node node = map.get(key);
        if (node == null)
            return -1;
        else {//exist
            int freq = node.freq;
            DoubleLinkedList from = freqLists.get(freq);
            from.remove(node);
            node.freq++;
            DoubleLinkedList to = freqLists.get(freq);
            if (to == null)
                to = new DoubleLinkedList(node);
            else
                to.addLast(node);
            link(from, to);
            return node.val;
        }
    }

    public void link(DoubleLinkedList from, DoubleLinkedList to) {
        Node next = from.tail.next;
        if (from != null)
            from.tail.next = to.head;
        if (next != null)
            next.pre = to.tail;
        to.head.pre = from.tail;
        to.tail.next = next;
    }

    public void put(int key, int value) {
        System.out.println("put " + key + "/" + value + ":");
        if (capacity == 0)
            return;
        Node node = map.get(key);

        if (node == null) {
            node = new Node(key, value, 1);


            DoubleLinkedList once = freqLists.get(1);
            once.addLast(node);
        } else {
            //add new node;
            int freq = node.freq;
            DoubleLinkedList from = freqLists.get(freq);
            from.remove(node);
            node.freq++;
            node.val = value;
            DoubleLinkedList to = freqLists.get(freq);
            if (to == null)
                to = new DoubleLinkedList(node);
            else
                to.addLast(node);
            link(from, to);
        }
    }

    private class DoubleLinkedList {
        Node head;
        Node tail;
        int size;

        public DoubleLinkedList() {
            head = tail = null;
            size = 0;

        }

        public DoubleLinkedList(Node item) {
            head = tail = item;
            size = 1;
        }

        public void addLast(Node item) {
            if (head == null)
                head = tail = item;
            else {
                tail.next = item;
                item.pre = tail;
                tail = tail.next;
            }
            size++;
        }

        public void remove(Node item) {
            Node pre = item.pre;
            Node next = item.next;
            if (pre != null)
                pre.next = next;
            if (next != null)
                next.pre = pre;
            if (item == head)
                head = head.next;
            if (item == tail)
                tail = tail.pre;
            size--;
        }
    }

    private class Node {
        int key;
        int val;
        int freq; //we keep track of the frequency here
        Node pre;
        Node next;

        public Node(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }
}