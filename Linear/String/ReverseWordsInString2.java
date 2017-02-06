package Linear.String;

import java.util.Arrays;

/**
 * Created by rliu on 12/26/16.
 * 186.Reverse Words In String 2
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 * <p>
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * <p>
 * Could you do it in-place without allocating extra space?
 */
public class ReverseWordsInString2 {
    public static void main(String[] args) {
        reverseWords("the sky is blue".toCharArray());
    }

    public static void reverseWords(char[] s) {
        if (s.length == 0)
            return;
        for (int i = 0; i < s.length / 2; i++) {
            swap(s, i, s.length - 1 - i);
        }

        int left = 0;
        int right = 0;
        while (right <= s.length) {
            if (right == s.length || s[right] == ' ') {
                int i = left;
                int j = right - 1;
                while (i < j) {
                    swap(s, i++, j--);
                }
                left = right + 1;
            }
            right++;
        }
        System.out.println(Arrays.toString(s));
    }

    public static void swap(char[] s, int i, int j) {
        if (s[i] != s[j]) {
            s[i] ^= s[j];
            s[j] ^= s[i];
            s[i] ^= s[j];
//            char temp = s[i];
//            s[i] = s[j];
//            s[j] = temp;
        }
    }

}
