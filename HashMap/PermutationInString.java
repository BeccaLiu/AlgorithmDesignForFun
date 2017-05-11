package HashMap;

import java.util.HashSet;

/**
 * Created by rliu on 5/11/17.
 * 567. Permutation in String
 */
public class PermutationInString {

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "dfadfboa"));
    }


    public static boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null)
            return false;

        char[] array = new char[26];
        for (char i : s1.toCharArray()) {
            array[i - 'a']++;
        }
        String pattern = charArraytoString(array);

//        HashSet<String> permuteset = new HashSet<>();
//        permutationSetGeneration(s1, new StringBuilder(), new boolean[s1.length()], permuteset);
        char[] arrayOther = new char[26];

        for (int i = 0; i < s2.length(); i++) {
            arrayOther[s2.charAt(i) - 'a']++;
            if (i >= s1.length() - 1) {
                if (i > s1.length() - 1)
                    arrayOther[s2.charAt(i - s1.length()) - 'a']--;
                String patternOther = charArraytoString(arrayOther);
                if (pattern.equals(patternOther))
                    return true;
            }
        }
        return false;
    }

    public static String charArraytoString(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i + ",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }


    //to slow the generate all the permutation
    public static void permutationSetGeneration(String s1, StringBuilder sb, boolean[] isVisited, HashSet<String> res) {
        if (sb.length() == s1.length()) {
            res.add(new String(sb));
            return;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (!isVisited[i]) {
                sb.append(s1.charAt(i));
                isVisited[i] = true;
                permutationSetGeneration(s1, sb, isVisited, res);
                isVisited[i] = false;
                sb.setLength(sb.length() - 1);
            }
        }
    }
}
