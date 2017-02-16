package Tree;

import java.util.ArrayDeque;

/**
 * Created by rliu on 2/9/17.
 * 208. Implement Tree.Trie (Prefix Tree)
 * Implement a trie with insert, search, and startsWith methods.
 */
public class Trie {
    TireNode root;

    public Trie() {
        root = new TireNode();
    }

    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("app");
        obj.insert("apple");
        obj.insert("add");
        obj.insert("bear");
        boolean param_2 = obj.search("apps");
        boolean param_3 = obj.search("app");
        System.out.println(param_2);
        System.out.println(param_3);
        boolean rt = obj.searchWildCard(".pp");
        System.out.println(rt);
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TireNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (curr.nodes[index] == null) {
                curr.nodes[index] = new TireNode();
            }
            curr = curr.nodes[index];
        }
        curr.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TireNode pre = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (pre.nodes[index] == null)
                return false;
            pre = pre.nodes[index];
        }
        return pre.isWord == null ? false : pre.isWord;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean searchWildCard(String word) {
        TireNode pre = root;
        ArrayDeque<TireNode> queue = new ArrayDeque<>();
        queue.offer(pre);
        for (int i = 0; i < word.length() && !queue.isEmpty(); i++) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                TireNode curr = queue.poll();
                char c = word.charAt(i);
                if (c != '.') {
                    if (curr.nodes[c - 'a'] != null)
                        queue.offer(curr.nodes[c - 'a']);
                } else {
                    for (int k = 0; k < curr.nodes.length; k++) {
                        if (curr.nodes[k] != null)
                            queue.offer(curr.nodes[k]);
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            TireNode curr = queue.poll();
            if (curr.isWord)
                return true;
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TireNode pre = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (pre.nodes[index] == null)
                return false;
            pre = pre.nodes[index];
        }
        return pre != null;
    }

    public class TireNode {
        TireNode[] nodes;
        Boolean isWord;

        public TireNode() {
            nodes = new TireNode[26];
            isWord = false;
        }

    }
}
