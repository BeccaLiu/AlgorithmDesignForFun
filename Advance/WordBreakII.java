package Advance;

import java.util.*;

/**
 * Created by rliu on 3/2/17.
 * 140. Word Break II
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 */
public class WordBreakII {
    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa");
        HashSet<String> set = new HashSet<String>();
        for (String ss : wordDict)
            set.add(ss);

        HashMap<String, List<String>> map = new HashMap<>();
//        for (String ss : wordDict) {
//            List<String> list = new ArrayList<>();
//            list.add(ss);
//            map.put(ss, list);
//        }

        //System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", list));
        System.out.println(wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", set, map));
    }

    //dp can work, but not still receiving time limited exceed or space limited, looks like a better solution will be backtracking with memorized array
    //[Attention] when to use dp, when asking count the possibility time, return true or false,
    //if need to returning the whole set of result, using backtracking
    public static List<String> wordBreak(String s, HashSet<String> set, HashMap<String, List<String>> map) {
        List<String> curr = map.get(s);
        if (curr != null)
            return curr;

        LinkedList<String> res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        //one clear solution will be thinking in the other way, if break the work is high in complexity, and it is constructed by the word in word dict and the size of the word in word dict is small, we can iterate just the word dict
        for (String word : set) {
            if (s.startsWith(word)) {
                List<String> sublist = wordBreak(s.substring(word.length()), set, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }

//        for (int i = 1; i < s.length(); i++) {
//            String left = s.substring(0, i);
//            List<String> leftList = wordBreak(left, set, map);
//            String right = s.substring(i, s.length());
//            List<String> rightList = wordBreak(right, set, map);
//            if(leftList==null||rightList==null||leftList.size()==0||rightList.size()==0)
//                return new ArrayList<>();
//
//            for (String l : leftList)
//                for (String r : rightList)
//                    res.add(l + " " + r);
//        }

        map.put(s, res);
        return new ArrayList<>(res);
    }

}
