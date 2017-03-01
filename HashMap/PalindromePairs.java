package HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rliu on 2/28/17.
 * 336. Palindrome Pairs
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 */
public class PalindromePairs {
    public static void main(String[] args) {
        //"a","b","c","ab","ac","aa"
        System.out.println(palindromePairs(new String[]{"a", "b", "c", "ab", "ac", "aa"}));
        System.out.println(palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll", "aba", ""}));
    }


    //intuitive idea that two iteration and generate new string check if string is permuation will exceed the time limitation, which take O(n^2*k)
    //if we want to fasten it, the isPalindrome function can not be fasten easily
    //so we focus on the main stream
    //1. put all the reverse string of every word in hash
    //2. if the first part of the word[i] exsits in hash, means we just need to check if the rest of the string is palidrome like [abcd] [cba], cba is in hashmap, we check only the d
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list = new ArrayList<>();
        if (words == null || words.length == 0)
            return list;

        HashMap<String, Integer> map = new HashMap<>(); // reverse string and index

        for (int i = 0; i < words.length; i++)
            map.put(words[i], i);
        for (int i = 0; i < words.length; i++) {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            int len = words[i].length();
            for (int j = 0; j <= len; j++) {
                if (words[i].equals(""))
                    continue;
                //bcd cb dc
                //bcd ->reverse -> dcb -> ["","dcb"] ["d","cb"],["dc","b"],["dcb",""]
                //["d","cb"]-> bcd+"cb"
                //["dc","b"]-> "dc"+bcd

                //ab b a
                //ab->reverse->ba ->["","ba"] ["b","a"] ["ba",""]
                //["b","a"] left="b" "b"ab right="a" ab"a"
                String left = reversed.substring(0, j);
                String right = reversed.substring(j, len);
                Integer indexL = map.get(left);
                Integer indexR = map.get(right);


                if (j <= len && indexL != null && indexL != i && isPalindrome(reversed, j, len - 1)) { //return  left="b" "b"ab
                    List<Integer> indexes = new ArrayList<>();
                    indexes.add(indexL);
                    indexes.add(i);
                    list.add(indexes);
                }

                //Attention : without len>=j and j>0 for case "abcd", "dcba" will get [0,1][1,0][0,1][1,0]
                //using j<=len and j>0 will exculsively including the result as when j=0, j<=len will be T, and j>0 will be F
                if (j > 0 && indexR != null && indexR != i && isPalindrome(reversed, 0, j - 1)) { //return right="a" ab"a"
                    List<Integer> indexes = new ArrayList<>();
                    indexes.add(i);
                    indexes.add(indexR);
                    list.add(indexes);
                }
            }
        }
        return list;
    }

    public static boolean isPalindrome(String a, int l, int r) {
        while (l <= r) {
            if (a.charAt(l++) != a.charAt(r--))
                return false;
        }
        return true;
    }
}
