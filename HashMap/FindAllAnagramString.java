package HashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rliu on 2/2/17.
 * 438. Find All Anagrams in a String
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * <p>
 * Analysis:
 * s: " init c b a e b a b a c d" p: "abc"
 * a  1   1 1 0 0 0 0 0-1 0 0
 * b  1   1 0 0 0 0 0-1 0 0 1
 * c  1   0 0 0 1 1 1 1 1 0 0
 * d  0   0 0 0 0 0 0 0 0 0-1
 * e  0   0 0 0-1-1-1 0 0 0 0
 * valid0   1 2 3 2 2 2 2 2 3 2
 * counter marks count need to match for each char
 * if counter <0 means in current windows, this char is redundant
 */
public class FindAllAnagramString {
    public static void main(String[] args) {
        System.out.println(findAnagramsFaster("cbaebabacd", "abc"));
    }

    //O(n*n) solution
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        int[] counter = new int[128];
        for (int i = 0; i < p.length(); i++) {
            counter[p.charAt(i) - ' ']++;
        }

        int start = 0;
        int valid = 0;
        while (start <= s.length() - p.length()) {
            int[] currentCounter = new int[128];
            for (int j = start; j < start + p.length(); j++) {
                currentCounter[s.charAt(j) - ' ']++;
                if (currentCounter[s.charAt(j) - ' '] > counter[s.charAt(j) - ' '])
                    break;
                else {
                    valid++;
                }
                if (valid == p.length())
                    res.add(start);
            }
            valid = 0;
            start++;
        }

        return res;
    }

    //O(n) solution
    public static List<Integer> findAnagramsFaster(String s, String p) {
        List<Integer> res = new ArrayList<>();

        int[] counter = new int[128];
        for (int i = 0; i < p.length(); i++) {
            counter[p.charAt(i) - ' ']++;
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s.length()) {
            //moving right pointer
            //every iteration we will move right once
            //counter[s.charAt(right) ] >= 1 means this is a valid char
            if (counter[s.charAt(right++) - ' ']-- >= 1)
                valid++;

            //collect result
            if (valid == p.length())
                res.add(left);

            //moving left when window size euqals p.length
            //aka left=0,right=4 for p.len=3
            //counter[s.charAt(left)] >= 0 means left char is a valid one
            if (right - left == p.length() && counter[s.charAt(left++) - ' ']++ >= 0)
                valid--;

        }

        return res;

    }

}
