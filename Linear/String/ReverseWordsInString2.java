package Linear.String;

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
        System.out.print(reverseWords("the sky is blue".toCharArray()));
    }

    public static String reverseWords(char[] s) {
        if (s.length == 0)
            return s.toString();
        for (int i = 0; i < s.length / 2; i++) {
            swap(s, i, s.length - 1 - i);
        }
        int left = 0;
        for (int i = 0; i <= s.length; i++) {
            if (i == s.length || s[i] == ' ') {
                int right = i - 1;
                while (left <= right) {
                    swap(s, left++, right--);
                }
                left = i + 1;
            }
        }
        return s.toString();
    }

    public static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

}
