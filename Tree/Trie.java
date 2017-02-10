package Tree;

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
