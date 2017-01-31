package Linear.String;

/**
 * Created by rliu on 1/30/17.
 * 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";

        int end = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            char curr = strs[0].charAt(i);
            boolean isMatch = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != curr) {
                    isMatch = false;
                    break;
                }
            }

            if (!isMatch) {
                end = i;
                break;
            } else
                end = i + 1;
        }
        return strs[0].substring(0, end);

    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"aa", "a"}));
    }
}
