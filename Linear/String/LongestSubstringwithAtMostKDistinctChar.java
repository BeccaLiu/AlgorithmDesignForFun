package Linear.String;

import java.util.HashMap;

/**
 * Created by rliu on 1/30/17.
 * 340. Longest Substring with At Most K Distinct Characters
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 * For example, Given s = “eceba” and k = 2,
 * T is "ece" which its length is 3.
 */
public class LongestSubstringwithAtMostKDistinctChar {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("abaccca", 2));
    }

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        //key is the char, and Integer is the count and the most recent index
        if (k == 0 || s == null || s.length() == 0)
            return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        int[] counter = new int[128];
        int start = 0;
        int length = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (map.size() == k) { //only when size ==k ,we need to calculate the length
                Integer index = map.get(s.charAt(i));
                if (index == null) {
                    //do calculation
                    length = Math.max(length, i - start);
                    //removing char to until make the substring valid to including current char
                    index = start;
                    while (index <= i) {
                        counter[s.charAt(index) - ' ']--;
                        //when counter becames 0 means that we are not creating a valid input
                        if (counter[s.charAt(index) - ' '] == 0) {
                            map.remove(s.charAt(index));
                            break;
                        }
                        index++;
                    }
                    //moving the start to valid start point
                    start = index + 1;
                }
            }
            map.put(s.charAt(i), i);
            counter[s.charAt(i) - ' ']++;
        }
        length = Math.max(length, s.length() - start);
        return length;
    }
}
