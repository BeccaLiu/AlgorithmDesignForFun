package Tree;

/**
 * Created by rliu on 5/2/17 6:23 PM.
 */
public class Contacts {
    static TrieNode root = new TrieNode();

    public static void main(String[] args) {
        add("hack");
        add("hackerrank");
        System.out.println(find("hak"));
        System.out.println(find("hac"));
    }

    public static int find(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            int cint = c - 'a';
            if (curr.next[cint] == null)
                return 0;
            curr = curr.next[cint];
        }


        return curr.wordCount;
    }

    public static void add(String s) {
        TrieNode curr = root;
        for (char c : s.toCharArray()) {
            int cint = c - 'a';
            if (curr.next[cint] == null) {
                curr.next[cint] = new TrieNode();
            }
            curr = curr.next[cint];
            curr.wordCount += 1;
        }
        curr.isWord = true;
    }


    public static class TrieNode {
        //HashMap<Character,TrieNode> next=new HashMap<Character,TrieNode>();
        TrieNode[] next = new TrieNode[26];
        boolean isWord;
        int wordCount;

    }
}
