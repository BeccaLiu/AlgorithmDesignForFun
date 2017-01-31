package Design;

import java.util.HashMap;

/**
 * Created by rliu on 1/29/17.
 * 146. LRU Cache
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 */
public class LRUCache {
    HashMap<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;


    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(10 /* capacity */);
        String[] ex = {"LRUCache", "put", "put", "put", "put", "put", "get", "put", "get", "get", "put", "get", "put", "put", "put", "get", "put", "get", "get", "get", "get", "put", "put", "get", "get", "get", "put", "put", "get", "put", "get", "put", "get", "get", "get", "put", "put", "put", "get", "put", "get", "get", "put", "put", "get", "put", "put", "put", "put", "get", "put", "put", "get", "put", "put", "get", "put", "put", "put", "put", "put", "get", "put", "put", "get", "put", "get", "get", "get", "put", "get", "get", "put", "put", "put", "put", "get", "put", "put", "put", "put", "get", "get", "get", "put", "put", "put", "get", "put", "put", "put", "get", "put", "put", "put", "get", "get", "get", "put", "put", "put", "put", "get", "put", "put", "put", "put", "put", "put", "put"};
        int[][] data = {{10}, {10, 13}, {3, 17}, {6, 11}, {10, 5}, {9, 10}, {13}, {2, 19}, {2}, {3}, {5, 25}, {8}, {9, 22}, {5, 5}, {1, 30}, {11}, {9, 12}, {7}, {5}, {8}, {9}, {4, 30}, {9, 3}, {9}, {10}, {10}, {6, 14}, {3, 1},
                {3}, {10, 11}, {8}, {2, 14}, {1}, {5}, {4}, {11, 4}, {12, 24}, {5, 18}, {13}, {7, 23}, {8}, {12}, {3, 27}, {2, 12}, {5}, {2, 9}, {13, 4}, {8, 18}, {1, 7}, {6}, {9, 29}, {8, 21}, {5}, {6, 30}, {1, 12}, {10}, {4, 15}, {7, 22}, {11, 26}, {8, 17}, {9, 29}, {5}, {3, 4}, {11, 30}, {12}, {4, 29}, {3}, {9}, {6}, {3, 4}, {1}, {10}, {3, 29}, {10, 28}, {1, 20}, {11, 13}, {3}, {3, 12}, {3, 8}, {10, 9}, {3, 26}, {8}, {7}, {5}, {13, 17}, {2, 27}, {11, 15}, {12}, {9, 19}, {2, 15}, {3, 16}, {1}, {12, 17}, {9, 1}, {6, 19}, {4}, {5}, {5}, {8, 1}, {11, 7}, {5, 2}, {9, 28}, {1}, {2, 2}, {7, 4}, {4, 22}, {7, 24}, {9, 26}, {13, 28}, {11, 26}};


        for (int i = 1; i < ex.length; i++) {
            if (ex[i].equals("put")) {
                if (data[i][0] == 9 && data[i][1] == 12)
                    System.out.println();
                cache.put(data[i][0], data[i][1]);

            } else if (ex[i].equals("get")) {
                int rt = cache.get(data[i][0]);
                if (rt == 24)
                    System.out.println();
                System.out.println(rt);
            }
        }
//        cache.put(1, 4);
//        // System.out.println(cache.get(1));
//        cache.put(2, 3);
//        cache.put(3, 2);
//        System.out.println(cache.get(1));
//        cache.put(4, 1);
//         System.out.println(cache.get(1));
//        cache.put(2, 2);
//        System.out.println(cache.get(1));       // returns 1
//        System.out.println(cache.get(2));
//        System.out.println(cache.get(3));
//        cache.put(3, 3);    // evicts key 2
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(2));       // returns -1 (not found)
//        cache.put(4, 4);    // evicts key 1
//        System.out.println(cache.get(1));       // returns -1 (not found)
//        System.out.println(cache.get(2));       // returns -1 (not found)
//        System.out.println(cache.get(3));       // returns 3
//        System.out.println(cache.get(4));       // returns 4

    }

    public int get(int key) {
        Node curr = map.get(key);
        if (curr == null)
            return -1;
        else {
            if (curr != tail) {
                removeNode(curr);
                appendNode(curr);
            }
            return curr.val;
        }
    }

    public void put(int key, int value) {
        Node curr = map.get(key);
        if (curr != null) {
            if (curr != tail) {
                removeNode(curr);
                appendNode(curr);
            }
            curr.val = value;
        } else {
            curr = new Node(key, value);
            if (map.size() < capacity) {
                appendNode(curr);
                map.put(key, curr);
            } else {
                int headKey = head.key;
                map.remove(headKey);
                removeNode(head);
                appendNode(curr);
                map.put(key, curr);
            }
        }
    }

    public void appendNode(Node node) {
        if (head == null)
            head = tail = node;
        else {
            tail.next = node;
            node.pre = tail;
            tail = tail.next;
        }

    }

    public void removeNode(Node node) {
        Node next = node.next;
        Node pre = node.pre;

        node.pre = null;
        node.next = null;

        if (next != null)
            next.pre = pre;
        if (pre != null)
            pre.next = next;

        if (node == head)
            head = next;
        if (node == tail)
            tail = pre;

    }

    private class Node {
        int key;
        int val;
        Node pre;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public String toString() {
            Node head = this;
            StringBuilder sb = new StringBuilder();
            while (head != null) {

                sb.append("[" + head.key + "," + head.val + "]");
                head = head.next;
            }
            return sb.toString();
        }
    }
}
