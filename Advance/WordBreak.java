package Advance;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rliu on 2/26/17.
 * 139. Word Break
 */
public class WordBreak {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("leet", "code");
        System.out.println(wordBreak("leetcode", list));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0)
            return false;
        HashSet<String> dict = new HashSet<>();
        int maxLen = Integer.MIN_VALUE;
        for (String ss : wordDict) {
            dict.add(ss);
            maxLen = Math.max(maxLen, ss.length());
        }
        boolean[] cache = new boolean[s.length() + 1];
        cache[0] = true;

        for (int i = 1; i < cache.length; i++) {
            for (int j = i - 1; j >= 0 && j >= i - maxLen; j--) {
                if (cache[j] && dict.contains(s.substring(j, i))) {
                    cache[i] = true;
                    break;
                }
            }
        }
        return cache[s.length()];

    }
}
