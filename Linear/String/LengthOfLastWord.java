package Linear.String;

/**
 * Created by rliu on 12/19/16.
 * 58. Length of Last Word
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * For example,
 * Given s = "Hello World",
 * return 5.
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord(" "));
        System.out.println(lengthOfLastWord("hello world"));

    }

    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0)
            return 0;
        String[] a = s.split(" ");
        if (a.length == 0)
            return 0;
        return a[a.length - 1].length();

    }
}
