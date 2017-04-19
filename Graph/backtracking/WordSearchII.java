package Graph.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rliu on 4/19/17 11:36 AM.
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * It is better to using Trie for eliminate the impossible work to prevent go through all the possibilities
 */
public class WordSearchII {
    public static void main(String[] args) {
        char[][] board = {{'d', 'o', 'a', 'f'}, {'a', 'g', 'a', 'i'}, {'d', 'c', 'a', 'n'}};
        List<String> words = Arrays.asList("dog", "dad", "dgdg", "can", "again");
        System.out.println(wordSearchII(board, words));
    }


    public static List<String> wordSearchII(char[][] board, List<String> words) {
        TrieNode root = buildTrie(words);
        List<String> ret = new ArrayList<>();
        HashSet<String> resSet = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfsHelper(board, new StringBuilder(), root, i, j, resSet);
            }
        }
        ret.addAll(resSet);
        return ret;
    }

    public static void dfsHelper(char[][] board, StringBuilder word, TrieNode root, int i, int j, HashSet<String> set) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return;

        if (board[i][j] >= 'a' && board[i][j] <= 'z' && root.next[board[i][j] - 'a'] != null) {
            char c = board[i][j];
            word.append(c);
            if (root.next[c - 'a'].isWord)
                set.add(word.toString());
            board[i][j] += 256;
            dfsHelper(board, word, root.next[c - 'a'], i + 1, j, set);
            dfsHelper(board, word, root.next[c - 'a'], i - 1, j, set);
            dfsHelper(board, word, root.next[c - 'a'], i, j + 1, set);
            dfsHelper(board, word, root.next[c - 'a'], i, j - 1, set);


            board[i][j] -= 256;
            word.setLength(word.length() - 1);
        }

    }

    public static TrieNode buildTrie(List<String> words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode curr = root;
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if (curr.next[c - 'a'] == null)
                    curr.next[c - 'a'] = new TrieNode();
                curr = curr.next[c - 'a'];
            }
            curr.isWord = true;
        }

        return root;
    }

    private static class TrieNode {
        TrieNode[] next;
        boolean isWord;

        TrieNode() {
            next = new TrieNode[26];
            isWord = false;
        }
    }
}
