package Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by rliu on 2/18/17.
 * 269. Alien Dictionary
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 * For example,
 * Given the following words in dictionary,
 * ["wrt","wrf","er","ett","rftt"]
 * The correct order is: "wertf".
 * <p>
 * Testcase: ["ab"],["z","z"],["ab","adc"]
 * ["wrtkj","wrt"] expected ""
 */
public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = new String[]{"wrtkj", "wrt"};
        System.out.println(alienOrder(words));

    }

    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";

        if (words.length == 1) //for input ["ab"]
            return words[0];

        int[] inDegree = new int[26];
        Arrays.fill(inDegree, -1);//set every as -1, so that we can change it to 0 to mark this char is a char existed in wordsList
        HashSet<Character>[] order = new HashSet[26];

        //create adjacent list to represent the directed graph
        int count = 0;
        for (int i = 0; i < words.length - 1; i++) {
            int a = 0;
            int b = 0;
            boolean valid = false;
            while (a < words[i].length() && b < words[i + 1].length()) {
                char ca = words[i].charAt(a);
                char cb = words[i + 1].charAt(b);
                count = markExistChar(inDegree, ca, count);
                count = markExistChar(inDegree, cb, count);
                if (ca != cb) {
                    if (order[ca - 'a'] == null)
                        order[ca - 'a'] = new HashSet<Character>();

                    if (order[ca - 'a'].add(cb))
                        inDegree[cb - 'a']++;
                    valid = true;
                    break;
                }
                a++;
                b++;
            }
            if (!valid && words[i].length() > words[i + 1].length()) //for cases like ["wrtkj","wrt"] return "", but ["wrt","wrtkj"] return "wrtkj"
                return "";
            while (a < words[i].length())
                count = markExistChar(inDegree, words[i].charAt(a++), count);

            while (b < words[i + 1].length())
                count = markExistChar(inDegree, words[i + 1].charAt(b++), count);
        }

        ArrayDeque<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                queue.offer((char) (i + 'a'));
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                char curr = queue.poll();
                sb.append(curr);
                count--;
                if (order[curr - 'a'] != null) {
                    for (char c : order[curr - 'a']) {
                        inDegree[c - 'a']--;
                        if (inDegree[c - 'a'] == 0)
                            queue.offer(c);
                    }
                }
            }
        }
        return count == 0 ? sb.toString() : "";
    }

    public static int markExistChar(int[] inDegree, char ca, int count) {
        if (inDegree[ca - 'a'] == -1) { //count distinct char in words list
            inDegree[ca - 'a'] = 0;
            count++;
        }
        return count;
    }
}
