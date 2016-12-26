package Linear.String;

import java.util.ArrayList;

/**
 * Created by rliu on 12/20/16.
 * 151. Reverse Words in a String
 * Given an input string, reverse the string word by word.
 * <p>
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the"
 * this question looks easy, but so many pitfall, pay attention to input "a"->"a" ," ","  "->""
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        System.out.println(reverseWords(" "));
    }

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return s;
        ArrayList<String> rt = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= s.length() - 1; i++) {
            if (s.charAt(i) == ' ') {
                if (sb != null && sb.length() != 0)
                    rt.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            sb.append(s.charAt(i));
        }
        if (sb != null && sb.length() != 0)
            rt.add(sb.toString());
        StringBuilder rs = new StringBuilder();
        for (int i = rt.size() - 1; i >= 0; i--) {
            rs.append(rt.get(i));
            rs.append(" ");
        }
        return rs.length() == 0 ? "" : rs.substring(0, rs.length() - 1).toString();
    }
}
