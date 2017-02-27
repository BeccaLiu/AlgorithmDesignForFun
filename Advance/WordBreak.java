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
        List<String> list = Arrays.asList("leet", "cod");
        System.out.println(wordBreak("leetcode", list));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0)
            return false;
        HashSet<String> set = new HashSet<>();
        for (String ss : wordDict)
            set.add(ss);
        boolean[] cache = new boolean[s.length() + 1];
        cache[0] = true;

        for (int i = 0; i < s.length(); i++) {
            boolean isValid = false;
            for (int j = 0; j <= i && !isValid; j++) {
                String curr = s.substring(j, i + 1);
                isValid = set.contains(curr) && cache[j];
            }
            cache[i + 1] = isValid;
        }
        return cache[cache.length - 1];

    }
}
