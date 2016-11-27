package Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rliu on 11/14/16.
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */
public class WordLadder {
    //first generate the solution graph
    //find the shortest path, BFS, using queue, use hashset to market visited
    //TODO: Time Complexity:
    public static int wordLadder(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null || endWord == null || beginWord.length() != endWord.length() || wordList == null || wordList.size() == 0)
            return 0;

        Set<String> visited = new HashSet<>();
        ArrayDeque<String> queue = new ArrayDeque<String>();
        int level = 0;
        queue.add(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            System.out.println(queue);
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String word = queue.remove();
                for (int j = 0; j < word.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String newWord = replace(word, j, c);
                        if (newWord.equals(endWord))
                            return level + 1;
                        if (wordList.contains(newWord) && !visited.contains(newWord)) {
                            queue.add(newWord);
                            visited.add(newWord);
                        }
                    }
                }
            }

        }
        return 0;
    }

    //too slow running from starting word to end word, as every time, we need to generate the every possible transformation of the words at each level
    //=>why not from two side to middle
    //Bi-Direction breadth first search
    public static int wordLadderMultiEnd(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null || endWord == null || beginWord.length() != endWord.length() || wordList == null || wordList.size() == 0)
            return 0;

        Set<String> visited = new HashSet<>(); //the nodes in the set will be smaller than or equal to the wordlist size
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);
        beginSet.add(beginWord);
        endSet.add(endWord);
        int level = 0;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            level++;
            Set<String> nextLevel = new HashSet<>();
            System.out.println(beginSet + "/" + endSet);
            for (String word : beginSet) {
                for (int j = 0; j < word.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        String newWord = replace(word, j, c);
                        if (endSet.contains(newWord))
                            return level + 1;
                        if (wordList.contains(newWord) && visited.add(newWord))
                            nextLevel.add(newWord);
                    }
                }
            }
            if (nextLevel.size() <= endSet.size())
                beginSet = nextLevel;
            else {
                beginSet = endSet;
                endSet = nextLevel;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String[] arr = {"hot", "dot", "dog", "lot", "log"};
        Set<String> set = new HashSet<>(Arrays.asList(arr));
        System.out.println(wordLadder("hit", "cog", set));
        System.out.println(wordLadderMultiEnd("hit", "cog", set));
    }

    public static String replace(String word, int index, char k) {
        char[] charWord = word.toCharArray();
        charWord[index] = k;
        return new String(charWord);
    }
}
