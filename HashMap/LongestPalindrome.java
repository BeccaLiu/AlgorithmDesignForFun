package HashMap;

import java.util.HashMap;

/**
 * Created by rliu on 2/28/17.
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("bananas"));
    }

    public static int longestPalindrome(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        for (char c : s.toCharArray())
            count.put(c, count.getOrDefault(c, 0) + 1);

        int count1 = 0;
        int countOver2 = 0;

        //using values() but not valueset, only has keySet() // values()
        for (int i : count.values()) {
            if (i == 1)
                count1++;
            if (i >= 2 && i % 2 == 0) {
                countOver2 += i;

            } else if (i >= 2 && i % 2 == 1) { //need to consider the odd and even
                countOver2 += i - 1;
                count1++;
            }
        }

        return countOver2 + (count1 > 0 ? 1 : 0);

    }
}
