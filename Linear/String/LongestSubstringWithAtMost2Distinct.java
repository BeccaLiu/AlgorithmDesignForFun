package Linear.String;

/**
 * Created by rliu on 2/2/17.
 * 159. Longest Substring with At Most Two Distinct Characters
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
 * For example, Given s = “eceba”,
 * T is "ece" which its length is 3.
 */
public class LongestSubstringWithAtMost2Distinct {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));
    }

    //using the idea of two pointer
    //compare to at most k distinct, here if we only have two distinct, using the pointer to point to that two distinct char index
    //ex: aaeeeced when i='c' first=0, second=2, we need to calculate the length by i-first, and reset first=second, and second=i;
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0)
            return 0;
        if (s.length() <= 2)
            return s.length();
        int start = 0; ////eceeeee repeatingstart is 0,
        int repeatingStart = 0; //eceeeeed repeatingstart is 2, repeatingstart -1 is the other char

        int[] counter = new int[128];
        int len = Integer.MIN_VALUE;

        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - ' ';
            if (curr != s.charAt(repeatingStart) - ' ') {
                if (counter[curr] != 0) { //exist in range [start,repeatingstart] aka eceeee[c]
                    repeatingStart = i;
                } else {//curr does not exist in [start,i] at all, eceeeec[d] then do the length calculation
                    len = Math.max(len, i - start);
                    //first char is all removed
                    if (repeatingStart - 1 >= 0)
                        counter[s.charAt(repeatingStart - 1) - ' '] = 0;
                    //second char only left size i-reaptingStart
                    counter[s.charAt(repeatingStart) - ' '] = i - repeatingStart;
                    //reset start
                    start = repeatingStart;
                    //reset reaptingStart
                    repeatingStart = i;
                    //}
                }
            }
            counter[s.charAt(i) - ' ']++;
        }
        len = Math.max(len, s.length() - start);
        return len;
    }
}

